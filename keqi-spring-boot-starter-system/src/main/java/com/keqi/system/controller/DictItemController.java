package com.keqi.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.keqi.common.pojo.PageDto;
import com.keqi.system.domain.db.ConfigDO;
import com.keqi.system.domain.db.DictItemDO;
import com.keqi.system.service.DictItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "字典管理")
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
    public void updateByConfigKey(@Validated @RequestBody ConfigDO param) {
        dictItemService.updateByConfigKey(param);
    }

    @ApiOperation("分页查询配置列表")
    @ApiOperationSupport(ignoreParameters = {
            "records", "total", "orders", "optimizeCountSql", "isSearchCount", "hitCount",
            "countId", "maxLimit", "searchCount", "searchName", "orderFiled", "orderType",
            "searchValue", "beginDate", "endDate", "beginTime", "endTime"})
    @GetMapping("/page")
    public PageDto<ConfigDO> page(Page<ConfigDO> param) {
        return configService.page(param);
    }

    @ApiOperation("查询指定typeCode对应的配置项")
    @GetMapping("/{typeCode}")
    public List<DictItemDO> listByTypeCode(@PathVariable String typeCode) {
        return dictItemService.listByTypeCode(typeCode);
    }
}
