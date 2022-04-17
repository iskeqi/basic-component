package tech.taoq.mp;

import tech.taoq.mp.config.MyBatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Import;

/**
 * MpAutoConfiguration
 *
 * @author keqi
 */
@MapperScan("tech.taoq.**.mapper")
@Import(MyBatisPlusConfig.class)
public class MpAutoConfiguration {
}
