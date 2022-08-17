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
import tech.taoq.oms.OmsConstant;
import tech.taoq.oms.domain.db.PackageFileDO;
import tech.taoq.oms.domain.db.PackageRecordDO;
import tech.taoq.oms.domain.dto.AppStatus;
import tech.taoq.oms.domain.param.OperateParam;
import tech.taoq.oms.domain.param.UploadJarParam;
import tech.taoq.oms.mapper.PackageFileMapper;
import tech.taoq.oms.mapper.PackageRecordMapper;
import tech.taoq.oms.util.RuntimeShellUtil;
import tech.taoq.system.service.ConfigService;
import tech.taoq.web.mvc.result.NoAdvice;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Api(tags = "应用升级")
@RestController
@RequestMapping("/oms/upgrade")
public class AppUpgrade {

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
    public AppStatus status(@RequestParam(required = false) String appKey) {
        AppStatus appStatus = new AppStatus();
        appStatus.setStatus(true);

        if (!StringUtils.hasText(appKey)) {
            return appStatus;
        }

        // 示例: WCS_ACCESS_URL:http://127.0.0.1:8089
        String accessKey = appKey.concat(OmsConstant.ACCESS_URL);
        String accessUrl = configService.getByConfigKey(accessKey);
        if (!StringUtils.hasText(accessUrl)) {
            throw new ParamIllegalException("不存在此配置 " + accessKey);
        }

        String[] split = accessUrl.split(":");
        if (split.length != 3) {
            throw new ParamIllegalException("配置格式错误 " + accessKey);
        }

        if (!Objects.equals(port, split[2])) {
            // 如果查询的是自身以外的其它程序
            String respStr = null;
            try {
                respStr = HttpUtil.get(accessUrl);
            } catch (Exception e) {
                log.error("get " + appKey + " status error", e);
            }


            if (respStr != null) {
                AppStatus respAppStatus = JsonUtil.readValue(respStr, AppStatus.class);
                appStatus.setStatus(respAppStatus.getStatus());
            } else {
                appStatus.setStatus(false);
            }
        }

        return appStatus;
    }

    @ApiOperation("操作应用")
    @PostMapping("/operate")
    public void operate(@RequestBody OperateParam param) {
        synchronized (operateObjLock) {
            // 示例: WCS_DEPLOY_SHELL:/data/riot/wcs/bin/wcs.sh
            String deployKey = param.getAppKey().concat(OmsConstant.DEPLOY_SHELL);
            String deployShell = configService.getByConfigKey(deployKey);
            if (!StringUtils.hasText(deployShell)) {
                throw new ParamIllegalException("不存在此配置 " + deployKey);
            }

            String osName = System.getProperties().getProperty("os.name");

            if (!"Linux".equals(osName)) {
                throw new ParamIllegalException("当前操作仅支持在 Linux 操作系统中运行");
            }

            RuntimeShellUtil.Result result = new RuntimeShellUtil.Result();
            // 构建命令,如: nohup sudo bash /data/riot/wcs/bin/wcs.sh restart > /dev/null 2>&1
            String command = "nohup sudo bash " + deployShell + " " + param.getOperate().toLowerCase() + " > /dev/null 2>&1";
            // 执行命令
            log.info("start exec operate {}, command is {}", param.getOperate(), command);
            RuntimeShellUtil.execute(command);

            log.info("operate app {} command {}, result {}", param.getAppKey(), param.getOperate(), JsonUtil.writeValueAsString(result));
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
            packageFileMapper.setGlobalMaxAllowedPacket();

            // 保存二进制文件到DB中
            PackageFileDO t1 = new PackageFileDO();
            t1.setPackageBytes(bytes);
            packageFileMapper.insert(t1);

            // 记录业务数据
            PackageRecordDO t2 = new PackageRecordDO();
            t2.setName(originalFilename);
            // 示例: WCS
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
        Page<PackageRecordDO> page = packageRecordMapper.selectPage(param.toPage(), Wrappers.lambdaQuery(PackageRecordDO.class).orderByDesc(PackageRecordDO::getId));
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
            String installKey = t1.getType().concat(OmsConstant.INSTALL_PATH);
            String installPath = configService.getByConfigKey(installKey);
            if (!StringUtils.hasText(installPath)) {
                throw new ParamIllegalException("不存在此配置 " + installKey);
            }

            // 示例: WCS_DEPLOY_SHELL:/data/riot/wcs/bin/wcs.sh
            String deployKey = t1.getType().concat(OmsConstant.DEPLOY_SHELL);
            String deployShell = configService.getByConfigKey(deployKey);
            if (!StringUtils.hasText(deployShell)) {
                throw new ParamIllegalException("不存在此配置 " + deployKey);
            }

            List<String> fileNames = FileUtil.listFileNames(installPath);
            if (fileNames.size() != 1) {
                throw new ParamIllegalException("此目录下文件异常,请稍后重试或联系厂商技术人员处理 " + installPath);
            }

            // 从DB中读取二进制数据,并写入到指定目录去
            PackageFileDO t2 = packageFileMapper.selectById(t1.getFileId());
            String fileName = RandomUtil.randomString(6);
            File tempFile = new File(installPath, fileName);
            FileUtil.writeBytes(t2.getPackageBytes(), tempFile);

            // 将当前文件标记为正在使用的版本
            packageRecordMapper.update(new PackageRecordDO().setTag(false), null);
            PackageRecordDO t3 = new PackageRecordDO();
            t3.setId(t1.getId());
            t3.setTag(true);
            packageRecordMapper.updateById(t3);

            RuntimeShellUtil.Result result = new RuntimeShellUtil.Result();
            // 构建命令,如: nohup sudo bash /data/riot/wcs/bin/wcs.sh upgrade tempFileName > /dev/null 2>&1
            String command = "nohup sudo bash " + deployShell + " " + OperateParam.OPERATE.UPGRADE.name().toLowerCase() + " " + fileName + " > /dev/null 2>&1";
            // 执行命令
            log.info("start exec upgrade command : {}", command);
            RuntimeShellUtil.execute(command);
            log.info("upgrade app {}, result {}", t1.getType(), JsonUtil.writeValueAsString(result));
        }
    }
}
