package com.jboa.service;

import com.jboa.common.Pager;
import com.jboa.entity.Employee;
import com.jboa.entity.dto.AbstractEmployee;
import com.jboa.entity.dto.DetailEmployee;

public interface EmployeeService {
	public Employee login (String employeename, String pwd) throws Exception ;
	public boolean deleteEmployee(int id) throws Exception;
	public DetailEmployee getEmployeeById(int id) throws Exception;
	public void addEmployee(Employee employee) throws Exception;
	public void updateEmployee(DetailEmployee employee) throws Exception;
	public Pager<AbstractEmployee> getEmployeesByPager(int currentPage,int pageSize,Employee condition) throws Exception;
	public boolean nameIsAvai (String empName) throws Exception;
	public void saveOrUpdate(DetailEmployee detailEmployee)  throws Exception;
	public void updateEmployee(Employee employee) throws Exception;
}
