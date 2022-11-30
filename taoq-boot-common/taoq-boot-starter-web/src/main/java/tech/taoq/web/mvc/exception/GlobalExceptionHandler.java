package tech.taoq.web.mvc.exception;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.taoq.common.exception.TaoqException;
import tech.taoq.common.response.ResultEntity;
import tech.taoq.common.response.ResultEntityBuilder;
import tech.taoq.common.response.ResultStatusEnum;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.StringJoiner;

/**
 * 全局异常处理器
 *
 * @author keqi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private List<ExceptionHandlerAdapter> exceptionHandlerAdapterList;

    /**
     * TaoqException
     *
     * @param e TaoqException
     * @return r
     */
    @ExceptionHandler(value = TaoqException.class)
    public ResultEntity<?> businessException(TaoqException e) {
        log.error("status={},message={}", e.getStatus(), e.getMessage());
        return ResultEntityBuilder.failure(e.getStatus(), e.getMessage());
    }

    /**
     * 使用 @Validated 校验方法参数中使用 @RequestBody 修饰的实体类(支持多层嵌套校验,嵌套字段需使用 @Valid 修饰)
     * 使用 @Validated 校验方法参数中未使用任何注解修饰的实体类(GET/POST均支持,抛出的是BindException)
     *
     * @param e MethodArgumentNotValidException
     * @return r
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResultEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringJoiner errorMsg = new StringJoiner(";");
        for (ObjectError allError : e.getBindingResult().getAllErrors()) {
            errorMsg.add(allError.getDefaultMessage());
        }

        log.error("validate invalid fields: {}", errorMsg);
        return ResultEntityBuilder.failure(ResultStatusEnum.PARAM_ILLEGAL.getCode(), errorMsg.toString());
    }


    /**
     * 使用 @Validated 校验方法参数中的 @RequestParam 和 @PathVariable 修饰的参数,抛出的是这种异常(GET/POST均支持)
     * 使用 @Validated 校验方法参数中的,没有显式使用 @RequestParam 修饰的非实体类参数,抛出的是这种异常(GET/POST均支持)
     *
     * @param e ConstraintViolationException
     * @return r
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultEntity<?> constraintViolationException(ConstraintViolationException e) {
        StringJoiner errorMsg = new StringJoiner(";");
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            errorMsg.add(constraintViolation.getMessage());
        }

        log.error("validate invalid fields: {}", errorMsg);
        return ResultEntityBuilder.failure(ResultStatusEnum.PARAM_ILLEGAL.getCode(), errorMsg.toString());
    }

    /**
     * 捕获所有异常（这个异常必须要放在最后）
     *
     * @param e Throwable
     * @return r
     */
    @ExceptionHandler(Throwable.class)
    public ResultEntity<?> throwable(Throwable e) {
        for (ExceptionHandlerAdapter handlerAdapter : exceptionHandlerAdapterList) {
            if (handlerAdapter.supports(e)) {
                return handlerAdapter.handle(e);
            }
        }
        return ResultEntityBuilder.failure();
    }

}
