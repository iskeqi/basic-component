package tech.taoq.common.pojo.enums;

/**
 * @author keqi
 */
public enum DisableEnum {

    ENABLE(0, "启用"),
    DISABLE(1, "禁用");

    private final Integer code;
    private final String codeName;

    DisableEnum(Integer code, String codeName) {
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
