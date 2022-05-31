package com.keqi.iot.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@TableName("zdy_callback_param")
public class CallbackParamDO {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("回调类型")
    private String type;

    @ApiModelProperty("回调参数JSON字符串")
    private String callbackParam;
}
