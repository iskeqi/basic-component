package com.keqi.system;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author keqi
 */
@EnableCaching
@ComponentScan("com.keqi.system")
public class SystemAutoConfiguration {
}
