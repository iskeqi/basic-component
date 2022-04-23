package tech.taoq.rbac.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.rbac.domain.db.FuncFieldDO;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FuncFieldDataParam extends FuncFieldDO {

    @ApiModelProperty("字段值")
    private String dataValue;
}
