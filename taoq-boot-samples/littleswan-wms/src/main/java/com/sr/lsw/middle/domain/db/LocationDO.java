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
@TableName("middle_location")
public class LocationDO extends BaseDO {

    @ApiModelProperty("库位名称")
    private String name;

    @ApiModelProperty("库位编码")
    private String code;

    @ApiModelProperty("库位状态[OCCUPY:占用 IDLE:空闲 LOCK:锁定]")
    private String status;

    @ApiModelProperty("所属库区[由用户自定义填写]")
    private String scope;

    @ApiModelProperty("FMS地图id")
    private Integer fmsMapId;

    @ApiModelProperty("FMS站点id")
    private Integer fmsStationId;

    @ApiModelProperty("前置站点id")
    private Integer fmsPreStationId;

    @ApiModelProperty("起点搬运类型")
    private String startFlowType;

    @ApiModelProperty("终点搬运类型")
    private String endFlowType;

    public enum Status {
        OCCUPY, IDLE, LOCK
    }
}
