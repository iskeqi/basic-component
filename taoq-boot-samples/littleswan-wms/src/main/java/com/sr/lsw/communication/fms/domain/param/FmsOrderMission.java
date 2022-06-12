package com.sr.lsw.communication.fms.domain.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class FmsOrderMission {

    @ApiModelProperty("子任务类型")
    private String type;

    @ApiModelProperty("地图id")
    @JsonProperty("map_id")
    private Integer mapId;

    @ApiModelProperty("目的站点id")
    private Integer destination;

    @ApiModelProperty("动作id")
    @JsonProperty("action_id")
    private Integer actionId;

    @ApiModelProperty("动作参数1")
    @JsonProperty("action_param1")
    private Integer actionParam1;

    @ApiModelProperty("动作参数2")
    @JsonProperty("action_param2")
    private Integer actionParam2;

    public enum Type {
        MOVE, ACT
    }

    /**
     * 创建移动子任务对象
     *
     * @param mapId       地图id
     * @param destination 站点id
     * @return r
     */
    public static FmsOrderMission createMoveMission(Integer mapId, Integer destination) {
        return new FmsOrderMission().setType(Type.MOVE.name().toLowerCase())
                .setMapId(mapId).setDestination(destination);
    }

    /**
     * 创建动作子任务对象
     *
     * @param action 动作指令三元组
     * @return r
     */
    public static FmsOrderMission createActMission(int[] action) {
        return new FmsOrderMission().setType(Type.ACT.name().toLowerCase())
                .setActionId(action[0]).setActionParam1(action[1]).setActionParam2(action[2]);
    }
}
