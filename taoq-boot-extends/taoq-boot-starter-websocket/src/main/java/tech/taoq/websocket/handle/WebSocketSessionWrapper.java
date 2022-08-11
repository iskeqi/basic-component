package tech.taoq.websocket.handle;

import org.springframework.web.socket.WebSocketSession;

/**
 * WebSocketSessionWrapper
 *
 * @author keqi
 */
public class WebSocketSessionWrapper {

    /**
     * websocket connection
     */
    private WebSocketSession webSocketSession;

    /**
     * 当前 websocket 连接订阅的是哪个主题
     */
    private String topic;

    /**
     * 当前连接属于哪个用户
     */
    private String userIdentifier;

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }
}
