package tech.taoq.sso.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.sso.domain.db.AccountDO;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountPageParam extends PageParam<AccountDO> {

	@ApiModelProperty("用户名")
	private String account;

	@ApiModelProperty("用户昵称")
	private String name;

	@ApiModelProperty("手机号码")
	private String phone;

	@ApiModelProperty("是否禁用[false:未禁用 true:已禁用]")
	private Boolean disable;

	@ApiModelProperty("部门id")
	private String departmentId;

	@ApiModelProperty(value = "是否展示 admin 账号", hidden = true)
	private Boolean showAdmin = false;
}
