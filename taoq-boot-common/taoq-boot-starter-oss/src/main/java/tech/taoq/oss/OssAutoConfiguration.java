package tech.taoq.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.taoq.oss.domain.db.UploadFileDO;

/**
 * OssAutoConfiguration
 *
 * @author keqi
 */
@ComponentScan("tech.taoq.oss")
public class OssAutoConfiguration {

    @Autowired
    private OssProperties ossProperties;

    /**
     * 配置 myResourceHandlers 对象
     * <p>
     * 直接利用 Servlet 容器作为一个 web 服务器，提供文件下载的功能
     */
    @Bean
    @ConditionalOnProperty(prefix = "taoq.oss", name = "resourceHandlers",
            havingValue = "true", matchIfMissing = true)
    public WebMvcConfigurer myResourceHandlers() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                // 使用本地文件系统存储文件时，才启用此配置
                if (UploadFileDO.StorageType.LOCAL_FILE_SYSTEM.getCodeName().equals(ossProperties.getStorageType())) {
                    String rootPath = ossProperties.getLocalFileSystem().getRootPath();
                    if (!rootPath.startsWith("/")) {
                        rootPath = "/" + rootPath;
                    }
                    if (!rootPath.endsWith("/")) {
                        rootPath = rootPath + "/";
                    }
                    String locations = "file://" + rootPath;
                    registry.addResourceHandler("/oss/**").addResourceLocations(locations);
                }
                registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/META-INF/resources/")
                        .addResourceLocations("classpath:/resources/")
                        .addResourceLocations("classpath:/static/")
                        .addResourceLocations("classpath:/public/");
            }
        };
    }
}
