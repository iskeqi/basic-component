package tech.taoq.mail.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mail.domain.db.MailDO;
import tech.taoq.mail.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 邮箱服务管理
 *
 * @author keqi
 */
@Api(tags = "邮箱服务管理")
@RestController
@RequestMapping("/sys/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @ApiOperation("新增邮箱服务")
    @PostMapping
    public MailDO insert(@Validated @RequestBody MailDO param) {
        return mailService.insert(param);
    }

    @ApiOperation("删除邮箱服务")
    @DeleteMapping("/{identifier}")
    public void deleteByIdentifier(@PathVariable String identifier) {
        mailService.deleteByIdentifier(identifier);
    }

    @ApiOperation("修改邮箱服务")
    @PutMapping
    public void updateByidentifier(@Validated @RequestBody MailDO param) {
        mailService.updateByIdentifier(param);
    }

    @ApiOperation("查询邮箱服务详情")
    @GetMapping
    public MailDO getByIdentifier(String identifier) {
        return mailService.getByIdentifier(identifier);
    }

    @ApiOperation("分页查询邮箱服务列表")
    @ApiOperationSupport(ignoreParameters = {
            "records", "total", "orders", "optimizeCountSql", "isSearchCount", "hitCount",
            "countId", "maxLimit", "searchCount", "searchName", "orderFiled", "orderType",
            "searchValue", "beginDate", "endDate", "beginTime", "endTime"})
    @GetMapping("/page")
    public PageDto<MailDO> page(Page<MailDO> param) {
        return mailService.page(param);
    }

    @ApiOperation("测试是否可连接")
    @PostMapping("/isConnect")
    public MailDO isConnect(String identifier) {
        return mailService.isConnect(identifier);
    }
}
