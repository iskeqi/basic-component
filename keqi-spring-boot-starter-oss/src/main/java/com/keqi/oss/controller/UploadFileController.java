package com.keqi.oss.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UploadFileController
 *
 * @author keqi
 */
@RestController
@RequestMapping("/sys/upload")
public class UploadFileController {

    // 文件上传

    // 文件下载

    // 文件删除
    @DeleteMapping("/{fileName}")
    public void delete(@PathVariable String fileName) {

    }
}
