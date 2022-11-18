package tech.taoq.sso.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class LoginParam {

	@ApiModelProperty("用户名")
	private String account;

	@ApiModelProperty("密码")
	private String password;

	@ApiModelProperty("登录设备标识")
	private String device;
}
