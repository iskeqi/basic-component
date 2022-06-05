package tech.taoq.sso.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.sso.domain.db.AccountDO;
import tech.taoq.sso.domain.param.AccountPageParam;
import tech.taoq.sso.domain.param.AccountParam;
import tech.taoq.sso.service.AccountService;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/sys/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@ApiOperation("增加用户")
	@PostMapping
	public void insert(@RequestBody AccountParam param) {
		accountService.insert(param);
	}

	@ApiOperation("删除用户")
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id) {
		accountService.deleteById(id);
	}

	@ApiOperation("修改用户")
	@PutMapping
	public void updateById(@RequestBody AccountParam param) {
		accountService.updateById(param);
	}

	@ApiOperation("分页查询用户列表")
	@GetMapping
	public PageDto<AccountDO> page(AccountPageParam param) {
		return accountService.page(param);
	}
}
