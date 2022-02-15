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
     * 当前 websocket 连接处于哪个页面上
     */
    private String page;

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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }
}
