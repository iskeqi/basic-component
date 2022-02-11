package tech.taoq.knife4j;

import tech.taoq.knife4j.config.Knife4jConfig;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author keqi
 */
@Import(Knife4jConfig.class)
@EnableSwagger2WebMvc
public class Knife4jAutoConfiguration {
}
