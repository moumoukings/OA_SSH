package com.jboa.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jboa.common.Pager;
import com.jboa.entity.Employee;
import com.jboa.entity.dto.AbstractEmployee;
import com.jboa.entity.dto.DetailEmployee;
import com.jboa.exception.HibernateDaoSupportException;
import com.jboa.service.EmployeeService;
import com.jboa.util.DtoEntityConvertUtil;

@Service("employeeService")
public class EmployeeServiceImpl extends BaseService implements EmployeeService{

	@Override
	public Employee login(String employeename, String pwd) throws HibernateDaoSupportException {
		log.debug("用户名为：" + employeename + "  密码为：" + pwd);
		return employeeDao.login(employeename, pwd);
	}
	@Override
	public boolean deleteEmployee(int id) throws Exception {
		employeeDao.deleteEmployee(id);
		return true;
	}

	@Override
	public DetailEmployee getEmployeeById(int id) throws Exception {
		return DtoEntityConvertUtil.toDetailEmloyee(employeeDao.findById(id, Employee.class));
	}

	@Override
	public void addEmployee(Employee employee) throws Exception {
		employeeDao.saveOrUpdate(employee);
	}

	@Override
	public void updateEmployee(DetailEmployee detailEmployee) throws Exception {
		Employee employee = employeeDao.findById(detailEmployee.getId(), Employee.class);
		if (detailEmployee.getAddress() != null)
			employee.setAddress(detailEmployee.getAddress());
		if (detailEmployee.getAge() != null)
			employee.setAge(detailEmployee.getAge());
		if (detailEmployee.getName() != null)
			employee.setName(detailEmployee.getName());
		if (detailEmployee.getPassword() != null)
			employee.setPassword(detailEmployee.getPassword());
		if(detailEmployee.getPhoneNum() != null)
			employee.setPhoneNum(detailEmployee.getPhoneNum());
		if (detailEmployee.getSex() != null)
			employee.setSex(detailEmployee.getSex());
		employeeDao.saveOrUpdate(employee);
	}

	@Override
	public Pager<AbstractEmployee> getEmployeesByPager(int currentPage, int pageSize,
			Employee condition) throws Exception {
		log.debug("开始查询，相应信息为：" + currentPage + "  " + pageSize);
		Map <String, Object> params = new HashMap<>();
		Pager <Employee> employees = employeeDao.getEmployeesByPager(currentPage, pageSize, params);
		log.debug("获取的集合长度为：" + employees.getPageRecords().size());
		Pager<AbstractEmployee> etemps = new Pager<>();
		etemps.setCurrentPage(employees.getCurrentPage());
		etemps.setPageSize(employees.getPageSize());
		etemps.setTotal(employees.getTotal());
		for (Employee temp : employees.getPageRecords()){
			etemps.getPageRecords().add(DtoEntityConvertUtil.toAbstractEmployee(temp));
		}
		return etemps;
	}
	@Override
	public boolean nameIsAvai(String empName) throws Exception {
		return employeeDao.getEmployeeByEmployeeName(empName) == null;
	}
	@Override
	public void saveOrUpdate(DetailEmployee detailEmployee) throws Exception {
		Employee employee;
		if (detailEmployee.getId() != null){
				employee = employeeDao.findById(detailEmployee.getId(), Employee.class);
			if (detailEmployee.getAddress() != null)
				employee.setAddress(detailEmployee.getAddress());
			if (detailEmployee.getAge() != null)
				employee.setAge(detailEmployee.getAge());
			if (detailEmployee.getName() != null)
				employee.setName(detailEmployee.getName());
			if (detailEmployee.getPassword() != null)
				employee.setPassword(detailEmployee.getPassword());
			if(detailEmployee.getPhoneNum() != null)
				employee.setPhoneNum(detailEmployee.getPhoneNum());
			if (detailEmployee.getSex() != null)
				employee.setSex(detailEmployee.getSex());
		}else {
			employee = DtoEntityConvertUtil.toEmployee(detailEmployee);
		}
		log.debug("待更新的数据为：" + employee.toString());
		employeeDao.saveOrUpdate(employee);
	}
	@Override
	public void updateEmployee(Employee employee) throws Exception {
		employeeDao.saveOrUpdate(employee);
	}
}
