package tech.taoq.websocket.handle.domain;

import java.util.Map;

/**
 * WebSocketMessageParam
 *
 * @author keqi
 */
public class WebSocketMessageParam {

    /**
     * 当前 websocket 连接代表哪个页面
     */
    protected String page;

    /**
     * 当前WebSocket连接中指定页面的推送消息类型
     */
    protected String type;

    /**
     * 请求唯一 ID
     */
    protected String requestId;

    /**
     * request param
     */
    protected Map<String, Object> params;

    public WebSocketMessageDto transfer(Map<String, Object> data) {
        WebSocketMessageDto r = new WebSocketMessageDto();
        r.setPage(this.getPage());
        r.setType(this.getType());
        r.setRequestId(this.getRequestId());
        r.setParams(this.getParams());
        r.setData(data);
        return r;
    }

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
