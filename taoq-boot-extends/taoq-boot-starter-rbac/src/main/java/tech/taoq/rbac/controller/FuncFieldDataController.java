package tech.taoq.rbac.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.rbac.domain.param.FuncFieldDataPageParam;
import tech.taoq.rbac.domain.param.FuncFieldDataParam;
import tech.taoq.rbac.service.FuncFieldDataService;

import java.util.List;

@Api(tags = "功能字段数据管理")
@RestController
@RequestMapping("/sys/funcFieldData")
public class FuncFieldDataController {

    @Autowired
    private FuncFieldDataService funcFieldDataService;

    @ApiOperation("增加功能字段数据")
    @PostMapping
    public void insert(@RequestBody List<FuncFieldDataParam> param) {
        funcFieldDataService.insert(param);
    }

    @ApiOperation("删除功能字段数据")
    @DeleteMapping("/{menuId}/{id}")
    public void deleteById(@PathVariable String menuId, @PathVariable String id) {
        funcFieldDataService.deleteById(menuId, id);
    }

    @ApiOperation("修改功能字段数据")
    @PutMapping
    public void updateById(@RequestBody List<FuncFieldDataParam> param) {
        funcFieldDataService.updateById(param);
    }

    @ApiOperation("查询功能字段数据列表")
    @GetMapping
    public PageDto<List<FuncFieldDataParam>> page(FuncFieldDataPageParam param) {
        return funcFieldDataService.page(param);
    }
}
