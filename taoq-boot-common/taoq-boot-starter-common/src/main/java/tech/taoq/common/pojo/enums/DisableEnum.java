package tech.taoq.common.pojo.enums;

/**
 * 启用/禁用枚举类
 *
 * @author keqi
 */
public enum DisableEnum {

    ENABLE("0", "启用"),
    DISABLE("1", "禁用");

    private final String code;
    private final String codeName;

    DisableEnum(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static DisableEnum parse(String code) {
        DisableEnum[] values = DisableEnum.values();
        for (DisableEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
