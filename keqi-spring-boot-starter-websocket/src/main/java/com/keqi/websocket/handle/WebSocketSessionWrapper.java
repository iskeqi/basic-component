package com.keqi.websocket.handle;

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
     * which page is the current websocket connection
     */
    private String page;

    /**
     * which user the current connection belongs to
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
