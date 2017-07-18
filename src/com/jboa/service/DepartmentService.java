package com.jboa.service;

import java.util.List;

import com.jboa.entity.Department;


public interface DepartmentService {
	List<Department> list()throws Exception;
	/**
	 * 根据部门id获取部门实体
	 * @param deptId 部门ID
	 * @return 部门实体
	 * @throws Exception
	 */
	Department findById (Integer deptId) throws Exception;
}
