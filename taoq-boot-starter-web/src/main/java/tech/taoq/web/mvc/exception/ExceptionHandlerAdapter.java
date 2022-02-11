package tech.taoq.web.mvc.exception;

import tech.taoq.common.response.ResultEntity;

/**
 * 异常处理器接口
 *
 * @author keqi
 */
public interface ExceptionHandlerAdapter {

    /**
     * 是否能够处理当前类型的异常
     *
     * @param e e
     * @return r
     */
    boolean supports(Throwable e);

    /**
     * 异常处理逻辑
     *
     * @param e e
     * @return r
     */
    ResultEntity<?> handle(Throwable e);
}
