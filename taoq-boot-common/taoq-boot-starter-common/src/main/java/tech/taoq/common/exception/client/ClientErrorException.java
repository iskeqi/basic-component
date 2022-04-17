package tech.taoq.common.exception.client;

import tech.taoq.common.exception.TaoqException;
import tech.taoq.common.response.ResultStatusEnum;

/**
 * 客户端错误时，抛出此异常
 *
 * @author keqi
 */
public class ClientErrorException extends TaoqException {

    private static final long serialVersionUID = -7776560122489518092L;

    /**
     * 无更细致错误码时使用
     *
     * @param message message
     */
    public ClientErrorException(String message) {
        super(ResultStatusEnum.CLINET_ERROR.getCode(), message);
    }

    public ClientErrorException(String status, String message) {
        super(status, message);
    }
}
