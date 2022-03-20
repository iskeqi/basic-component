package tech.taoq.mail.domain.enums;

import tech.taoq.common.pojo.enums.DisableEnum;

/**
 * @author keqi
 */
public enum ConnectEnum {

    NOT_CONNECT(0, "不可连接"),
    CONNECT(1, "连接");

    private final Integer code;
    private final String codeName;

    ConnectEnum(Integer code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public Integer getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static DisableEnum parse(Integer code) {
        DisableEnum[] values = DisableEnum.values();
        for (DisableEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
