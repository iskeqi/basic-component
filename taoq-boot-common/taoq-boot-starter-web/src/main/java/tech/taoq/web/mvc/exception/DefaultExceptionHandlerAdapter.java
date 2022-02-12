package tech.taoq.web.mvc.exception;

import tech.taoq.common.response.ResultEntity;
import tech.taoq.common.response.ResultEntityBuilder;
import tech.taoq.common.response.ResultStatusEnum;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 默认异常处理器适配器(优先级是最低的，用于最后保底)
 *
 * @author keqi
 */
@Order
@Component
public class DefaultExceptionHandlerAdapter implements ExceptionHandlerAdapter {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DefaultExceptionHandlerAdapter.class);

    @Value("${spring.profiles.active:local-dev}")
    private String active;

    @Override
    public boolean supports(Throwable e) {
        return true;
    }

    @Override
    public ResultEntity<?> handle(Throwable e) {
        // 未知异常，打印异常栈信息便于排查问题
        log.error(e.getMessage(), e);

        if ("prod".equals(active)) {
            // 邮件、微信、钉钉通知相关责任人
            return ResultEntityBuilder.failure(ResultStatusEnum.SERVER_ERROR.getCode());
        }

        // 开发阶段，直接将异常信息通过接口响应出去，便于排查问题
        return ResultEntityBuilder.failure(e.toString());
    }
}
