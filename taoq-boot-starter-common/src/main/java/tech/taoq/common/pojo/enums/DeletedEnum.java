package tech.taoq.common.pojo.enums;

/**
 * @author keqi
 */
public enum DeletedEnum {

    ENABLE(0, "未删除"),
    DISABLE(1, "已删除");

    private final Integer code;
    private final String codeName;

    DeletedEnum(Integer code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public Integer getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static DeletedEnum parse(Integer code) {
        DeletedEnum[] values = DeletedEnum.values();
        for (DeletedEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
