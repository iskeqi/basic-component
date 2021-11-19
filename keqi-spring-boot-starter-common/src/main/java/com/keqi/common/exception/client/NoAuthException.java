package com.keqi.common.exception.client;

import com.keqi.common.exception.KeqiException;
import com.keqi.common.response.ResultStatusEnum;

/**
 * 未登录时，抛出此异常
 *
 * @author keqi
 */
public class NoAuthException extends KeqiException {

    private static final long serialVersionUID = -1172470366891102416L;

    public NoAuthException(String message) {
        super(ResultStatusEnum.NO_AUTH.getCode(), message);
    }
}
