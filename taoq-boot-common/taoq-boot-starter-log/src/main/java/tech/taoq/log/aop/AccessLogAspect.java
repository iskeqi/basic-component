package tech.taoq.log.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tech.taoq.common.exception.TaoqException;
import tech.taoq.common.pojo.AuthBO;
import tech.taoq.common.util.Auth;
import tech.taoq.log.domain.bo.AccessLog;
import tech.taoq.log.service.AccessLogAspectService;
import tech.taoq.log.service.UserIdentifier;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * AccessLogAspect
 *
 * @author keqi
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(prefix = "taoq.log", value = "access-log")
public class AccessLogAspect {

    private static final String ACCESS_LOG = "ACCESS_LOG";

    @Autowired(required = false)
    private UserIdentifier userIdentifier;
    @Autowired
    private AccessLogAspectService accessLogAspectService;

    //    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) "
//            + "|| @annotation(org.springframework.web.bind.annotation.PostMapping)"
//            + "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)"
//            + "|| @annotation(org.springframework.web.bind.annotation.PutMapping)"
//            + "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    @Pointcut("execution(public * **..controller.*.*(..))")
    public void accessLogPointCut() {
    }

    @Before("accessLogPointCut()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        AccessLog accessLog = new AccessLog();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) {
            uri = uri + "?" + queryString;
        }
        accessLog.setUri(uri);
        accessLog.setMethod(request.getMethod());
        accessLog.setClassName(joinPoint.getSignature().getDeclaringTypeName());
        accessLog.setMethodName(joinPoint.getSignature().getName());
        if (joinPoint.getArgs().length > 0) {
            accessLog.setReqBody(joinPoint.getArgs()[0]);
        }

        AuthBO authBO = new AuthBO<>();
        authBO.setKey(ACCESS_LOG);
        authBO.setData(accessLog);
        Auth.setAuthBO(authBO);
    }

    @AfterReturning(value = "accessLogPointCut()", returning = "resp")
    public void afterReturning(JoinPoint joinPoint, Object resp) {
        AuthBO authBO = Auth.getAuthBO(ACCESS_LOG);
        AccessLog accessLog = (AccessLog) authBO.getData();
        accessLog.setSuccess(true);
        accessLog.setRespBody(resp);
    }

    @After("accessLogPointCut()")
    public void after(JoinPoint joinPoint) {
        AuthBO authBO = Auth.getAuthBO(ACCESS_LOG);
        AccessLog accessLog = (AccessLog) authBO.getData();

        if (userIdentifier != null) {
            accessLog.setUserIdentifier(userIdentifier.getUserIdentifier());
        }
        accessLog.setEndTime(LocalDateTime.now());
        accessLog.setConsumeTime(String.valueOf(accessLog.getStartTime().until(accessLog.getEndTime(), ChronoUnit.MILLIS)));

        accessLogAspectService.writeLog((AccessLog) authBO.getData());
    }

    @AfterThrowing(value = "accessLogPointCut()", throwing = "throwable")
    public void afterThrowing(Throwable throwable) throws Throwable {
        String resp;
        if (throwable instanceof TaoqException) {
            TaoqException taoqException = (TaoqException) throwable;
            resp = taoqException.getStatus() + " - " + taoqException.getMessage();
        } else {
            resp = throwable.getMessage();
        }

        AuthBO authBO = Auth.getAuthBO(ACCESS_LOG);
        AccessLog accessLog = (AccessLog) authBO.getData();
        accessLog.setRespBody(resp);
        accessLog.setSuccess(false);
    }

    @Around("accessLogPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        AuthBO authBO = Auth.getAuthBO(ACCESS_LOG);
        AccessLog accessLog = (AccessLog) authBO.getData();
        accessLog.setStartTime(LocalDateTime.now());

        return proceedingJoinPoint.proceed();
    }
}
