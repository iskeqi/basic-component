package com.keqi.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocketUtil
 *
 * @author keqi
 */
@Slf4j
public class WebSocketUtil {

    /**
     * Session 与用户的映射
     */
    private static final Map<WebSocketSession, String> SESSION_USER_MAP = new ConcurrentHashMap<>();
    /**
     * 用户与 Session 的映射
     */
    private static final Map<String, WebSocketSession> USER_SESSION_MAP = new ConcurrentHashMap<>();

}
