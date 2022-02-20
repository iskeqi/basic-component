package tech.taoq.oss;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * OssProperties
 *
 * @author keqi
 */
@Component
@ConfigurationProperties(prefix = "taoq.oss")
public class OssProperties {

    /**
     * 文件存储类型 [localFileSystem,minio]
     */
    private String storageType = "localFileSystem";

    /**
     * 是否配置 /oss 为前缀的静态资源文件映射
     */
    private Boolean resourceHandlers;

    /**
     * Minio 相关属性
     */
    private Minio minio = new Minio();

    /**
     * 本地文件系统相关属性
     */
    private LocalFileSystem localFileSystem = new LocalFileSystem();

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

    public static class LocalFileSystem {

        /**
         * 本地文件系统根路径
         */
        private String rootPath;

        public String getRootPath() {
            return rootPath;
        }

        public void setRootPath(String rootPath) {
            this.rootPath = rootPath;
        }
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public Minio getMinio() {
        return minio;
    }

    public void setMinio(Minio minio) {
        this.minio = minio;
    }

    public LocalFileSystem getLocalFileSystem() {
        return localFileSystem;
    }

    public void setLocalFileSystem(LocalFileSystem localFileSystem) {
        this.localFileSystem = localFileSystem;
    }

    public Boolean getResourceHandlers() {
        return resourceHandlers;
    }

    public void setResourceHandlers(Boolean resourceHandlers) {
        this.resourceHandlers = resourceHandlers;
    }
}
