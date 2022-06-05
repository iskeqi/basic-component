package tech.taoq.sso.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.mp.pojo.QueryBaseParam;
import tech.taoq.sso.domain.db.JobDO;
import tech.taoq.sso.service.JobService;

@Api(tags = "岗位管理")
@RestController
@RequestMapping("/sys/job")
public class JobController {

	@Autowired
	private JobService jobService;

	@ApiOperation("增加岗位")
	@PostMapping
	public void insert(@RequestBody JobDO param) {
		jobService.insert(param);
	}

	@ApiOperation("删除岗位")
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id) {
		jobService.deleteById(id);
	}

	@ApiOperation("修改岗位")
	@PutMapping
	public void updateById(@RequestBody JobDO param) {
		jobService.updateById(param);
	}

	@ApiOperation("分页查询岗位列表")
	@ApiOperationSupport(ignoreParameters = {
			"records", "total", "orders", "optimizeCountSql", "optimizeJoinOfCountSql", "hitCount",
			"pages", "countId", "maxLimit", "searchCount", "searchName", "orderFiled", "orderType",
			"searchValue", "beginDate", "endDate", "beginTime", "endTime"})
	@PostMapping("/page")
	public PageDto<JobDO> page(@RequestBody QueryBaseParam<JobDO> param) {
		return jobService.page(param);
	}
}
