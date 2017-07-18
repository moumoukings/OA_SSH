package com.jboa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jboa.dao.DepartmentDao;
import com.jboa.entity.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department, Integer> implements DepartmentDao{

	@Override
	public List<Department> list() throws Exception {
		return super.getObjects("from Department");
	}

	@Override
	public Department getObjectById(Integer deptId) throws Exception {
		return super.findById(deptId, Department.class);
	}
}
