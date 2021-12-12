package com.keqi.websocket.handle;

/**
 * HandleTextMessageAdapter
 *
 * @author keqi
 */
public interface HandleTextMessageAdapter {

    /**
     * get page and type value, eg: page-type
     *
     * @return r
     */
    String getPageType();

    /**
     * Message processing logic
     *
     * @param param param
     * @return r
     */
    WebSocketMessageDto handle(WebSocketMessageParam param);

    /**
     * whether it can handle the current type of message
     *
     * @param page page
     * @param type type
     * @return r
     */
    default boolean supports(String page, String type) {
        return this.getPageType().equals(this.concatPageAndType(page, type));
    }

    /**
     * provide a unified splicing method
     *
     * @param page page
     * @param type type
     * @return r
     */
    default String concatPageAndType(String page, String type) {
        return page + "-" + type;
    }
}
