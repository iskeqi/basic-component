package tech.taoq.common.response;

/**
 * ResponseStatusEnum
 *
 * @author keqi
 */
public enum ResultStatusEnum {

    SUCCESS("00000", "success"),

    // 用户端错误

    // 客户端错误
    CLINET_ERROR("A0001", "client error"),
    // token 失效或错误
    NO_AUTH("A0002", "token invalid or error"),
    // 参数错误
    PARAM_ILLEGAL("A0003", "param error"),

    // 服务端错误
    // 系统繁忙，请稍后重试
    SERVER_ERROR("B0001", "system is busy, please try again later"),

    // 第三方服务错误
    THIRD_SERVICE_ERROR("C0001", "error in third party service");

    /**
     * 状态码
     */
    private final String code;

    /**
     * 状态码对应描述信息
     */
    private final String codeName;

    ResultStatusEnum(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

}
