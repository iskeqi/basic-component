package com.keqi.websocket.handle.domain;

import java.util.Map;

/**
 * WebSocketMessageDto
 *
 * @author keqi
 */
public class WebSocketMessageDto extends WebSocketMessageParam {

    /**
     * response params
     */
    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
