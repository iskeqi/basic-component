package tech.taoq.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import tech.taoq.log.domain.bo.AccessLog;

import java.util.List;

/**
 * AccessLogAspectService
 *
 * @author keqi
 */
@Component
@ConditionalOnProperty(prefix = "taoq.log.access-log", value = "enable")
public class AccessLogAspectService {

    @Autowired(required = false)
    private List<AccessLogService> accessLogServiceList;

    public void writeLog(AccessLog accessLog) {
        if (accessLogServiceList != null && accessLogServiceList.size() > 0) {
            for (AccessLogService accessLogService : accessLogServiceList) {
                accessLogService.writeAccessLog(accessLog);
            }
        }
    }
}
