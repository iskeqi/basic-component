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
     * 文件存储类型 [localFileSystem,minio]
     */
    private String fileStorageType = "localFileSystem";

    /**
     * Minio 相关属性
     */
    private Minio minio = new Minio();

    public static class Minio {

        /**
         * MINIO URL
         */
        private String endpoint;

        /**
         * MINIO accessKey
         */
        private String accessKey;

        /**
         * MINIO secretKey
         */
        private String secretKey;

        /**
         * MINIO defaultBucket
         */
        private String defaultBucket;

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getDefaultBucket() {
            return defaultBucket;
        }

        public void setDefaultBucket(String defaultBucket) {
            this.defaultBucket = defaultBucket;
        }
    }

    public String getFileStorageType() {
        return fileStorageType;
    }

    public void setFileStorageType(String fileStorageType) {
        this.fileStorageType = fileStorageType;
    }

    public Minio getMinio() {
        return minio;
    }

    public void setMinio(Minio minio) {
        this.minio = minio;
    }
}
