package tech.taoq.rbac.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.rbac.domain.db.RoleDO;
import tech.taoq.rbac.domain.param.AuthorizeMenuParam;
import tech.taoq.rbac.domain.param.AuthorizeRoleParam;
import tech.taoq.rbac.service.RoleService;

import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("增加角色")
    @PostMapping
    public void insert(@RequestBody RoleDO param) {
        roleService.insert(param);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        roleService.deleteById(id);
    }

    @ApiOperation("修改角色")
    @PutMapping
    public void updateById(@RequestBody RoleDO param) {
        roleService.updateById(param);
    }

    @ApiOperation("查询角色列表")
    @GetMapping
    public PageDto<RoleDO> page(PageParam<RoleDO> param) {
        return roleService.page(param);
    }

    @ApiOperation("给指定角色赋予菜单权限")
    @PostMapping("/authorizeMenu")
    public void authorizeMenu(@RequestBody AuthorizeMenuParam param) {
        roleService.authorizeMenu(param);
    }

    @ApiOperation("给指定用户赋予角色")
    @PostMapping("/authorizeRole")
    public void authorizeRole(@RequestBody AuthorizeRoleParam param) {
        roleService.authorizeRole(param);
    }

    @ApiOperation("查询指定用户关联的角色")
    @PostMapping("/listByAccount/{account}")
    public List<RoleDO> listByAccount(@PathVariable String account) {
        return roleService.listByAccount(account);
    }
}
