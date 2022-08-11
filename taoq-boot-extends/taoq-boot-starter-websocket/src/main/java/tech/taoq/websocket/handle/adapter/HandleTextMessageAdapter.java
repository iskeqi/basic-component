package tech.taoq.websocket.handle.adapter;

import tech.taoq.websocket.handle.domain.WebSocketMessageDto;
import tech.taoq.websocket.handle.domain.WebSocketMessageParam;

/**
 * HandleTextMessageAdapter
 *
 * @author keqi
 */
public interface HandleTextMessageAdapter {

    /**
     * 获取 topic 和类型值，例如：topic-type
     *
     * @return r
     */
    String getTopicType();

    /**
     * 消息处理逻辑
     *
     * @param param param
     * @return r
     */
    WebSocketMessageDto handle(WebSocketMessageParam param);

    /**
     * 是否可以处理当前的消息类型
     *
     * @param page page
     * @param type type
     * @return r
     */
    default boolean supports(String page, String type) {
        return this.getTopicType().equals(this.concatTopicAndType(page, type));
    }

    /**
     * 提供统一的拼接方法
     *
     * @param topic topic
     * @param type type
     * @return r
     */
    default String concatTopicAndType(String topic, String type) {
        return topic + "-" + type;
    }
}
