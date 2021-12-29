package com.keqi.oss;

import com.keqi.oss.service.oss.FileSystemService;
import com.keqi.oss.service.oss.MinioService;
import com.keqi.oss.service.oss.OssService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * OssAutoConfiguration
 *
 * @author keqi
 */
@ComponentScan("com.keqi.oss")
public class OssAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "keqi.oss", name = "fileStorageType",
            havingValue = "localFileSystem", matchIfMissing = true)
    public OssService fileSystemService() {
        return new FileSystemService();
    }

    @Bean
    @ConditionalOnProperty(prefix = "keqi.oss", name = "fileStorageType", havingValue = "minio")
    public OssService minioService() {
        return new MinioService();
    }
}
