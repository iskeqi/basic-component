package tech.taoq.system.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.system.domain.db.ConfigDO;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ConfigPageParam extends PageParam<ConfigDO> {

    @ApiModelProperty("配置ID")
    private String id;

    @ApiModelProperty("配置key")
    private String configKey;
}
