package tech.taoq.sso.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.mp.pojo.QueryBaseParam;
import tech.taoq.sso.domain.db.JobDO;
import tech.taoq.sso.mapper.JobMapper;

/**
 * 岗位管理
 *
 * @author keqi
 */
@Service
public class JobService {

	@Autowired
	private JobMapper jobMapper;

	@Transactional
	public void insert(JobDO param) {
		jobMapper.insert(param);
	}

	@Transactional
	public void deleteById(String id) {
		jobMapper.deleteById(id);
	}

	@Transactional
	public void updateById(JobDO param) {
		jobMapper.updateById(param);
	}

	public PageDto<JobDO> page(QueryBaseParam<JobDO> param) {
		Page<JobDO> page = jobMapper.selectPage(param,
				Wrappers.lambdaQuery(JobDO.class).orderByDesc(JobDO::getOrderNum));
		return new PageDto<>(page.getTotal(), page.getRecords());
	}
}
