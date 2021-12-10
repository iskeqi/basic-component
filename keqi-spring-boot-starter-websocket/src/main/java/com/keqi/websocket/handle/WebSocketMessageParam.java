package com.keqi.websocket.handle;

import lombok.Data;

import java.util.Map;

/**
 * WebSocketMessageParam
 *
 * @author keqi
 */
@Data
public class WebSocketMessageParam {

    /**
     * which page is the current websocket connection
     */
    protected String page;

    /**
     * The type of push message in the specified page in the current WebSocket connection
     */
    protected String type;

    /**
     * request unique id
     */
    protected String requestId;

    /**
     * request params
     */
    protected Map<String, Object> params;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
