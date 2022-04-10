package tech.taoq.rbac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.rbac.domain.db.RoleDO;
import tech.taoq.rbac.domain.param.AuthorizeMenuParam;

import java.util.List;

public interface RoleService {

    void insert(RoleDO param);

    void deleteById(String id);

    void updateById(RoleDO param);

    PageDto<RoleDO> page(Page<RoleDO> param);

    void authorizeMenu(AuthorizeMenuParam param);

    List<RoleDO> listByAccountId(String accountId);
}
