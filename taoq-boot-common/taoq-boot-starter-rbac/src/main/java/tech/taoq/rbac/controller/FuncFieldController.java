package tech.taoq.rbac.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.rbac.domain.db.FuncFieldDO;
import tech.taoq.rbac.service.FuncFieldService;

import java.util.List;

@Api(tags = "功能字段管理")
@RestController
@RequestMapping("/sys/funcField")
public class FuncFieldController {

    @Autowired
    private FuncFieldService funcFieldService;

    @ApiOperation("增加功能字段")
    @PostMapping
    public void insert(@RequestBody FuncFieldDO param) {
        funcFieldService.insert(param);
    }

    @ApiOperation("删除功能字段")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        funcFieldService.deleteById(id);
    }

    @ApiOperation("修改功能字段")
    @PutMapping
    public void updateById(@RequestBody FuncFieldDO param) {
        funcFieldService.updateById(param);
    }

    @ApiOperation("查询功能字段列表")
    @GetMapping
    public PageDto<FuncFieldDO> page(PageParam<FuncFieldDO> param) {
        return funcFieldService.page(param);
    }

    @ApiOperation("查询指定菜单id下的所有功能字段")
    @PostMapping("/listByMenuId/{MenuId}")
    public List<FuncFieldDO> listByMenuId(@PathVariable String MenuId) {
        return funcFieldService.listByMenuId(MenuId);
    }
}
