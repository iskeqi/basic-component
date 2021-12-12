package com.keqi.websocket;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.keqi.common.exception.server.ServerErrorException;
import com.keqi.common.util.JsonUtil;
import com.keqi.websocket.handle.WebSocketMessageDto;
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
     * broadcast
     *
     * @param dto dto
     */
    public static void broadcast(WebSocketMessageDto dto) {
        for (WebSocketSessionWrapper wrapper : WRAPPER_SET) {
            if (Objects.equals(wrapper.getPage(), dto.getPage())) {
                send(wrapper, dto);
            }
        }
    }

    /**
     * send a message to the specified webSocketSessionId
     *
     * @param dto dto
     */
    public static void sendByWebSocketSessionId(String webSocketSessionId, WebSocketMessageDto dto) {
        for (WebSocketSessionWrapper wrapper : WRAPPER_SET) {
            if (wrapper.getWebSocketSession().getId().equals(webSocketSessionId)) {
                send(wrapper, dto);
            }
        }
    }

    /**
     * send a message to the specified page
     *
     * @param page
     * @param dto
     */
    public static void sendByPage(String page, WebSocketMessageDto dto) {
        for (WebSocketSessionWrapper wrapper : WRAPPER_SET) {
            send(wrapper, dto);
        }
    }

    private synchronized static void send(WebSocketSessionWrapper wrapper, WebSocketMessageDto dto) {
        WebSocketSession webSocketSession = wrapper.getWebSocketSession();
        if (webSocketSession.isOpen()) {
            boolean flag = false;
            String value = JsonUtil.writeValueAsString(dto);
            TextMessage textMessage = new TextMessage(value);
            try {
                webSocketSession.sendMessage(textMessage);
            } catch (IOException e) {
                flag = true;
                log.error("message send error, msg {}", value);
                log.error(e.getMessage(), e);
            }

            if (flag) {
                throw new ServerErrorException("message send error");
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
}
