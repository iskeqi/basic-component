package tech.taoq.rbac.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.rbac.domain.db.AccountRoleDO;
import tech.taoq.rbac.domain.db.MenuDO;
import tech.taoq.rbac.domain.db.RoleMenuDO;
import tech.taoq.rbac.domain.dto.MenuAccountDto;
import tech.taoq.rbac.domain.dto.MenuDto;
import tech.taoq.rbac.domain.dto.MenuRoleDto;
import tech.taoq.rbac.mapper.AccountRoleMapper;
import tech.taoq.rbac.mapper.MenuMapper;
import tech.taoq.rbac.mapper.RoleMapper;
import tech.taoq.rbac.mapper.RoleMenuMapper;
import tech.taoq.rbac.service.MenuService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    private static final String ROOT_ID = "0";

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private AccountRoleMapper accountRoleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuRoleDto> listByRoleId(String roleId) {
        // 查询指定角色关联的所有菜单
        List<RoleMenuDO> roleMenuDOList = roleMenuMapper.selectList(Wrappers.query(new RoleMenuDO().setRoleId(roleId)));
        Set<String> menuIdSet = roleMenuDOList.stream().map(RoleMenuDO::getMenuId).collect(Collectors.toSet());

        // 查询所有菜单，并设置是否被占用字段对应的值
        List<MenuDO> menuDOList = menuMapper.selectList(Wrappers.query());
        List<MenuRoleDto> nodeList = new ArrayList<>(menuDOList.size());
        for (MenuDO menuDO : menuDOList) {
            MenuRoleDto menuRoleDto = new MenuRoleDto();
            BeanUtils.copyProperties(menuDO, menuRoleDto);
            menuRoleDto.setOccupy(menuIdSet.contains(menuDO.getId()));
            nodeList.add(menuRoleDto);
        }

        // 升序排序
        nodeList.sort(Comparator.comparing(MenuDO::getOrderNum));

        // 构造树形结构列表
        List<MenuRoleDto> treeList = new ArrayList<>();
        for (MenuRoleDto first : nodeList) {
            if (ROOT_ID.equals(first.getParentId())) {
                treeList.add(first);
            }
            for (MenuRoleDto t : nodeList) {
                if (Objects.equals(t.getParentId(), first.getId())) {
                    if (first.getChildList() == null) {
                        first.setChildList(new ArrayList<>());
                    }
                    first.getChildList().add(t);
                }
            }
        }

        return treeList;
    }

    @Override
    public List<MenuAccountDto> listByAccountId(String accountId) {
        // 查询指定用户关联的所有菜单
        List<AccountRoleDO> accountRoleList = accountRoleMapper
                .selectList(Wrappers.query(new AccountRoleDO().setAccountId(accountId)));

        List<String> menuIdList = new ArrayList<>();
        for (AccountRoleDO t : accountRoleList) {
            List<RoleMenuDO> list = roleMenuMapper.selectList(Wrappers.query(new RoleMenuDO().setRoleId(t.getRoleId())));
            List<String> menuIds = list.stream().map(RoleMenuDO::getMenuId).collect(Collectors.toList());
            menuIdList.addAll(menuIds);
        }
        Set<String> menuIdSet = new HashSet<>(menuIdList);

        // 查询所有菜单，并设置是否被占用字段对应的值
        List<MenuDO> menuDOList = menuMapper.selectList(Wrappers.query());
        List<MenuAccountDto> nodeList = new ArrayList<>(menuDOList.size());
        for (MenuDO menuDO : menuDOList) {
            MenuAccountDto menuRoleDto = new MenuAccountDto();
            BeanUtils.copyProperties(menuDO, menuRoleDto);
            menuRoleDto.setOccupy(menuIdSet.contains(menuDO.getId()));
            nodeList.add(menuRoleDto);
        }

        // 升序排序
        nodeList.sort(Comparator.comparing(MenuDO::getOrderNum));

        // 构造树形结构列表
        List<MenuAccountDto> treeList = new ArrayList<>();
        for (MenuAccountDto first : nodeList) {
            if (ROOT_ID.equals(first.getParentId())) {
                treeList.add(first);
            }
            for (MenuAccountDto t : nodeList) {
                if (Objects.equals(t.getParentId(), first.getId())) {
                    if (first.getChildList() == null) {
                        first.setChildList(new ArrayList<>());
                    }
                    first.getChildList().add(t);
                }
            }
        }

        return treeList;
    }

    @Override
    public List<MenuDto> listMenus() {
        // 查询所有菜单列表
        List<MenuDO> menuDOList = menuMapper.selectList(Wrappers.query());
        menuDOList.sort(Comparator.comparing(MenuDO::getOrderNum));
        List<MenuDto> nodeList = new ArrayList<>(menuDOList.size());
        for (MenuDO menuDO : menuDOList) {
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(menuDO, menuDto);
            nodeList.add(menuDto);
        }

        // 构造树形结构列表
        List<MenuDto> treeList = new ArrayList<>();
        for (MenuDto first : nodeList) {
            if (ROOT_ID.equals(first.getParentId())) {
                treeList.add(first);
            }
            for (MenuDto t : nodeList) {
                if (Objects.equals(t.getParentId(), first.getId())) {
                    if (first.getChildList() == null) {
                        first.setChildList(new ArrayList<>());
                    }
                    first.getChildList().add(t);
                }
            }
        }

        return treeList;
    }
}
