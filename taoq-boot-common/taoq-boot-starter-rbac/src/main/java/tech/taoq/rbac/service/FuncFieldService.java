package tech.taoq.rbac.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.rbac.constant.RbacConstant;
import tech.taoq.rbac.domain.db.FuncFieldDO;
import tech.taoq.rbac.domain.db.MenuDO;
import tech.taoq.rbac.mapper.FuncFieldDataMapper;
import tech.taoq.rbac.mapper.FuncFieldMapper;
import tech.taoq.rbac.mapper.MenuMapper;

import java.util.Comparator;
import java.util.List;

@Service
public class FuncFieldService {

    @Autowired
    private FuncFieldMapper funcFieldMapper;
    @Autowired
    private FuncFieldDataMapper funcFieldDataMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Transactional
    public void insert(FuncFieldDO param) {
        Long count = funcFieldMapper.selectCount(Wrappers.query(new FuncFieldDO().setMenuId(param.getMenuId())));
        MenuDO menuDO = menuMapper.selectById(param.getMenuId());
        if (count == 0) {
            // 创建表
            funcFieldDataMapper.createTable(menuDO.getPermiss(), menuDO.getName());
            // 新增3个字段
            FuncFieldDO id = new FuncFieldDO()
                    .setName(RbacConstant.ID)
                    .setCode(RbacConstant.ID)
                    .setMenuId(param.getMenuId())
                    .setType(FuncFieldDO.Type.NUMBER.name())
                    .setOrderNum(0);
            funcFieldMapper.insert(id);
            FuncFieldDO createTime = new FuncFieldDO()
                    .setName(RbacConstant.CREATE_TIME)
                    .setCode(RbacConstant.CREATE_TIME)
                    .setMenuId(param.getMenuId())
                    .setType(FuncFieldDO.Type.DATETIME.name())
                    .setOrderNum(Integer.MAX_VALUE - 2);
            funcFieldMapper.insert(createTime);
            FuncFieldDO updateTime = new FuncFieldDO()
                    .setName(RbacConstant.UPDATE_TIME)
                    .setCode(RbacConstant.UPDATE_TIME)
                    .setMenuId(param.getMenuId())
                    .setType(FuncFieldDO.Type.DATETIME.name())
                    .setOrderNum(Integer.MAX_VALUE - 1);
            funcFieldMapper.insert(updateTime);
        }

        // 新增字段
        param.setCode("v" + ++count);
        funcFieldDataMapper.addColumn(menuDO.getPermiss(), param.getCode(), param.getName());

        funcFieldMapper.insert(param);
    }

    @Transactional
    public void deleteById(String id) {
        FuncFieldDO funcFieldDO = funcFieldMapper.selectById(id);
        MenuDO menuDO = menuMapper.selectById(funcFieldDO.getMenuId());

        funcFieldMapper.deleteById(id);

        funcFieldDataMapper.dropColumn(menuDO.getPermiss(), funcFieldDO.getCode());
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
