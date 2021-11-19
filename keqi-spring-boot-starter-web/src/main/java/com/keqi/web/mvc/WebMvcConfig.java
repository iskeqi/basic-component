package com.keqi.web.mvc;

import com.keqi.web.mvc.converter.MyStringToLocalDateConverter;
import com.keqi.web.mvc.converter.MyStringToLocalDateTimeConverter;
import com.keqi.web.mvc.converter.MyStringToNumberConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web mvc 配置类
 * <p>
 * (
 * 如果希望对此类进行扩展，但是又无法直接更改此类时，可以创建一个配置类继承 WebMvcConfig 重写本类中已经重写
 * 的方法，还可以实现 WebMvcConfigurer 接口中在本类没有实现的方法
 * )
 *
 * @author keqi
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加 Formatter 对象
     *
     * @param registry registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new MyStringToLocalDateConverter());
        registry.addConverter(new MyStringToLocalDateTimeConverter());
        registry.addConverterFactory(new MyStringToNumberConverterFactory());
    }
}

