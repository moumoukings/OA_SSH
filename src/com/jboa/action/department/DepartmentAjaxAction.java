package com.jboa.action.department;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.action.BaseAction;
import com.jboa.entity.Department;
import com.jboa.entity.Employee;

@Controller("departmentAjaxAction")
@Scope("prototype")
public class DepartmentAjaxAction extends BaseAction {

	private static final long serialVersionUID = 4515372701166145358L;

	public String getDepts() {
		try {
			logger.debug("开始查询部门列表");
			List<Department> depts = departmentService.list();
			logger.debug("查询成功，开始封装为键值对");
			Map<Integer, String> departments = new HashMap<>();
			for (Department d : depts) {
				departments.put(d.getDeptId(), d.getName());
			}
			logger.debug("部门表为：" + departments);
			resultMap.put("success", true);
			resultMap.put("result", departments);
		} catch (Exception e) {
			logger.info("查询时发生错误："+ e);
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("msg", "数据库错误，请稍后再试");
		}
		return SUCCESS;
	}

	public String getEmployees() {
		String deptId = getParameter("deptId");
		try {
			logger.debug("开始查询，获取的部门id为：" + deptId);
			Department department = departmentService.findById(Integer.parseInt(deptId));
			logger.debug("查询成功");
			Map<Integer, String> emps = new HashMap<>();
			for (Employee employee : department.getEmployees()){
				emps.put(employee.getEmpId(), employee.getName());
			}
			logger.debug("封装后的员工信息为：" + emps);
			resultMap.put("success", true);
			resultMap.put("result", emps);
		} catch (NumberFormatException e) {
			logger.info("ID格式错误发生异常" + e);
			resultMap.put("success", false);
			resultMap.put("msg", "部门ID格式错误，必须为整数");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("数据库错误发生异常" + e);
			resultMap.put("success", false);
			resultMap.put("msg", "数据库错误，请稍后再试");
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
