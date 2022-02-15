package tech.taoq.websocket.handle;

import cn.hutool.core.collection.ConcurrentHashSet;
import tech.taoq.common.util.JsonUtil;
import tech.taoq.websocket.handle.adapter.HeartbeatMessageAdapter;
import tech.taoq.websocket.handle.domain.WebSocketMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

/**
 * WebSocketUtil
 *
 * @author keqi
 */
public class WebSocketUtil {

    private static final Logger log = LoggerFactory.getLogger(WebSocketUtil.class);

    public static final Set<WebSocketSessionWrapper> WRAPPER_SET = new ConcurrentHashSet<>();

    /**
     * 广播（包含全局消息和特定页面消息）
     *
     * @param dto dto
     */
    public void broadcast(WebSocketMessageDto dto) {
        WRAPPER_SET.forEach(wrapper -> send(wrapper, dto));
    }

    /**
     * 向指定用户发送消息
     *
     * @param userIdentifier userIdentifier
     * @param dto            dto
     */
    public void sendByUserIdentifier(String userIdentifier, WebSocketMessageDto dto) {
        WRAPPER_SET.forEach(wrapper -> {
            if (Objects.equals(wrapper.getUserIdentifier(), userIdentifier)) {
                send(wrapper, dto);
            }
        });
    }

    /**
     * 向指定的 webSocketSessionId 发送消息
     *
     * @param dto dto
     */
    static void sendByWebSocketSessionId(String webSocketSessionId, WebSocketMessageDto dto) {
        WRAPPER_SET.forEach(wrapper -> {
            if (wrapper.getWebSocketSession().getId().equals(webSocketSessionId)) {
                send(wrapper, dto);
            }
        });
    }

    private synchronized static void send(WebSocketSessionWrapper wrapper, WebSocketMessageDto dto) {
        // 全局消息，无论当前连接在哪个页面，都会发送
        // 其他消息只会发送到指定页面
        if (Objects.equals(wrapper.getPage(), dto.getPage()) ||
                HeartbeatMessageAdapter.GLOBAL.equals(dto.getPage())) {
            WebSocketSession webSocketSession = wrapper.getWebSocketSession();
            if (webSocketSession.isOpen()) {
                String value = JsonUtil.writeValueAsString(dto);
                TextMessage textMessage = new TextMessage(value);
                try {
                    webSocketSession.sendMessage(textMessage);
                } catch (IOException e) {
                    log.error("message send error, msg {}", value);
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    public synchronized static void afterConnectionEstablished(WebSocketSessionWrapper wrapper) {
        WRAPPER_SET.add(wrapper);
        log.info("number of established websocket connections currently {} ", WRAPPER_SET.size());
    }

    public synchronized static void afterConnectionClosed(String webSocketSessionId) {
        WRAPPER_SET.forEach(wrapper -> {
            if (Objects.equals(wrapper.getWebSocketSession().getId(), webSocketSessionId)) {
                WRAPPER_SET.remove(wrapper);
                log.info("remove connection (close event), userIdentifier : {} , webSocketSessionId : {}",
                        wrapper.getUserIdentifier(), webSocketSessionId);
            }
        });
        log.info("number of established websocket connections currently {} ", WRAPPER_SET.size());
    }

    public synchronized static void handleTransportError(String webSocketSessionId) {
        WRAPPER_SET.forEach(wrapper -> {
            if (Objects.equals(wrapper.getWebSocketSession().getId(), webSocketSessionId)) {
                WRAPPER_SET.remove(wrapper);
                log.info("remove connection (error event), userIdentifier : {} , webSocketSessionId : {}",
                        wrapper.getUserIdentifier(), webSocketSessionId);
            }
        });
        log.info("number of established websocket connections currently {} ", WRAPPER_SET.size());
    }

    public synchronized static void updatePageByWebSocketSessionId(String id, String page) {
        WRAPPER_SET.forEach(webSocketSessionWrapper -> {
            if (Objects.equals(id, webSocketSessionWrapper.getWebSocketSession().getId())) {
                webSocketSessionWrapper.setPage(page);
            }
        });
    }
}
