package com.keqi.common.exception.server;

import com.keqi.common.exception.KeqiException;
import com.keqi.common.response.ResultStatusEnum;

/**
 * 服务端错误时，抛出此异常
 *
 * @author keqi
 */
public class ServerErrorException extends KeqiException {

    private static final long serialVersionUID = -1177625451890500114L;

    public ServerErrorException(String message) {
        super(ResultStatusEnum.SERVER_ERROR.getCode(), message);
    }

    /**
     * 无更细致错误码时使用
     *
     * @param message message
     */
    public ServerErrorException(String status, String message) {
        super(status, message);
    }
}
