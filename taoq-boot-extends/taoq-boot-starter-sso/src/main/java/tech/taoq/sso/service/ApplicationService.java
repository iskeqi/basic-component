package tech.taoq.sso.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.sso.domain.db.ApplicationDO;
import tech.taoq.sso.mapper.ApplicationMapper;

/**
 * 应用管理
 *
 * @author keqi
 */
@Service
public class ApplicationService {

	@Autowired
	private ApplicationMapper applicationMapper;

	@Transactional
	public void insert(ApplicationDO param) {
		applicationMapper.insert(param);
	}

	@Transactional
	public void deleteById(String id) {
		applicationMapper.deleteById(id);
	}

	@Transactional
	public void updateById(ApplicationDO param) {
		applicationMapper.updateById(param);
	}

	public PageDto<ApplicationDO> page(PageParam<ApplicationDO> param) {
		Page<ApplicationDO> page = applicationMapper.selectPage(param.toPage(), Wrappers.query());
		return new PageDto<>(page.getTotal(), page.getRecords());
	}
}
