package tech.taoq.websocket.handle;

import tech.taoq.common.util.JsonUtil;
import tech.taoq.websocket.auth.WebSocketInterceptor;
import tech.taoq.websocket.handle.adapter.HandleTextMessageAdapter;
import tech.taoq.websocket.handle.adapter.HeartbeatMessageAdapter;
import tech.taoq.websocket.handle.domain.WebSocketMessageDto;
import tech.taoq.websocket.handle.domain.WebSocketMessageParam;
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
        WebSocketMessageParam param = null;
        boolean isReply = false;
        try {
            param = JsonUtil.readValue(textMessage.getPayload(), WebSocketMessageParam.class);
            log.info("receive message : {}", JsonUtil.writeValueAsString(param));

            // 更新当前连接所在的页面值
            if (!HeartbeatMessageAdapter.GLOBAL.equals(param.getPage())) {
                WebSocketUtil.updatePageByWebSocketSessionId(webSocketSession.getId(), param.getPage());
            }

            for (HandleTextMessageAdapter messageAdapter : handleTextMessageAdapters) {
                if (messageAdapter.supports(param.getPage(), param.getType())) {
                    WebSocketMessageDto dto = messageAdapter.handle(param);
                    WebSocketUtil.sendByWebSocketSessionId(webSocketSession.getId(), dto);
                    isReply = true;
                }
            }
            if (!isReply) {
                log.info("no reply, request id is {}", param.getRequestId());
            }
        } catch (Throwable e) {
            log.error("an error occurred (message event), request params : {} ", JsonUtil.writeValueAsString(param));
            log.error(e.getMessage(), e);
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
