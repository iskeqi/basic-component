package tech.taoq.websocket.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * WebSocketInterceptor
 *
 * @author keqi
 */
@Component
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    private static final Logger log = LoggerFactory.getLogger(WebSocketInterceptor.class);

    @Autowired(required = false)
    private WebSocketAuth webSocketAuth;

    public static final String USER_IDENTIFIER = "userIdentifier";

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) request).getServletRequest();

        if (webSocketAuth == null) {
            log.error("No implementation class found for WebSocketAuth");
            return false;
        }

        // 认证
        WebSocketAuthDto webSocketAuthDto = webSocketAuth.auth(httpServletRequest);
        if (!webSocketAuthDto.getAuthenticate()) {
            StringBuffer requestURL = httpServletRequest.getRequestURL().append('?');
            String queryString = httpServletRequest.getQueryString();
            log.error("websocket authentication failed, request params : {}", requestURL.append(queryString));
            return false;
        }

        attributes.put(USER_IDENTIFIER, webSocketAuthDto.getUserIdentifier());
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

}
