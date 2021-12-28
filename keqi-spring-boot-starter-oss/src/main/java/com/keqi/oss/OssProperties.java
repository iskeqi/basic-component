package com.keqi.oss;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * OssProperties
 *
 * @author keqi
 */
@Component
@ConfigurationProperties(prefix = "keqi.oss")
public class OssProperties {

    /**
     * 文件存储类型[localFileSystem,minio]
     */
    private String fileStorageType = "localFileSystem";

    public String getFileStorageType() {
        return fileStorageType;
    }

    public void setFileStorageType(String fileStorageType) {
        this.fileStorageType = fileStorageType;
    }
}
