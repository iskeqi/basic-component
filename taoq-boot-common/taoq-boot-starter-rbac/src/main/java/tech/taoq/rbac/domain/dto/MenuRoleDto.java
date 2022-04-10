package tech.taoq.rbac.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.rbac.domain.db.MenuDO;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuRoleDto extends MenuDO {

    @ApiModelProperty("是否占用[Y:占用 N:未占用]")
    private String occupy;

    public enum Occupy {
        Y, N
    }
}
