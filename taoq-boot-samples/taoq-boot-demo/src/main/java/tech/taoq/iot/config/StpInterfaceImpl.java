package tech.taoq.iot.config;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.taoq.rbac.domain.db.AccountRoleDO;
import tech.taoq.rbac.domain.db.RoleDO;
import tech.taoq.rbac.mapper.AccountRoleMapper;
import tech.taoq.rbac.mapper.RoleMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private AccountRoleMapper accountRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> getPermissionList(Object accountId, String loginType) {
        // 返回用户对应角色对应的权限列表
        return null;
    }

    @Override
    public List<String> getRoleList(Object accountId, String loginType) {
        // 返回用户对应的角色权限标识列表
        List<AccountRoleDO> accountRoleDOList = accountRoleMapper.selectList(
                Wrappers.query(new AccountRoleDO().setAccountId(String.valueOf(accountId))));
        List<String> roleIdList = accountRoleDOList.stream().map(AccountRoleDO::getRoleId).collect(Collectors.toList());
        List<RoleDO> roleDOList = roleMapper.selectBatchIds(roleIdList);
        return roleDOList.stream().map(RoleDO::getPermiss).collect(Collectors.toList());
    }
}
