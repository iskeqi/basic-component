package com.sr.lsw.communication.fms.domain.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class FmsResp<T> {

    public static final String MSG = "msg";

    @ApiModelProperty("响应编码")
    private Integer code;

    @ApiModelProperty("响应消息")
    private String msg;

    @ApiModelProperty("响应描述")
    private String description;

    @ApiModelProperty("请求是否成功[业务上的]")
    private Boolean success = true;

    @ApiModelProperty("实际响应数据")
    private T data;

    public boolean isSuccess() {
        return success;
    }
}
