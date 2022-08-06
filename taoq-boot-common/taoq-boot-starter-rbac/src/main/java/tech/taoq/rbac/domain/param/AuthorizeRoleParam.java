package tech.taoq.rbac.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class AuthorizeRoleParam {

    @ApiModelProperty("用户名")
    private String account;

    @ApiModelProperty("角色id列表")
    private List<String> roleIdList;
}
