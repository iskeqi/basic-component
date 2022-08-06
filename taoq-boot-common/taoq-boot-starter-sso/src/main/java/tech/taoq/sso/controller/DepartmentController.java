package tech.taoq.sso.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.sso.domain.db.DepartmentDO;
import tech.taoq.sso.service.DepartmentService;

import java.util.List;

@Api(tags = "部门管理")
@RestController
@RequestMapping("/sys/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@ApiOperation("增加部门")
	@PostMapping
	public void insert(@RequestBody DepartmentDO param) {
		departmentService.insert(param);
	}

	@ApiOperation("删除部门")
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id) {
		departmentService.deleteById(id);
	}

	@ApiOperation("修改部门")
	@PutMapping
	public void updateById(@RequestBody DepartmentDO param) {
		departmentService.updateById(param);
	}

	@ApiOperation("查询部门列表[树形结构]")
	@PostMapping("/listDepartment")
	public List<DepartmentDO> listDepartment() {
		return departmentService.listDepartment();
	}

}
