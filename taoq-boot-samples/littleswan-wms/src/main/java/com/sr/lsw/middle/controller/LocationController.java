package com.sr.lsw.middle.controller;

import com.sr.lsw.middle.domain.db.LocationDO;
import com.sr.lsw.middle.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;

@Api(tags = "库位管理")
@RestController
@RequestMapping("/api/v2/middle/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @ApiOperation("增加库位")
    @PostMapping
    public void insert(@RequestBody LocationDO param) {
        locationService.insert(param);
    }

    @ApiOperation("删除库位")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        locationService.deleteById(id);
    }

    @ApiOperation("修改库位")
    @PutMapping
    public void updateById(@RequestBody LocationDO param) {
        locationService.updateById(param);
    }

    @ApiOperation("查询库位列表")
    @GetMapping
    public PageDto<LocationDO> page(PageParam<LocationDO> param) {
        return locationService.page(param);
    }
}
