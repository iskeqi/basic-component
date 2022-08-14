package tech.taoq.oms.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.oms.domain.dto.AppStatus;
import tech.taoq.oms.domain.param.OperateParam;
import tech.taoq.oms.domain.param.UploadJarParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Api(tags = "应用升级")
@RestController
@RequestMapping("/oms/upgrade")
public class AppUpgrade {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);

    @ApiOperation("查询应用状态")
    @GetMapping("/status")
    public AppStatus status() {
        AppStatus appStatus = new AppStatus();
        appStatus.setStatus(true);
        return appStatus;
    }

    @ApiOperation("操作应用")
    @PostMapping("/operate")
    public void operate(@RequestBody OperateParam param) {
        String osName = System.getProperties().getProperty("os.name");

        if (!"Linux".equals(osName)) {
            throw new ParamIllegalException("当前操作仅支持在 Linux 操作系统中运行");
        }

        // 构建命令,如: sudo bash /data/riot/wcs/bin/wcs.sh restart
        String command = "sudo bash " + param.getShellFilePath() + " " + param.getOperate().toLowerCase();

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

    @ApiOperation("上传应用文件")
    @PostMapping("/uploadJar")
    public void uploadJar(UploadJarParam param) {
        MultipartFile uploadFile = param.getUploadFile();
        String originalFilename = uploadFile.getOriginalFilename();

        boolean success = true;
        try {
            byte[] bytes = uploadFile.getBytes();
        } catch (IOException e) {
            success = false;
            log.error("get bytes failure", e);
        }

        if (success) {
            // 保存二进制文件到DB中,以及记录业务数据
        }
    }

    @ApiOperation("升级")
    @PostMapping("/operate")
    public void upgrade(@RequestBody OperateParam param) {
        // 从DB中读取二进制数据,并写入到指定目录去

        // FileUtil.writeBytes();


        // FileUtil.rename()
    }
}
