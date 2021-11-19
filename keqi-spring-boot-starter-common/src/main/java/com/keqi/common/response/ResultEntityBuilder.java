package com.keqi.common.response;

/**
 * 响应实体构建类
 *
 * @author keqi
 */
public class ResultEntityBuilder {

    /**
     * 单个对象
     *
     * @param data data
     * @return r 如果是一个空的List对象，那么 body 的值会是 []
     */
    public static ResultEntity<?> success(Object data) {
        return new ResultEntity<>(ResultStatusEnum.SUCCESS.getCode(),
                ResultStatusEnum.SUCCESS.getCodeName(), data);
    }

    /**
     * 没有返回值
     *
     * @return r
     */
    public static ResultEntity<?> success() {
        return new ResultEntity<>(ResultStatusEnum.SUCCESS.getCode(),
                ResultStatusEnum.SUCCESS.getCodeName(), null);
    }

    /**
     * 操作失败
     *
     * @return r
     */
    public static ResultEntity<?> failure() {
        return new ResultEntity<>(ResultStatusEnum.SERVER_ERROR.getCode(),
                ResultStatusEnum.SERVER_ERROR.getCodeName(), null);
    }

    /**
     * 操作失败
     *
     * @param message message
     * @return r
     */
    public static ResultEntity<?> failure(String message) {
        return new ResultEntity<>(ResultStatusEnum.SERVER_ERROR.getCode(), message, null);
    }

    /**
     * 操作失败
     *
     * @param status  status
     * @param message message
     * @return r
     */
    public static ResultEntity<?> failure(String status, String message) {
        return new ResultEntity<>(status, message, null);
    }

    /**
     * 操作失败
     *
     * @param status  status
     * @param message message
     * @param data    data
     * @return r
     */
    public static ResultEntity<?> failure(String status, String message, String data) {
        return new ResultEntity<>(status, message, data);
    }
}
