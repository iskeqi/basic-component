package tech.taoq.rbac.service;

import tech.taoq.rbac.domain.dto.MenuAccountDto;
import tech.taoq.rbac.domain.dto.MenuDto;
import tech.taoq.rbac.domain.dto.MenuRoleDto;

import java.util.List;

public interface MenuService {

    List<MenuRoleDto> listByRoleId(String roleId);

    List<MenuAccountDto> listByAccountId(String accountId);

    List<MenuDto> listMenus();
}
