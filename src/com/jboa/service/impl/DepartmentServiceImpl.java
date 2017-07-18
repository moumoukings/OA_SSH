package com.jboa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jboa.dao.DepartmentDao;
import com.jboa.entity.Department;
import com.jboa.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentDao departmentDao;
	@Override
	public List<Department> list() throws Exception {
		return departmentDao.list();
	}
	@Override
	public Department findById(Integer deptId) throws Exception {
		return departmentDao.getObjectById(deptId);
	}

}
