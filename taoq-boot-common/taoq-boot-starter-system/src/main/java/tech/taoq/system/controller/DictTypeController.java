package tech.taoq.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.system.domain.DictTypeDO;
import tech.taoq.system.service.DictTypeService;

@Api(tags = "字典类型管理")
@RestController
@RequestMapping("/sys/dictType")
public class DictTypeController {

    @Autowired
    private DictTypeService dictTypeService;

    @ApiOperation("增加字典类型")
    @PostMapping
    public void insert(@Validated @RequestBody DictTypeDO param) {
        dictTypeService.insert(param);
    }

    @ApiOperation("删除字典类型")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        dictTypeService.deleteById(id);
    }

    @ApiOperation("修改字典类型")
    @PutMapping
    public void updateById(@Validated @RequestBody DictTypeDO param) {
        dictTypeService.updateById(param);
    }

    @ApiOperation("查询字典类型详情")
    @GetMapping("/{id}")
    public DictTypeDO getById(@PathVariable String id) {
        return dictTypeService.getById(id);
    }

    @ApiOperation("查询字典类型列表")
    @GetMapping
    public PageDto<DictTypeDO> page(PageParam<DictTypeDO> param) {
        return dictTypeService.page(param);
    }
}
