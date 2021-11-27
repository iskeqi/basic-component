package com.keqi.mp;

import com.keqi.mp.config.MyBatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Import;

/**
 * @author keqi
 */
@MapperScan("com.keqi")
@Import(MyBatisPlusConfig.class)
public class MpAutoConfiguration {
}
