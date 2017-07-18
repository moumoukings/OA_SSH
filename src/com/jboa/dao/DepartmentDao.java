package com.jboa.dao;

import java.util.List;

import com.jboa.entity.Department;

public interface DepartmentDao {
	List<Department> list()throws Exception;
	Department getObjectById (Integer deptId) throws Exception;
}
