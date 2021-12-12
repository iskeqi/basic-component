package com.keqi.websocket.handle;

import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * HeartbeatMessageAdapter
 *
 * @author keqi
 */
@Component
public class HeartbeatMessageAdapter implements HandleTextMessageAdapter {

    private static final String PAGE = "COMMON";
    private static final String TYPE = "HEARTBEAT";

    @Override
    public String getPageType() {
        return this.concatPageAndType(PAGE, TYPE);
    }

    @Override
    public WebSocketMessageDto handle(WebSocketMessageParam param) {
        return param.transfer(Collections.emptyMap());
    }
}
