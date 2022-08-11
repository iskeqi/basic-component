package tech.taoq.websocket.config;

import tech.taoq.websocket.WebSocketProperties;
import tech.taoq.websocket.auth.WebSocketInterceptor;
import tech.taoq.websocket.handle.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocketConfig
 *
 * @author keqi
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSocketHandler webSocketHandler;
    @Autowired
    private WebSocketInterceptor webSocketInterceptor;
    @Autowired
    private WebSocketProperties webSocketProperties;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, webSocketProperties.getWebSocketHandlerPath()) // 配置 webSocketHandler
                .addInterceptors(webSocketInterceptor) // 配置握手拦截器
                .setAllowedOrigins("*"); // 允许跨域访问
    }

}
