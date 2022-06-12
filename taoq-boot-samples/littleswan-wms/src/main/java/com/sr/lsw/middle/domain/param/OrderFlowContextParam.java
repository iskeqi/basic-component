package com.sr.lsw.middle.domain.param;

import com.sr.lsw.communication.fms.domain.param.FmsOrder;
import com.sr.lsw.middle.service.OrderFlowService;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class OrderFlowContextParam {

    @ApiModelProperty("订单对象[起点和终点共用]")
    private FmsOrder fmsOrder;

    @ApiModelProperty("起点搬运类型")
    private OrderFlowService.FlowType startFlowType;

    @ApiModelProperty("起点参数对象")
    private Object startParam;

    @ApiModelProperty("起点库位id")
    private String startLocationId;

    @ApiModelProperty("终点搬运类型")
    private OrderFlowService.FlowType endFlowType;

    @ApiModelProperty("终点参数对象")
    private Object endParam;

    @ApiModelProperty("终点库位id")
    private String endLocationId;
}
