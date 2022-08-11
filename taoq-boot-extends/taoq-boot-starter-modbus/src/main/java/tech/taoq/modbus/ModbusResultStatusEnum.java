package tech.taoq.modbus;

import tech.taoq.common.pojo.IResultStatusEnum;

/**
 * 全局响应状态码
 *
 * @author keqi
 */
public enum ModbusResultStatusEnum implements IResultStatusEnum {

    // Modbus 错误码
    MODBUS_ACCESS_ERROR("C0002", "modbus access error");

    private final String code;

    private final String codeName;

    ModbusResultStatusEnum(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    @Override
    public String toString() {
        return code + IResultStatusEnum.DELIMITER + codeName;
    }
}
