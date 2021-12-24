package com.keqi.oss.domain.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * DownloadInfoDto
 *
 * @author keqi
 */
public class DownloadInfoDto {

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件下载url")
    private String downloadUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
