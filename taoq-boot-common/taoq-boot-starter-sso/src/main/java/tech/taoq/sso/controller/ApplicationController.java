package tech.taoq.sso.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.sso.domain.db.ApplicationDO;
import tech.taoq.sso.service.ApplicationService;

@Api(tags = "应用管理")
@RestController
@RequestMapping("/sys/application")
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@ApiOperation("增加应用")
	@PostMapping
	public void insert(@RequestBody ApplicationDO param) {
		applicationService.insert(param);
	}

	@ApiOperation("删除应用")
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id) {
		applicationService.deleteById(id);
	}

	@ApiOperation("修改应用")
	@PutMapping
	public void updateById(@RequestBody ApplicationDO param) {
		applicationService.updateById(param);
	}

	@ApiOperation("查询应用列表")
	@GetMapping
	public PageDto<ApplicationDO> page(PageParam<ApplicationDO> param) {
		return applicationService.page(param);
	}

}
