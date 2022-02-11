package tech.taoq.web.mvc.exception;

import tech.taoq.common.exception.KeqiException;
import tech.taoq.common.response.ResultEntity;
import tech.taoq.common.response.ResultEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常处理器
 *
 * @author keqi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private List<ExceptionHandlerAdapter> exceptionHandlerAdapterList;

    /**
     * KeqiException
     *
     * @param e KeqiException
     * @return r
     */
    @ExceptionHandler(value = KeqiException.class)
    public ResultEntity businessException(KeqiException e) {
        return ResultEntityBuilder.failure(e.getStatus(), e.getMessage());
    }

    /**
     * 捕获所有异常（这个异常必须要放在最后）
     *
     * @param e Throwable
     * @return r
     */
    @ExceptionHandler(Throwable.class)
    public ResultEntity throwable(Throwable e) {
        for (ExceptionHandlerAdapter handlerAdapter : exceptionHandlerAdapterList) {
            if (handlerAdapter.supports(e)) {
                return handlerAdapter.handle(e);
            }
        }
        return ResultEntityBuilder.failure();
    }

}
