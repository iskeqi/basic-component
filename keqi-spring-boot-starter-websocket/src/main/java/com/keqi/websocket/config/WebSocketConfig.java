package com.keqi.websocket.config;

import com.keqi.websocket.auth.WebSocketInterceptor;
import com.keqi.websocket.handle.WebSocketHandler;
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

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/") // configure webSocketHandler
                .addInterceptors(webSocketInterceptor) // configure HandshakeInterceptor
                .setAllowedOrigins("*"); // allow cross-domain access
    }

}
