package tech.taoq.sso.web;

import cn.dev33.satoken.exception.SaTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.taoq.common.response.ResultEntity;
import tech.taoq.common.response.ResultEntityBuilder;
import tech.taoq.common.response.ResultStatusEnum;
import tech.taoq.web.mvc.exception.ExceptionHandlerAdapter;

/**
 * SaToken 异常处理器
 *
 * @author keqi
 */
@Slf4j
@Component
public class StExceptionHandlerAdapter implements ExceptionHandlerAdapter {

    @Override
    public boolean supports(Throwable e) {
        return e instanceof SaTokenException;
    }

    @Override
    public ResultEntity<?> handle(Throwable e) {
        log.info("SaTokenException : {}", e.getMessage());
        return ResultEntityBuilder.failure(ResultStatusEnum.NO_AUTH.getCode(), e.getMessage());
    }
}
