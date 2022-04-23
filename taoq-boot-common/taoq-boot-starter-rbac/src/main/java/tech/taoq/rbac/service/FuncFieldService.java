package tech.taoq.rbac.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.rbac.domain.db.FuncFieldDO;
import tech.taoq.rbac.mapper.FuncFieldMapper;

import java.util.Comparator;
import java.util.List;

@Service
public class FuncFieldService {

    @Autowired
    private FuncFieldMapper funcFieldMapper;

    @Transactional
    public void insert(FuncFieldDO param) {
        FuncFieldDO funcFieldDO = funcFieldMapper.selectOne(
                Wrappers.query(new FuncFieldDO().setMenuId(param.getMenuId()).setCode(param.getCode())));
        if (funcFieldDO != null) {
            throw new ParamIllegalException("字段编码不得重复");
        }

        funcFieldMapper.insert(param);
    }

    @Transactional
    public void deleteById(String id) {
        funcFieldMapper.deleteById(id);
    }

    @Transactional
    public void updateById(FuncFieldDO param) {
        param.setCode(param.getCode());
        funcFieldMapper.updateById(param);
    }

    public PageDto<FuncFieldDO> page(Page<FuncFieldDO> param) {
        Page<FuncFieldDO> page = funcFieldMapper.selectPage(param, null);
        return new PageDto<>(page.getTotal(), page.getRecords());
    }

    public List<FuncFieldDO> listByMenuId(String menuId) {
        List<FuncFieldDO> funcFieldDOList = funcFieldMapper.selectList(
                Wrappers.query(new FuncFieldDO().setMenuId(menuId)));
        funcFieldDOList.sort(Comparator.comparing(FuncFieldDO::getOrderNum));
        return funcFieldDOList;
    }
}
