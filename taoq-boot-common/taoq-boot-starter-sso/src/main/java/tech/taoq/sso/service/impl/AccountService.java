package tech.taoq.sso.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.sso.domain.db.AccountDO;
import tech.taoq.sso.domain.db.AccountDepartmentDO;
import tech.taoq.sso.domain.db.AccountJobDO;
import tech.taoq.sso.domain.param.AccountPageParam;
import tech.taoq.sso.domain.param.AccountParam;
import tech.taoq.sso.mapper.AccountDepartmentMapper;
import tech.taoq.sso.mapper.AccountJobMapper;
import tech.taoq.sso.mapper.AccountMapper;

@Service
public class AccountService {

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountJobMapper accountJobMapper;
	@Autowired
	private AccountDepartmentMapper accountDepartmentMapper;
	@Autowired
	private AccountService accountService;

	@Transactional
	public void insert(AccountParam param) {
		accountMapper.insert(param);

		accountService.insertAssociations(param);
	}

	@Transactional
	public void deleteById(String id) {
		accountMapper.deleteById(id);

		accountService.deleteAssociations(id);
	}

	@Transactional
	public void updateById(AccountParam param) {
		accountService.deleteAssociations(param.getId());

		accountMapper.updateById(param);
		accountService.insertAssociations(param);
	}

	public PageDto<AccountDO> page(AccountPageParam param) {
		// todo 必须手写sql来完成某些需求
		Page<AccountDO> page = accountMapper.selectPage(param,
				Wrappers.query(new AccountDO().setAccount(param.getAccount())
						.setPhone(param.getPhone())
						.setDisable(param.getDisable())));
		return new PageDto<>(page.getTotal(), page.getRecords());
	}

	private void deleteAssociations(String id) {
		accountJobMapper.delete(Wrappers.query(new AccountJobDO().setAccountId(id)));
		accountDepartmentMapper.delete(Wrappers.query(new AccountDepartmentDO().setAccountId(id)));
	}

	private void insertAssociations(AccountParam param) {
		for (String jobId : param.getJobIdList()) {
			accountJobMapper.insert(new AccountJobDO().setAccountId(param.getId()).setJobId(jobId));
		}

		for (String departmentId : param.getDepartmentIdList()) {
			accountDepartmentMapper.insert(
					new AccountDepartmentDO().setAccountId(param.getId()).setDepartmentId(departmentId));
		}
	}
}
