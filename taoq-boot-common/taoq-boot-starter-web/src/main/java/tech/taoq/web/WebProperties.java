package tech.taoq.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * WebProperties
 *
 * @author keqi
 */
@Component
@ConfigurationProperties(prefix = "taoq.web")
public class WebProperties {

    /**
     * 是否使用内置的 MappingJackson2HttpMessageConverter 对象
     */
    private Boolean myMappingJackson2HttpMessageConverter = true;

    /**
     * 是否使用内置的 MyStringToLocalDateConverter 对象
     */
    private Boolean myStringToLocalDateConverter = true;

    /**
     * 是否使用内置的 MyStringToLocalDateTimeConverter 对象
     */
    private Boolean myStringToLocalDateTimeConverter = true;

    /**
     * 是否使用内置的 MyStringToNumberConverterFactory 对象
     */
    private Boolean myStringToNumberConverterFactory = true;

    /**
     * 是否使用内置的 CorsFilter 对象进行跨域配置
     */
    private Boolean cors = true;

    /**
     * 生产环境的 spring.profiles.active 名称
     */
    private String prodProfileName = "prod";

    public Boolean getMyMappingJackson2HttpMessageConverter() {
        return myMappingJackson2HttpMessageConverter;
    }

    public void setMyMappingJackson2HttpMessageConverter(Boolean myMappingJackson2HttpMessageConverter) {
        this.myMappingJackson2HttpMessageConverter = myMappingJackson2HttpMessageConverter;
    }

    public Boolean getMyStringToLocalDateConverter() {
        return myStringToLocalDateConverter;
    }

    public void setMyStringToLocalDateConverter(Boolean myStringToLocalDateConverter) {
        this.myStringToLocalDateConverter = myStringToLocalDateConverter;
    }

    public Boolean getMyStringToLocalDateTimeConverter() {
        return myStringToLocalDateTimeConverter;
    }

    public void setMyStringToLocalDateTimeConverter(Boolean myStringToLocalDateTimeConverter) {
        this.myStringToLocalDateTimeConverter = myStringToLocalDateTimeConverter;
    }

    public Boolean getMyStringToNumberConverterFactory() {
        return myStringToNumberConverterFactory;
    }

    public void setMyStringToNumberConverterFactory(Boolean myStringToNumberConverterFactory) {
        this.myStringToNumberConverterFactory = myStringToNumberConverterFactory;
    }

    public Boolean getCors() {
        return cors;
    }

    public void setCors(Boolean cors) {
        this.cors = cors;
    }

    public String getProdProfileName() {
        return prodProfileName;
    }

    public void setProdProfileName(String prodProfileName) {
        this.prodProfileName = prodProfileName;
    }
}
