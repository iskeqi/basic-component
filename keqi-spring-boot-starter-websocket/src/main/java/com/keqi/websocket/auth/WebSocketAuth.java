package com.keqi.websocket.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * WebSocketAuth
 *
 * @author keqi
 */
public interface WebSocketAuth {

    /**
     * Authentication before websocket connection is established
     *
     * @param httpServletRequest httpServletRequest
     * @return r
     */
    WebSocketAuthDto auth(HttpServletRequest httpServletRequest);
}
