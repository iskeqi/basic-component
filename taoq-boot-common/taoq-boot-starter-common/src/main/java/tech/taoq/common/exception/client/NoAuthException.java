package tech.taoq.common.exception.client;

import tech.taoq.common.exception.TaoqException;
import tech.taoq.common.response.ResultStatusEnum;

/**
 * 未登录时，抛出此异常
 *
 * @author keqi
 */
public class NoAuthException extends TaoqException {

    private static final long serialVersionUID = -1172470366891102416L;

    public NoAuthException(String message) {
        super(ResultStatusEnum.NO_AUTH.getCode(), message);
    }
}
