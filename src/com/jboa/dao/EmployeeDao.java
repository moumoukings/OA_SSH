package com.jboa.dao;

import java.util.Map;

import com.jboa.common.Pager;
import com.jboa.entity.Employee;
import com.jboa.exception.HibernateDaoSupportException;

public interface EmployeeDao extends BaseDao<Employee, Integer>{
	public Employee getEmployeeByEmployeeName(String employeename) throws Exception;
	public void deleteEmployee(int id) throws Exception;
	public Pager<Employee> getEmployeesByPager(int currentPage,int pageSize,Map<String,Object> params) throws Exception;
	public Employee login(String username, String pwd) throws HibernateDaoSupportException ;
	public void saveOrUpdate (Employee employee) throws Exception;
}