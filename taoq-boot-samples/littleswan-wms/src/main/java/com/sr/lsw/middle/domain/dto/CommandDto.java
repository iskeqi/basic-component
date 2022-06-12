package com.sr.lsw.middle.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CommandDto {

    @ApiModelProperty("响应编码")
    private Integer code;

    @ApiModelProperty("响应描述")
    private String msg;

    @ApiModelProperty("响应数据")
    private Object data;

    /**
     * 成功响应
     *
     * @return r
     */
    public static CommandDto success() {
        return new CommandDto().setCode(200)
                .setMsg("success")
                .setData(null);
    }

    /**
     * 失败响应
     *
     * @return r
     */
    public static CommandDto received() {
        return new CommandDto().setCode(201)
                .setMsg("received")
                .setData(null);
    }
}
