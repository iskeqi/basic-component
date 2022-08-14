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

    SUCCESS("00000", "请求成功|Success|リクエスト成功|請求成功"),

    // 客户端错误
    CLINET_ERROR("A0001", "客户端错误|Client error|クライアントエラー|客戶端錯誤"),
    NO_AUTH("A0002", "token失效或错误|Token invalid or error|tokenの無効化またはエラー|token失效或錯誤"),
    PARAM_ILLEGAL("A0003", "参数错误|Parameter error|パラメータエラー|參數錯誤"),

    // 服务端错误
    SERVER_ERROR("B0001", "系统繁忙,请稍后重试|The system is busy, please try again later|システムがビジーです。後で再試行してください|系統繁忙，請稍後重試"),

    // 第三方服务错误
    THIRD_SERVICE_ERROR("C0001", "第三方系统错误|Third party system error|サードパーティ製システムエラー|協力廠商系統錯誤");

    private final String code;

    private final String codeName;

    @Override
    public String toString() {
        return code + IResultStatusEnum.DELIMITER + codeName;
    }
}
