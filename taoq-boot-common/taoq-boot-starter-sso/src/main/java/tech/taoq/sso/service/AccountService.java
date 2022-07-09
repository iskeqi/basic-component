package tech.taoq.sso.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.sso.domain.db.AccountDO;
import tech.taoq.sso.domain.db.AccountDepartmentDO;
import tech.taoq.sso.domain.db.AccountJobDO;
import tech.taoq.sso.domain.param.AccountPageParam;
import tech.taoq.sso.domain.param.AccountParam;
import tech.taoq.sso.domain.param.ResetPasswordParam;
import tech.taoq.sso.mapper.AccountDepartmentMapper;
import tech.taoq.sso.mapper.AccountJobMapper;
import tech.taoq.sso.mapper.AccountMapper;

/**
 * 用户管理
 *
 * @author keqi
 */
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

    private static final String DEFAULT_PASSWORD = "123456";
    private static final String ADMIN = "admin";

    @Transactional
    public void insert(AccountParam param) {
        Long count = accountMapper.selectCount(Wrappers.query(new AccountDO().setAccount(param.getAccount())));
        if (count > 0) {
            throw new ParamIllegalException("用户名不得重复");
        }

        accountMapper.insert(param);

        accountService.insertAssociations(param);
    }

    @Transactional
    public void deleteById(String id) {
        AccountDO accountDO = accountMapper.selectById(id);
        if (ADMIN.equals(accountDO.getAccount())) {
            throw new ParamIllegalException("admin 账号不允许被删除");
        }

        accountMapper.deleteById(id);

        accountService.deleteAssociations(id);
    }

    @Transactional
    public void updateById(AccountParam param) {
        accountService.deleteAssociations(param.getId());

        param.setAccount(null);
        accountMapper.updateById(param);

        accountService.insertAssociations(param);
    }

    public PageDto<AccountDO> page(AccountPageParam param) {
        String account = (String) StpUtil.getLoginId();
        if (ADMIN.equals(account)) {
            param.setShowAdmin(true);
        }

        Page<AccountDO> page = accountMapper.page(param.toPage(), param);
        return new PageDto<>(page.getTotal(), page.getRecords());
    }

    public void deleteAssociations(String id) {
        accountJobMapper.delete(Wrappers.query(new AccountJobDO().setAccountId(id)));
        accountDepartmentMapper.delete(Wrappers.query(new AccountDepartmentDO().setAccountId(id)));
    }

    public void insertAssociations(AccountParam param) {
        if (param.getJobIdList() != null) {
            for (String jobId : param.getJobIdList()) {
                accountJobMapper.insert(new AccountJobDO().setAccountId(param.getId()).setJobId(jobId));
            }
        }
        if (param.getDepartmentIdList() != null) {
            for (String departmentId : param.getDepartmentIdList()) {
                accountDepartmentMapper.insert(new AccountDepartmentDO().setAccountId(param.getId()).setDepartmentId(departmentId));
            }
        }
    }

    public void resetPassword(ResetPasswordParam param) {
        AccountDO accountDO = new AccountDO();
        accountDO.setId(param.getId());
        accountDO.setPassword(DEFAULT_PASSWORD);
        accountMapper.updateById(accountDO);
    }
}
