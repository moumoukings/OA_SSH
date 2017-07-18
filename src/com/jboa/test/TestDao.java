package com.jboa.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.jboa.common.Pager;
import com.jboa.dao.LeaveDao;
import com.jboa.dao.RoleRightDao;
import com.jboa.entity.Leave;
import com.jboa.entity.Right;
import com.jboa.exception.DAOException;

public class TestDao extends BaseTest{
	@Test
	public void testRoleRightDaoImpl (){
		RoleRightDao roleRightDao = (RoleRightDao) super.getContext().getBean("roleRightDao");
		List<Right> list = null;
		try {
			list = roleRightDao.getRightsByRoleId(1);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		System.out.println(list.size());
	}
	@Test
	public void testMap(){
		Map<String, String> params = new HashMap<>();
		params.put("emp.id", "1");
		params.put("empId", "2");
		for (Entry<String, String> entry : params.entrySet()){
			if (entry.getKey().contains(".")){
				params.put(entry.getKey().replace('.', '_'), params.get(entry.getKey()));
				params.remove(entry.getKey());
			}
		}
		for (Entry<String, String> entry :params.entrySet()){
			System.out.println(entry.getKey() + params.get(entry.getKey()));
		}
	}
	@Test
	public void testAbstractLeave (){
		LeaveDao leaveDao = (LeaveDao) super.getContext().getBean("leaveDao");
		//String hql = "select count(*) from com.jboa.entity.Leave l left join l.leaveResult where 1=1  and leaveResult=null  and creator.role.id=:creator_role_id and state=:state and creator.department.deptId=:creator_department_deptId";
		Map<String, Object>params = new HashMap<>();
		params.put("leaveResult", null);
		try {
			Pager<Leave> leaves = leaveDao.list("select l from Leave l left join l.leaveResult where l.leaveResult = null", 1, 10, null);
			//Pager<Leave> leaves = leaveDao.list(1, 10, params);
			for (Leave leave: leaves.getPageRecords()){
				System.out.println(leave);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}