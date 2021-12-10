package com.keqi.websocket.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocketHandler
 *
 * @author keqi
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);

    /**
     * open event
     *
     * @param webSocketSession webSocketSession
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {

    }

    /**
     * message event
     *
     * @param webSocketSession webSocketSession
     * @param textMessage      textMessage
     */
    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) {

    }

    /**
     * close event
     *
     * @param webSocketSession webSocketSession
     * @param closeStatus      closeStatus
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {

    }

    /**
     * error event
     *
     * @param webSocketSession webSocketSession
     * @param throwable        throwable
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {

    }

}
