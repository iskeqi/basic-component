package com.keqi.websocket;

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
     * @return Returns the unique identifier of the user, such as userId or username
     * return null means authentication failed
     */
    String auth(HttpServletRequest httpServletRequest);
}
