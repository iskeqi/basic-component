package tech.taoq.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import tech.taoq.sso.domain.db.AccountDO;
import tech.taoq.sso.domain.param.AccountPageParam;

public interface AccountMapper extends BaseMapper<AccountDO> {

    Page<AccountDO> page(Page<AccountDO> toPage, @Param("param") AccountPageParam param);
}
