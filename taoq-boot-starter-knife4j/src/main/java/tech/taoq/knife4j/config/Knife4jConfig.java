package tech.taoq.knife4j.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Knife4j 配置类
 *
 * @author keqi
 */
public class Knife4jConfig {

    @Bean
    @ConditionalOnMissingBean
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 关闭默认的响应信息
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .description("restful api")
                        .termsOfServiceUrl("https://www.keqi.com/")
                        .version("1.0")
                        .build())
                .groupName("default group")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("tech.taoq"))
                .paths(PathSelectors.any())
                .build();
    }
}
