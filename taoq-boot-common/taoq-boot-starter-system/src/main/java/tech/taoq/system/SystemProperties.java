package tech.taoq.system;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    private Boolean resultCode = false;

    /**
     * 扫描以下这些包下实现了 IResultStatusEnum 接口的枚举类
     */
    private List<String> packages = new ArrayList<>();

    public Boolean getResultCode() {
        return resultCode;
    }

    public void setResultCode(Boolean resultCode) {
        this.resultCode = resultCode;
    }

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }
}
