package tech.taoq.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.system.domain.db.DictItemDO;
import tech.taoq.system.service.DictItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "字典管理")
@ApiSupport(order = 1, author = "keqi")
@RestController
@RequestMapping("/sys/dict")
public class DictItemController {

    @Autowired
    private DictItemService dictItemService;

    @ApiOperation("新增字典")
    @PostMapping
    public DictItemDO insert(@Validated @RequestBody DictItemDO param) {
        return dictItemService.insert(param);
    }

    @ApiOperation(value = "删除字典", notes = "仅存在 typeCode 时，删除其对应的所有字典项，否则只删除对应的 itemCode")
    @DeleteMapping("/{typeCode}/{itemCode}")
    public void delete(@PathVariable String typeCode, @PathVariable(required = false) String itemCode) {
        dictItemService.delete(typeCode, itemCode);
    }

    @ApiOperation("修改字典")
    @PutMapping
    public void updateByTypeCodeAndItemCode(@Validated @RequestBody DictItemDO param) {
        dictItemService.updateByTypeCodeAndItemCode(param);
    }

    @ApiOperation("查询字典")
    @GetMapping
    public DictItemDO getByTypeCodeAndItemCode(String typeCode, String itemCode) {
        return dictItemService.getByTypeCodeAndItemCode(typeCode, itemCode);
    }

    @ApiOperation("分页查询配置列表")
    @ApiOperationSupport(ignoreParameters = {
            "records", "total", "orders", "optimizeCountSql", "isSearchCount", "hitCount",
            "countId", "maxLimit", "searchCount", "searchName", "orderFiled", "orderType",
            "searchValue", "beginDate", "endDate", "beginTime", "endTime", "pages"})
    @GetMapping("/page")
    public PageDto<DictItemDO> page(Page<DictItemDO> param) {
        return dictItemService.page(param);
    }

    @ApiOperation("查询指定typeCode对应的配置项")
    @GetMapping("/{typeCode}")
    public List<DictItemDO> listByTypeCode(@PathVariable String typeCode) {
        return dictItemService.listByTypeCode(typeCode);
    }

    @ApiOperation("修改指定typeCode对应的typeName")
    @PutMapping("/{typeCode}/{typeName}")
    public void updateTypeNameByTypeCode(@PathVariable String typeCode, @PathVariable String typeName) {
        dictItemService.updateTypeNameByTypeCode(typeCode, typeName);
    }
}
