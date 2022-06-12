package com.sr.lsw.middle.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CreateOrderDto {

    @ApiModelProperty("订单id")
    private String orderId;
}
