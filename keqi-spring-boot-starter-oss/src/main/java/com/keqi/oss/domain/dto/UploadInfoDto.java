package com.keqi.oss.domain.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * UploadInfoDto
 *
 * @author keqi
 */
public class UploadInfoDto {

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件下载url")
    private String uploadUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }
}
