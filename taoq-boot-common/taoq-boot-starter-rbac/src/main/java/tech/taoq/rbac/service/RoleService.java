package tech.taoq.rbac.service;

import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.rbac.domain.db.RoleDO;
import tech.taoq.rbac.domain.param.AuthorizeMenuParam;
import tech.taoq.rbac.domain.param.AuthorizeRoleParam;

import java.util.List;

public interface RoleService {

    void insert(RoleDO param);

    void deleteById(String id);

    void updateById(RoleDO param);

    PageDto<RoleDO> page(PageParam<RoleDO> param);

    void authorizeMenu(AuthorizeMenuParam param);

    List<RoleDO> listByAccount(String account);

    void authorizeRole(AuthorizeRoleParam param);
}
