package com.keqi.websocket.handle;

import com.keqi.common.util.JsonUtil;
import com.keqi.websocket.WebSocketSessionWrapper;
import com.keqi.websocket.WebSocketUtil;
import com.keqi.websocket.auth.WebSocketInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

/**
 * WebSocketHandler
 *
 * @author keqi
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);

    @Autowired
    private List<HandleTextMessageAdapter> handleTextMessageAdapters;

    /**
     * open event
     *
     * @param webSocketSession webSocketSession
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        String userIdentifier = (String) webSocketSession.getAttributes().get(WebSocketInterceptor.USER_IDENTIFIER);
        WebSocketSessionWrapper wrapper = new WebSocketSessionWrapper();
        wrapper.setWebSocketSession(webSocketSession);
        wrapper.setUserIdentifier(userIdentifier);
        WebSocketUtil.afterConnectionEstablished(wrapper);
        log.info("websocket connection established successfully, userIdentifier is {}", userIdentifier);
    }

    /**
     * message event
     *
     * @param webSocketSession webSocketSession
     * @param textMessage      textMessage
     */
    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) {
        try {
            WebSocketMessageParam param = JsonUtil.readValue(textMessage.getPayload(), WebSocketMessageParam.class);
            // todo update page
            boolean flag = true;
            for (HandleTextMessageAdapter messageAdapter : handleTextMessageAdapters) {
                if (messageAdapter.supports(param.getPage(), param.getType())) {
                    flag = false;
                    WebSocketMessageDto dto = messageAdapter.handle(param);
                    WebSocketUtil.sendByWebSocketSessionId(webSocketSession.getId(), dto);
                }
            }

            if (flag) {
                log.info("no match messageAdapter, page {}, type {} ", param.getPage(), param.getType());
            }
        } catch (Throwable e) {
            log.error("an error occurred (message event), request params : {} ", "a", e);
        }
    }

    /**
     * close event
     *
     * @param webSocketSession webSocketSession
     * @param closeStatus      closeStatus
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        WebSocketUtil.afterConnectionClosed(webSocketSession.getId());
    }

    /**
     * error event
     *
     * @param webSocketSession webSocketSession
     * @param throwable        throwable
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {
        WebSocketUtil.handleTransportError(webSocketSession.getId());
        log.error("error event", throwable);
    }

}
