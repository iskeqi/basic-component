package com.keqi.web.mvc.result;

import com.keqi.common.response.ResultEntity;
import com.keqi.common.response.ResultEntityBuilder;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * 针对 @ResponseBody 注解响应的接口做统一处理
 * 实现原理：
 * 1、controller 的方法中响应了返回值后，正常的执行逻辑是：HttpMessageConverter 会序列化成 JSON，然后返回给客户端
 * 2、如果实现了 ResponseBodyAdvice 接口，此接口中允许在 HttpMessageConverter 序列化之前，替换掉 Controller 中接口返回值
 *
 * @author keqi
 */
@ControllerAdvice(basePackages = "com.keqi")
public class ResultResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        for (Annotation annotation : returnType.getMethodAnnotations()) {
            if (Objects.equals(NoAdvice.class, annotation.annotationType())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof ResultEntity || body instanceof String) {
            return body;
        } else if (void.class == returnType.getGenericParameterType()) {
            return ResultEntityBuilder.success();
        } else {
            return ResultEntityBuilder.success(body);
        }
    }
}
