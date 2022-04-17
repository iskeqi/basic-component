package tech.taoq.web.mvc.result;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 如不希望被 ResultResponseBodyAdvice 代理时，可在方法上增加此注解
 *
 * @author keqi
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface NoAdvice {
}
