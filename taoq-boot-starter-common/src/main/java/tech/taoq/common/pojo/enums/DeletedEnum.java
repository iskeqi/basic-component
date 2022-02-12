package tech.taoq.common.pojo.enums;

/**
 * 逻辑删除枚举类
 *
 * @author keqi
 */
public enum DeletedEnum {

    ENABLE("0", "未删除"),
    DISABLE("1", "已删除");

    private final String code;
    private final String codeName;

    DeletedEnum(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static DeletedEnum parse(String code) {
        DeletedEnum[] values = DeletedEnum.values();
        for (DeletedEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
