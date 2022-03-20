package tech.taoq.oss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.oss.domain.dto.DownloadInfoDto;
import tech.taoq.oss.domain.dto.NotificationDto;
import tech.taoq.oss.domain.dto.UploadFileDto;
import tech.taoq.oss.domain.param.*;
import tech.taoq.oss.service.UploadFileService;

import java.io.IOException;

/**
 * UploadFileController
 *
 * @author keqi
 */
@RestController
@RequestMapping("/sys/upload")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 获取文件上传信息
     *
     * @param param param
     * @return r
     */
    @PostMapping
    public UploadFileDto uploadFileInfo(@RequestBody UploadFileParam param) {
        return uploadFileService.uploadFileInfo(param.getFileName());
    }

    /**
     * 获取文件下载链接
     *
     * @param param param
     * @return r
     */
    @PostMapping("/downloadInfo")
    public DownloadInfoDto downloadInfo(@RequestBody DownloadInfoParam param) {
        return uploadFileService.downloadInfo(param.getFileName());
    }

    /**
     * 删除文件
     *
     * @param param param
     */
    @DeleteMapping
    public void deleteFile(@RequestBody DeleteFileParam param) {
        uploadFileService.deleteFile(param.getId());
    }

    /**
     * 接收文件上传成功的通知
     *
     * @param param param
     */
    @PostMapping("/notification")
    public NotificationDto notification(@RequestBody NotificationParam param) {
        return uploadFileService.notification(param);
    }

    /**
     * 文件上传
     *
     * @param param param
     * @throws IOException IOException
     */
    @PostMapping("/upload")
    public void uploadFile(UploadParam param) throws IOException {
        uploadFileService.uploadFile(param);
    }
}
