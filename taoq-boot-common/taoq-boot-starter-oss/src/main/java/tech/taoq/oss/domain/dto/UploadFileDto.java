package tech.taoq.oss.domain.dto;

/**
 * UploadInfoDto
 *
 * @author keqi
 */
public class UploadFileDto {

    /**
     * 存储类型[1:本地文件系统 2:minio]
     */
    private String storageType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件上传url
     */
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

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }
}
