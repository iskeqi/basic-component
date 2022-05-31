package com.keqi.iot.config;

import org.springframework.stereotype.Component;
import tech.taoq.websocket.auth.WebSocketAuth;
import tech.taoq.websocket.auth.WebSocketAuthDto;

import javax.servlet.http.HttpServletRequest;

/**
 * @author keqi
 */
@Component
public class WebSocketAuthImpl implements WebSocketAuth {

    @Override
    public WebSocketAuthDto auth(HttpServletRequest httpServletRequest) {
        String userIdentifier = httpServletRequest.getParameter("userIdentifier");

        return WebSocketAuthDto.build("keqi".equals(userIdentifier), userIdentifier);
    }
}
