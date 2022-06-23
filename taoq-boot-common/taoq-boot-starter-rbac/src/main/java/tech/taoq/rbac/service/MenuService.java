package tech.taoq.rbac.service;

import tech.taoq.rbac.domain.dto.MenuDto;

import java.util.List;

public interface MenuService {

    List<MenuDto> listByRoleId(String roleId);

    List<MenuDto> listByAccount(String account);

    List<MenuDto> listMenus();
}
