package tech.taoq.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.rbac.domain.db.MenuDO;
import tech.taoq.rbac.mapper.AccountRoleMapper;
import tech.taoq.rbac.mapper.RoleMapper;
import tech.taoq.rbac.mapper.RoleMenuMapper;
import tech.taoq.rbac.service.MenuService;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private AccountRoleMapper accountRoleMapper;

    @Override
    public List<MenuDO> listByRoleId(String roleId) {




        return null;
    }
}
