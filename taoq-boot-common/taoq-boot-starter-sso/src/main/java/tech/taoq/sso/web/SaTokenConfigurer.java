package tech.taoq.sso.web;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
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

    private static final String[] KNIFE4J_PATH = new String[]{
            "/doc.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/swagger-ui.html/**",
            "/v2/api-docs",
            "/favicon.ico",
            "/error"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
        // 注册Sa-Token的路由拦截器
        registry.addInterceptor(new SaRouteInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/**/p")
                .excludePathPatterns(KNIFE4J_PATH);
    }
}
