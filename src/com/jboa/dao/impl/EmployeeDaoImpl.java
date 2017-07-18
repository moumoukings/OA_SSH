package com.jboa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.jboa.common.Pager;
import com.jboa.dao.EmployeeDao;
import com.jboa.entity.Employee;
import com.jboa.exception.HibernateDaoSupportException;


@Repository("employeeDao")
public class EmployeeDaoImpl extends BaseDaoImpl<Employee, Integer> implements EmployeeDao{

	@SuppressWarnings("unchecked")
	@Override
	public Employee getEmployeeByEmployeeName(String Employeename) throws Exception{
		String hql = "from Employee where name=:name and state =1";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", Employeename);
		List<Employee> Employees = super.queryListByHql(hql, params);
		return Employees!=null && Employees.size()>0?Employees.get(0):null;
	}

	@Override
	public void deleteEmployee(int id) throws Exception {
		String hql = "update Employee set state=0 where empId="+id;
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override 
	public void saveOrUpdate(Employee employee) throws Exception {
		getHibernateTemplate().getSessionFactory().getCurrentSession().saveOrUpdate(employee);
		log.debug("更新成功");
	}
	
	@Override
	public Pager<Employee> getEmployeesByPager(int currentPage, int pageSize,
			Map<String,Object> params) throws Exception {
		StringBuilder hql=new StringBuilder(" from Employee where state=1 ");
		for (Entry<String, Object> entry : params.entrySet()){
			hql.append(" and " + entry.getKey() + " =:" + entry.getKey());
		}
		logger.info("组装的hql语句是：" + hql);
		return super.findPager(hql.toString(), currentPage, pageSize, params);
	}

	@Override
	public Employee login(String username, String pwd) throws HibernateDaoSupportException {
		String hql = "from Employee where name=:username and password=:pwd and state=1 ";
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("pwd", pwd);
		return (Employee) super.getUniqueBeanResult(hql, params);
	}	
		
}
