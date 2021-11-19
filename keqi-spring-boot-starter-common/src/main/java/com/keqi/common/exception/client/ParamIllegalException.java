package com.keqi.common.exception.client;

import com.keqi.common.exception.KeqiException;
import com.keqi.common.response.ResultStatusEnum;

/**
 * 参数不合法时，抛出此异常
 *
 * @author keqi
 */
public class ParamIllegalException extends KeqiException {

    private static final long serialVersionUID = 3104400640505573074L;

    public ParamIllegalException(String message) {
        super(ResultStatusEnum.PARAM_ILLEGAL.getCode(), message);
    }
}
