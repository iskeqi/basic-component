package tech.taoq.rbac.service;

import tech.taoq.rbac.domain.db.MenuDO;

import java.util.List;

public interface MenuService {

    List<MenuDO> listByRoleId(String roleId);
}
