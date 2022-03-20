package tech.taoq.websocket.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * WebSocketAuth
 *
 * @author keqi
 */
public interface WebSocketAuth {

    /**
     * 建立 websocket 连接之前的身份验证
     *
     * @param httpServletRequest httpServletRequest
     * @return r
     */
    WebSocketAuthDto auth(HttpServletRequest httpServletRequest);
}
