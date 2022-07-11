package tech.taoq.log.service;

import tech.taoq.log.domain.bo.AccessLog;

/**
 * AccessLogService
 *
 * @author keqi
 */
public interface AccessLogService {

    void writeAccessLog(AccessLog accessLog);
}
