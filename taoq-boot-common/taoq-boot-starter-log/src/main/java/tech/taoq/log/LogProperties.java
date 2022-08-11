package tech.taoq.log;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * WebProperties
 *
 * @author keqi
 */
@Component
@ConfigurationProperties(prefix = "taoq.log")
public class LogProperties {

    /**
     * 是否使用 @OperationLog 注解记录访问日志
     */
    private Boolean operateLog = false;

    public static class AccessLog {

        /**
         * 是否使用 AOP 切面记录 web 访问日志
         */
        private Boolean enable = false;

        /**
         * 是否使用 AOP 切面记录 web 访问日志到日志文件中
         */
        private Boolean logFile = false;

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }

        public Boolean getLogFile() {
            return logFile;
        }

        public void setLogFile(Boolean logFile) {
            this.logFile = logFile;
        }
    }

    public Boolean getOperateLog() {
        return operateLog;
    }

    public void setOperateLog(Boolean operateLog) {
        this.operateLog = operateLog;
    }
}
