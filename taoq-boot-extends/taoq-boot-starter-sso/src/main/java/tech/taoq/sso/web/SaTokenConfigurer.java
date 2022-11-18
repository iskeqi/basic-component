package tech.taoq.sso.web;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SaTokenConfigurer
 *
 * @author keqi
 */
@Component
@ConditionalOnProperty(prefix = "taoq.sso", value = "satoken-intercepter", matchIfMissing = true)
public class SaTokenConfigurer implements WebMvcConfigurer {

    @Autowired(required = false)
    private ExcludePathPatterns excludePathPatterns;

    private static final String[] KNIFE4J_PATH = new String[]{
            "/doc.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/swagger-ui.html/**",
            "/v2/api-docs",
            "/favicon.ico",
            "/error"
    };

    private static final String[] FRONT_END = new String[]{
            "/",
            "/index.html",
            "/index",
            "/static/**",
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
        // 注册Sa-Token的路由拦截器
        InterceptorRegistration registration = registry.addInterceptor(new SaRouteInterceptor()).addPathPatterns("/**");
        registration.excludePathPatterns("/**/p");
        registration.excludePathPatterns(FRONT_END);
        registration.excludePathPatterns(KNIFE4J_PATH);
        if (excludePathPatterns != null) {
            registration.excludePathPatterns(excludePathPatterns.getExcludePathPatterns());
        }
    }
}
