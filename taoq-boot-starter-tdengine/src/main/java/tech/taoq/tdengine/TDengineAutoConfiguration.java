package tech.taoq.tdengine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * TDengineAutoConfiguration
 *
 * @author keqi
 */
@MapperScan("tech.taoq")
@ComponentScan("tech.taoq.tdengine")
public class TDengineAutoConfiguration {
}
