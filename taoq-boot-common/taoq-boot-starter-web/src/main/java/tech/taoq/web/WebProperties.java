package tech.taoq.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * WebProperties
 *
 * @author keqi
 */
@Component
@ConfigurationProperties(prefix = "keqi.web")
public class WebProperties {

    /**
     * 是否使用内置的 MappingJackson2HttpMessageConverter 对象
     */
    private boolean mappingJackson2HttpMessageConverter = true;

    /**
     * 是否使用内置的 MyStringToLocalDateConverter 对象
     */
    private boolean myStringToLocalDateConverter = true;

    /**
     * 是否使用内置的 MyStringToLocalDateTimeConverter 对象
     */
    private boolean myStringToLocalDateTimeConverter = true;

    /**
     * 是否使用内置的 MyStringToNumberConverterFactory 对象
     */
    private boolean myStringToNumberConverterFactory = true;

    /**
     * 是否使用内置的 CorsFilter 对象进行跨域配置
     */
    private boolean cors = true;

    public boolean isMappingJackson2HttpMessageConverter() {
        return mappingJackson2HttpMessageConverter;
    }

    public void setMappingJackson2HttpMessageConverter(boolean mappingJackson2HttpMessageConverter) {
        this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
    }

    public boolean isMyStringToLocalDateConverter() {
        return myStringToLocalDateConverter;
    }

    public void setMyStringToLocalDateConverter(boolean myStringToLocalDateConverter) {
        this.myStringToLocalDateConverter = myStringToLocalDateConverter;
    }

    public boolean isMyStringToLocalDateTimeConverter() {
        return myStringToLocalDateTimeConverter;
    }

    public void setMyStringToLocalDateTimeConverter(boolean myStringToLocalDateTimeConverter) {
        this.myStringToLocalDateTimeConverter = myStringToLocalDateTimeConverter;
    }

    public boolean isMyStringToNumberConverterFactory() {
        return myStringToNumberConverterFactory;
    }

    public void setMyStringToNumberConverterFactory(boolean myStringToNumberConverterFactory) {
        this.myStringToNumberConverterFactory = myStringToNumberConverterFactory;
    }

    public boolean isCors() {
        return cors;
    }

    public void setCors(boolean cors) {
        this.cors = cors;
    }
}
