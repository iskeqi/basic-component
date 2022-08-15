package tech.taoq.oms.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.common.util.JsonUtil;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.oms.domain.db.PackageFileDO;
import tech.taoq.oms.domain.db.PackageRecordDO;
import tech.taoq.oms.domain.dto.AppStatus;
import tech.taoq.oms.domain.param.OperateParam;
import tech.taoq.oms.domain.param.UploadJarParam;
import tech.taoq.oms.mapper.PackageFileMapper;
import tech.taoq.oms.mapper.PackageRecordMapper;
import tech.taoq.system.service.ConfigService;
import tech.taoq.web.mvc.result.NoAdvice;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Api(tags = "应用升级")
@RestController
@RequestMapping("/oms/upgrade")
public class AppUpgrade {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);

    @Autowired
    private ConfigService configService;
    @Autowired
    private PackageRecordMapper packageRecordMapper;
    @Autowired
    private PackageFileMapper packageFileMapper;

    private final Object upgradeObjLock = new Object();
    private final Object operateObjLock = new Object();

    @Value("${server.port:}")
    private String port;

    @ApiOperation("查询应用状态")
    @GetMapping("/status")
    @NoAdvice
    public AppStatus status(@RequestParam(required = false) String appAccessUrl) {
        AppStatus appStatus = new AppStatus();
        appStatus.setStatus(true);

        if (!StringUtils.hasText(appAccessUrl)) {
            return appStatus;
        }

        // 示例: WCS_ACCESS_URL:http://127.0.0.1:8089
        String accessUrl = configService.getByConfigKey(appAccessUrl);
        if (!StringUtils.hasText(accessUrl)) {
            throw new ParamIllegalException("不存在此配置 " + accessUrl);
        }

        String[] split = accessUrl.split(":");
        if (split.length != 3) {
            throw new ParamIllegalException("配置格式错误 " + accessUrl);
        }

        if (!Objects.equals(port, split[2])) {
            // 如果查询的是自身以外的其它程序
            String respStr = HttpUtil.get(accessUrl);
            AppStatus respAppStatus = JsonUtil.readValue(respStr, AppStatus.class);
            appStatus.setStatus(respAppStatus.getStatus());
        }

        return appStatus;
    }

    @ApiOperation("操作应用")
    @PostMapping("/operate")
    public void operate(@RequestBody OperateParam param) {
        synchronized (operateObjLock) {
            // 示例: WCS_DEPLOY_SHELL:/data/riot/wcs/bin/wcs.sh
            String deployShell = configService.getByConfigKey(param.getAppDeployShell());
            if (!StringUtils.hasText(deployShell)) {
                throw new ParamIllegalException("不存在此配置 " + deployShell);
            }

            String osName = System.getProperties().getProperty("os.name");

            if (!"Linux".equals(osName)) {
                throw new ParamIllegalException("当前操作仅支持在 Linux 操作系统中运行");
            }

            // 构建命令,如: sudo bash /data/riot/wcs/bin/wcs.sh restart
            String command = "sudo bash " + deployShell + " " + param.getOperate().toLowerCase();

            Process process = null;
            try {
                // 执行命令
                process = Runtime.getRuntime().exec(command);

                // 获取运行成功时的输出
                StringJoiner successResult = new StringJoiner("\n");
                Process successProcess = process;
                EXECUTOR_SERVICE.submit(() -> {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(successProcess.getInputStream()));
                    try {
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            successResult.add(line);
                        }
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    } finally {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                });

                // 获取运行失败时的输出
                StringJoiner errorResult = new StringJoiner("\n");
                Process errorProcess = process;
                EXECUTOR_SERVICE.submit(() -> {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorProcess.getErrorStream()));
                    try {
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            errorResult.add(line);
                        }
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    } finally {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                });

                // 等待执行结果
                int resultCode = process.waitFor();

                if (resultCode == 0) {
                    log.info(successResult.toString());
                } else {
                    log.info(errorResult.toString());
                }

            } catch (IOException | InterruptedException e) {
                log.error(e.getMessage(), e);
            } finally {
                assert process != null;
                process.destroy();
            }
        }
    }

    @ApiOperation("上传应用文件")
    @PostMapping("/uploadJar")
    public void uploadJar(UploadJarParam param) {
        MultipartFile uploadFile = param.getUploadFile();
        String originalFilename = uploadFile.getOriginalFilename();

        boolean success = true;
        byte[] bytes = null;
        try {
            bytes = uploadFile.getBytes();
        } catch (IOException e) {
            success = false;
            log.error("get bytes failure", e);
        }

        if (success) {
            // 保存二进制文件到DB中
            PackageFileDO t1 = new PackageFileDO();
            t1.setPackageBytes(bytes);
            packageFileMapper.insert(t1);

            // 记录业务数据
            PackageRecordDO t2 = new PackageRecordDO();
            t2.setName(originalFilename);
            t2.setType(param.getType());
            t2.setSize(bytes.length);
            t2.setFileId(t1.getId());
            t2.setNote(param.getNote());
            packageRecordMapper.insert(t2);
        }
    }

    @ApiOperation("查询安装包文件列表")
    @GetMapping("/page")
    public PageDto<PackageRecordDO> page(PageParam<PackageRecordDO> param) {
        Page<PackageRecordDO> page = packageRecordMapper.selectPage(param.toPage(),
                Wrappers.lambdaQuery(PackageRecordDO.class).orderByDesc(PackageRecordDO::getId));
        return PageDto.build(page);
    }

    @ApiOperation("升级")
    @PostMapping("/operate/{packageRecordId}")
    public void upgrade(@PathVariable String packageRecordId) {
        synchronized (upgradeObjLock) {
            PackageRecordDO t1 = packageRecordMapper.selectById(packageRecordId);
            if (t1 == null) {
                throw new ParamIllegalException("不存在对应安装包");
            }

            // 示例: WCS_INSTALL_PATH:/data/riot/wcs
            String installPath = configService.getByConfigKey(t1.getType());
            if (!StringUtils.hasText(installPath)) {
                throw new ParamIllegalException("不存在此配置 " + installPath);
            }

            List<String> fileNames = FileUtil.listFileNames(installPath);
            if (fileNames.size() != 1) {
                throw new ParamIllegalException("此目录下文件异常,请稍后重试或联系厂商技术人员处理 " + installPath);
            }

            // 从DB中读取二进制数据,并写入到指定目录去
            PackageFileDO t2 = packageFileMapper.selectById(t1.getFileId());
            File tempFile = new File(installPath, RandomUtil.randomString(6));
            FileUtil.writeBytes(t2.getPackageBytes(), tempFile);

            // 重命名文件
            FileUtil.rename(tempFile, fileNames.get(0), true);

            // 将当前文件标记为正在使用的版本
            PackageRecordDO t3 = new PackageRecordDO();
            t3.setId(t1.getId());
            t3.setTag(true);
            packageRecordMapper.updateById(t3);
        }
    }
}
