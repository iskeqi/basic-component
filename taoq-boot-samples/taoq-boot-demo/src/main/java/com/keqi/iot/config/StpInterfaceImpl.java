package com.keqi.iot.config;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.taoq.rbac.domain.db.AccountRoleDO;
import tech.taoq.rbac.domain.db.RoleDO;
import tech.taoq.rbac.mapper.AccountRoleMapper;
import tech.taoq.rbac.mapper.RoleMapper;

import java.util.ArrayList;
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
        // 在此处根据token进行认证
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        list.add("101");
        list.add("user-add");
        list.add("user-delete");
        list.add("user-update");
        list.add("user-get");
        list.add("article-get");
        return list;
    }

    @Override
    public List<String> getRoleList(Object accountId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("super-admin");
        return list;
    }
}
