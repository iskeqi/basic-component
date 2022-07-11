package tech.taoq.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import tech.taoq.log.domain.bo.AccessLog;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AccessLogAspectService
 *
 * @author keqi
 */
@Component
@ConditionalOnProperty(prefix = "taoq.log", value = "access-log")
public class AccessLogAspectService {

    private static final ExecutorService LOG_EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);

    @Autowired(required = false)
    private List<AccessLogService> accessLogServiceList;

    public void writeLog(AccessLog accessLog) {
        if (accessLogServiceList != null && accessLogServiceList.size() > 0) {
            for (AccessLogService accessLogService : accessLogServiceList) {
                LOG_EXECUTOR_SERVICE.submit(() -> accessLogService.writeAccessLog(accessLog));
            }
        }
    }
}
