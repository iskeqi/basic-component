package tech.taoq.modbus;

import tech.taoq.common.exception.third.ThirdServiceErrorException;

/**
 * ModbusTCPException
 *
 * @author keqi
 */
public class ModbusTCPException extends ThirdServiceErrorException {

    private static final long serialVersionUID = -3042686055658055555L;

    public ModbusTCPException(String message) {
        super(ModbusResultStatusEnum.MODBUS_ACCESS_ERROR.getCode(), message);
    }
}
