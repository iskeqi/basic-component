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
 * 如果希望对此类进行扩展，直接继续实现 WebMvcConfigurer 即可
 * Spring 是支持同时存在多个实现类的，实现同一个方法的最终效果是叠加，并不会互相影响
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

