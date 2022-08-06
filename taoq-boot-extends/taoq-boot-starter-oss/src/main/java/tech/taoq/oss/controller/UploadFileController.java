package tech.taoq.oss.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.oss.domain.dto.DownloadInfoDto;
import tech.taoq.oss.domain.dto.NotificationDto;
import tech.taoq.oss.domain.dto.UploadFileDto;
import tech.taoq.oss.domain.param.*;
import tech.taoq.oss.service.UploadFileService;

import java.io.IOException;

@Api(tags = "文件管理")
@RestController
@RequestMapping("/sys/upload")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @ApiOperation("获取文件上传信息")
    @PostMapping
    public UploadFileDto uploadFileInfo(@RequestBody UploadFileParam param) {
        return uploadFileService.uploadFileInfo(param.getFileName());
    }

    @ApiOperation("获取文件下载链接")
    @PostMapping("/downloadInfo")
    public DownloadInfoDto downloadInfo(@RequestBody DownloadInfoParam param) {
        return uploadFileService.downloadInfo(param.getFileName());
    }

    @ApiOperation("删除文件")
    @DeleteMapping
    public void deleteFile(@RequestBody DeleteFileParam param) {
        uploadFileService.deleteFile(param.getId());
    }

    @ApiOperation("接收文件上传成功的通知")
    @PostMapping("/notification")
    public NotificationDto notification(@RequestBody NotificationParam param) {
        return uploadFileService.notification(param);
    }

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public void uploadFile(UploadParam param) throws IOException {
        uploadFileService.uploadFile(param);
    }
}
