package tech.taoq.system;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author keqi
 */
@EnableCaching
@ComponentScan("tech.taoq.system")
public class SystemAutoConfiguration {
}
