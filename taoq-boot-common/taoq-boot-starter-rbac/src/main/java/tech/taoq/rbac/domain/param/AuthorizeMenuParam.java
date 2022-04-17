package tech.taoq.rbac.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeMenuParam {

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("菜单id列表")
    private List<String> menuIdList;
}
