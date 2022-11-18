package tech.taoq.websocket;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * WebSocketProperties
 *
 * @author keqi
 */
@Component
@ConfigurationProperties(prefix = "taoq.websocket")
public class WebSocketProperties {

    /**
     * WebSocketHandler 访问路径
     */
    private String webSocketHandlerPath = "/ws";

    public String getWebSocketHandlerPath() {
        return webSocketHandlerPath;
    }

    public void setWebSocketHandlerPath(String webSocketHandlerPath) {
        this.webSocketHandlerPath = webSocketHandlerPath;
    }
}
