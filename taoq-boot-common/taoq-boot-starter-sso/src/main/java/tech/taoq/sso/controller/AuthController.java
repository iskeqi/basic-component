package tech.taoq.sso.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.sso.domain.dto.AuthenticateDto;
import tech.taoq.sso.domain.dto.LoginDto;
import tech.taoq.sso.domain.param.AuthenticateParam;
import tech.taoq.sso.domain.param.LoginParam;
import tech.taoq.sso.service.AuthService;

@Api(tags = "认证管理")
@RestController
@RequestMapping("/sys/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@ApiOperation("登录")
	@PostMapping("/login/p")
	public LoginDto login(@RequestBody LoginParam param) {
		return authService.login(param);
	}

	@ApiOperation("注销")
	@PostMapping("/logout")
	public void logout() {
		authService.logout();
	}

	@ApiOperation("根据token认证是否登录")
	@PostMapping("/authenticateByToken")
	public AuthenticateDto authenticateByToken(@RequestBody AuthenticateParam param) {
		return authService.authenticateByToken(param);
	}
}
