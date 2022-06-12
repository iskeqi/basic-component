package com.sr.lsw.middle.domain.db;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.BaseDO;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("middle_callback_param")
public class CallbackParamDO extends BaseDO {

    @ApiModelProperty("回调类型")
    private String type;

    @ApiModelProperty("回调参数JSON字符串")
    private String callbackParam;
}
