package com.sr.lsw.middle.domain.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CommandParam {

    @ApiModelProperty("订单id")
    @JsonProperty("order_id")
    private Integer orderId;

    @ApiModelProperty("车辆id")
    @JsonProperty("vehicle_id")
    private Integer vehicleId;

    @ApiModelProperty("车辆名称")
    @JsonProperty("vehicle_name")
    private String vehicleName;

    @ApiModelProperty("地图id")
    @JsonProperty("map_id")
    private Integer mapId;

    @ApiModelProperty("站点id")
    @JsonProperty("station_no")
    private Integer stationNo;

    @ApiModelProperty("站点名称")
    @JsonProperty("station_name")
    private String stationName;

    @ApiModelProperty("自定义动作指令")
    @JsonProperty("action")
    private int[] action;
}
