package tech.taoq.rbac.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.rbac.domain.db.AccountRoleDO;
import tech.taoq.rbac.domain.db.RoleDO;
import tech.taoq.rbac.domain.db.RoleMenuDO;
import tech.taoq.rbac.domain.param.AuthorizeMenuParam;
import tech.taoq.rbac.mapper.AccountRoleMapper;
import tech.taoq.rbac.mapper.RoleMapper;
import tech.taoq.rbac.mapper.RoleMenuMapper;
import tech.taoq.rbac.service.RoleService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private AccountRoleMapper accountRoleMapper;

    @Override
    @Transactional
    public void insert(RoleDO param) {
        roleMapper.insert(param);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RoleDO roleDO = roleMapper.selectById(id);
        if (RoleDO.Type.N.name().equals(roleDO.getType())) {
            throw new ParamIllegalException("不允许删除内置角色");
        }

        // 删除角色记录
        roleMapper.deleteById(id);

        // 删除角色-菜单关联记录
        RoleMenuDO t1 = new RoleMenuDO();
        t1.setRoleId(id);
        roleMenuMapper.deleteById(t1);

        // 删除用户-角色关联记录
        AccountRoleDO t2 = new AccountRoleDO();
        t2.setRoleId(id);
        accountRoleMapper.deleteById(t2);
    }

    @Override
    @Transactional
    public void updateById(RoleDO param) {
        RoleDO roleDO = roleMapper.selectById(param.getId());
        if (RoleDO.Type.N.name().equals(roleDO.getType())) {
            throw new ParamIllegalException("不允许修改内置角色");
        }

        roleMapper.updateById(param);
    }

    @Override
    public PageDto<RoleDO> page(Page<RoleDO> param) {
        Page<RoleDO> page = roleMapper.selectPage(param, Wrappers.query());
        return new PageDto<>(page.getTotal(), page.getRecords());
    }

    @Override
    @Transactional
    public void authorizeMenu(AuthorizeMenuParam param) {
        for (String menuId : param.getMenuIdList()) {
            RoleMenuDO t1 = new RoleMenuDO().setRoleId(param.getRoleId()).setMenuId(menuId);
            roleMenuMapper.insert(t1);
        }
    }

    @Override
    public List<RoleDO> listByAccountId(String accountId) {
        List<AccountRoleDO> list = accountRoleMapper.selectList(
                Wrappers.query(new AccountRoleDO().setAccountId(accountId)));

        Collection<String> roleIdList = list.stream().map(AccountRoleDO::getRoleId).collect(Collectors.toList());

        return roleMapper.selectBatchIds(roleIdList);
    }
}
