package tech.taoq.common.exception.server;

import tech.taoq.common.exception.TaoqException;
import tech.taoq.common.response.ResultStatusEnum;

/**
 * 服务端错误时，抛出此异常
 *
 * @author keqi
 */
public class ServerErrorException extends TaoqException {

    private static final long serialVersionUID = -1177625451890500114L;

    /**
     * 无更细致错误码时使用
     *
     * @param message message
     */
    public ServerErrorException(String message) {
        super(ResultStatusEnum.SERVER_ERROR.getCode(), message);
    }

    public ServerErrorException(String status, String message) {
        super(status, message);
    }
}
