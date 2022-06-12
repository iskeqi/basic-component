package com.sr.lsw.communication.fms.domain.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class FmsOrder {

    @ApiModelProperty("上层系统订单唯一标识符[最大长度 64 个字符]")
    @JsonProperty("upper_id")
    private String upperId;

    @ApiModelProperty("订单优先级[0-255]，升序，默认优先级值为 10")
    private Integer priority = 10;

    @ApiModelProperty("指定车辆组id")
    @JsonProperty("vehicle_group")
    private Integer vehicleGroup;

    @ApiModelProperty("指定车辆id")
    @JsonProperty("appoint_vehicle_id")
    private Integer appointVehicleId;

    @ApiModelProperty("指定订单执行时间[yyyy-MM-dd HH:mm:ss]")
    @JsonProperty("appoint_execute_time")
    private String appointExecuteTime;

    @ApiModelProperty("子任务列表")
    private List<FmsOrderMission> mission;
}
