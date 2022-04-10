package tech.taoq.rbac.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.rbac.domain.db.RoleDO;
import tech.taoq.rbac.domain.param.AuthorizeMenuParam;
import tech.taoq.rbac.service.RoleService;

import java.util.List;

/**
 * RoleController
 *
 * @author keqi
 */
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

    @ApiOperation("分页查询角色列表")
    @GetMapping("/page")
    public PageDto<RoleDO> page(Page<RoleDO> param) {
        return roleService.page(param);
    }

    @ApiOperation("给指定角色赋予菜单权限")
    @PostMapping("/authorizeMenu")
    public void authorizeMenu(@RequestBody AuthorizeMenuParam param) {
        roleService.authorizeMenu(param);
    }

    @ApiOperation("查询指定用户关联的角色")
    @PostMapping("/listByAccountId/{accountId}")
    public List<RoleDO> listByAccountId(@PathVariable String accountId) {
        return roleService.listByAccountId(accountId);
    }
}
