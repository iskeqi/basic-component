package tech.taoq.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.taoq.common.pojo.IResultStatusEnum;

/**
 * 全局响应状态码
 *
 * @author keqi
 */
@Getter
@AllArgsConstructor
public enum ResultStatusEnum implements IResultStatusEnum {

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

    private final String code;

    private final String codeName;

    @Override
    public String toString() {
        return code + IResultStatusEnum.DELIMITER + codeName;
    }
}
