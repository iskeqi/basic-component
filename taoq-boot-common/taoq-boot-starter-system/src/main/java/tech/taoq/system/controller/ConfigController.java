package tech.taoq.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.system.domain.db.ConfigDO;
import tech.taoq.system.domain.param.ConfigPageParam;
import tech.taoq.system.service.ConfigService;

@Api(tags = "配置管理")
@RestController
@RequestMapping("/sys/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @ApiOperation("增加配置")
    @PostMapping
    public void insert(@Validated @RequestBody ConfigDO param) {
        configService.insert(param);
    }

    @ApiOperation("删除配置")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        configService.deleteById(id);
    }

    @ApiOperation("修改配置")
    @PutMapping
    public void updateById(@Validated @RequestBody ConfigDO param) {
        configService.updateById(param);
    }

    @ApiOperation("查询配置详情")
    @GetMapping("/{id}")
    public ConfigDO getById(@PathVariable String id) {
        return configService.getById(id);
    }

    @ApiOperation("查询配置列表")
    @GetMapping
    public PageDto<ConfigDO> page(ConfigPageParam param) {
        return configService.page(param);
    }
}
