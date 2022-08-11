package tech.taoq.web.mvc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tech.taoq.common.response.ResultEntity;
import tech.taoq.common.response.ResultEntityBuilder;
import tech.taoq.common.response.ResultStatusEnum;
import tech.taoq.web.WebProperties;

/**
 * 默认异常处理器适配器(优先级是最低的，用于最后保底)
 *
 * @author keqi
 */
@Order
@Component
public class DefaultExceptionHandlerAdapter implements ExceptionHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandlerAdapter.class);

    @Value("${spring.profiles.active:local-dev}")
    private String active;

    @Autowired
    private WebProperties webProperties;

    @Override
    public boolean supports(Throwable e) {
        return true;
    }

    @Override
    public ResultEntity<?> handle(Throwable e) {
        // 未主动进行处理的异常，打印异常栈信息便于排查问题
        log.error("no built-in ExceptionHandlerAdapter to handle this exception", e);

        if (webProperties.getProdProfileName().equals(active)) {
            // 邮件、微信、钉钉通知相关责任人
            return ResultEntityBuilder.failure(ResultStatusEnum.SERVER_ERROR.getCode());
        }

        // 开发阶段，直接将异常信息通过接口响应出去，便于排查问题
        return ResultEntityBuilder.failure(e.toString());
    }
}
