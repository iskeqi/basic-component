package tech.taoq.system;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SystemProperties
 *
 * @author keqi
 */
@Component
@ConfigurationProperties(prefix = "taoq.system")
public class SystemProperties {

    /**
     * 是否启用响应状态码的存储
     */
    private Boolean resultCode;

    public Boolean getResultCode() {
        return resultCode;
    }

    public void setResultCode(Boolean resultCode) {
        this.resultCode = resultCode;
    }
}
