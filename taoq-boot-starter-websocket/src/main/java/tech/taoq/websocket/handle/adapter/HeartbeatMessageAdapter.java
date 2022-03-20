package tech.taoq.websocket.handle.adapter;

import tech.taoq.websocket.handle.domain.WebSocketMessageDto;
import tech.taoq.websocket.handle.domain.WebSocketMessageParam;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * HeartbeatMessageAdapter
 *
 * @author keqi
 */
@Component
public class HeartbeatMessageAdapter implements HandleTextMessageAdapter {

    public static final String GLOBAL = "GLOBAL";
    public static final String HEARTBEAT = "HEARTBEAT";

    @Override
    public String getPageType() {
        return this.concatPageAndType(GLOBAL, HEARTBEAT);
    }

    @Override
    public WebSocketMessageDto handle(WebSocketMessageParam param) {
        return param.transfer(Collections.emptyMap());
    }
}
