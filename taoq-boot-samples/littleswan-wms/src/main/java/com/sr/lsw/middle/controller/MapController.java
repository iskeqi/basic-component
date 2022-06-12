package com.sr.lsw.middle.controller;

import com.sr.lsw.communication.fms.FmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "地图管理")
@RestController
@RequestMapping("/api/v2/middle/map")
public class MapController {

    @Autowired
    private FmsService fmsService;

    @ApiOperation("查询地图列表")
    @GetMapping
    public Object mapList() {
        return fmsService.mapList();
    }

    @ApiOperation("查询地图详情")
    @GetMapping("/{id}")
    public Object mapDetail(@PathVariable String id) {
        return fmsService.mapDetail(id);
    }
}
