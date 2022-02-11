package tech.taoq.mp;

import tech.taoq.mp.config.MyBatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Import;

/**
 * @author keqi
 */
@MapperScan("tech.taoq")
@Import(MyBatisPlusConfig.class)
public class MpAutoConfiguration {
}
