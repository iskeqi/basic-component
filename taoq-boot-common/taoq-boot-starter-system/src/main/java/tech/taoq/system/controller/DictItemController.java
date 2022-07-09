package tech.taoq.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.system.domain.db.DictItemDO;
import tech.taoq.system.service.DictItemService;

import java.util.List;

@Api(tags = "字典项管理")
@RestController
@RequestMapping("/sys/dict")
public class DictItemController {

    @Autowired
    private DictItemService dictItemService;

    @ApiOperation("增加字典项")
    @PostMapping
    public void insert(@Validated @RequestBody DictItemDO param) {
        dictItemService.insert(param);
    }

    @ApiOperation("删除字典项")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        dictItemService.deleteById(id);
    }

    @ApiOperation("修改字典项")
    @PutMapping
    public void updateById(@Validated @RequestBody DictItemDO param) {
        dictItemService.updateById(param);
    }

    @ApiOperation("查询字典项")
    @GetMapping("/{id}")
    public DictItemDO getById(@PathVariable String id) {
        return dictItemService.getById(id);
    }

    @ApiOperation("查询字典项列表")
    @GetMapping
    public PageDto<DictItemDO> page(PageParam<DictItemDO> param) {
        return dictItemService.page(param);
    }

    @ApiOperation("查询指定dictTypeId下所有的字典项")
    @PostMapping("/{dictTypeId}")
    public List<DictItemDO> listByDictTypeId(@PathVariable String dictTypeId) {
        return dictItemService.listByDictTypeId(dictTypeId);
    }
}
