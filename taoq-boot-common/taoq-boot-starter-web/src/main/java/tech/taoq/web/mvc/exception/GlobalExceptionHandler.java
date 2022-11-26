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
     * 使用 @Validated 校验方法参数中的 @RequestBody 修饰的实体类，抛出的是这种异常
     * 使用 @Valid 校验方法参数中的 @RequestBody 修饰的实体类，抛出的是这种异常(嵌套多层次校验)
     * <p>
     * 总结：使用了 @RequestBody 修饰参数，抛出的就是此种异常
     *
     * @param e MethodArgumentNotValidException
     * @return r
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringJoiner errorMsg = new StringJoiner(";");
        for (ObjectError allError : e.getBindingResult().getAllErrors()) {
            errorMsg.add(allError.getDefaultMessage());
        }

        return ResultEntityBuilder.failure(ResultStatusEnum.PARAM_ILLEGAL.getCode(), errorMsg.toString());
    }

    /**
     * 使用 @Validated 校验方法参数中的实体类参数，抛出的是此种异常(无论方法是 GET 还是 POST)
     * <p>
     * (GET请求有多个参数时，可以使用此种方式接收参数，如果是 POST 建议还是使用 @RequestBody 的方式来接收参数)
     *
     * @param e BindException
     * @return r
     */
    @ExceptionHandler(value = BindException.class)
    public ResultEntity<?> bindException(BindException e) {
        StringJoiner errorMsg = new StringJoiner(";");
        for (ObjectError allError : e.getBindingResult().getAllErrors()) {
            errorMsg.add(allError.getDefaultMessage());
        }

        return ResultEntityBuilder.failure(ResultStatusEnum.PARAM_ILLEGAL.getCode(), errorMsg.toString());
    }

    /**
     * 使用 @Validated 校验方法参数中的 @RequestParam 和 @PathVariable 修饰的参数，抛出的是这种异常(无论方法是 GET 还是 POST)
     * Controller 方法中没有显式使用 @RequestParam 修饰的非实体类参数，同样抛出的是此种异常
     * <p>
     * 总结：使用了 @RequestParam/@PathVariable 修饰参数，抛出的就是此种异常
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
