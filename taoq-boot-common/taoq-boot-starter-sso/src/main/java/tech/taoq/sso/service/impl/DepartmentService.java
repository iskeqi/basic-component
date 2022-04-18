package tech.taoq.sso.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.sso.domain.db.DepartmentDO;
import tech.taoq.sso.mapper.DepartmentMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class DepartmentService {

	private static final String ROOT_ID = "0";

	@Autowired
	private DepartmentMapper departmentMapper;

	@Transactional
	public void insert(DepartmentDO param) {
		if (param.getParent_id() == null) {
			param.setParent_id(ROOT_ID);
		}
		departmentMapper.insert(param);
	}

	@Transactional
	public void deleteById(String id) {
		Long count = departmentMapper.selectCount(Wrappers.query(new DepartmentDO().setParent_id(id)));
		if (count > 0) {
			throw new ParamIllegalException("当前部门下存在下属部门,不可直接被删除");
		}

		departmentMapper.deleteById(id);
	}

	@Transactional
	public void updateById(DepartmentDO param) {
		departmentMapper.updateById(param);
	}

	public List<DepartmentDO> listDepartment() {
		List<DepartmentDO> departmentDOList = departmentMapper.selectList(Wrappers.query());
		departmentDOList.sort(Comparator.comparing(DepartmentDO::getOrderNum));

		// 构造树形列表
		List<DepartmentDO> treeList = new ArrayList<>();
		for (DepartmentDO first : departmentDOList) {
			if (ROOT_ID.equals(first.getParent_id())) {
				treeList.add(first);
			}

			for (DepartmentDO t : departmentDOList) {
				if (Objects.equals(first.getId(), t.getParent_id())) {
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
