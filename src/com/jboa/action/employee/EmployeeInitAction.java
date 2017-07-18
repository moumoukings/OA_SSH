package com.jboa.action.employee;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.action.BaseAction;
import com.jboa.common.Pager;
import com.jboa.entity.Department;
import com.jboa.entity.Employee;
import com.jboa.entity.Role;
import com.jboa.entity.dto.AbstractEmployee;
import com.jboa.entity.dto.DetailEmployee;

@Controller("employeeInitAction")
@Scope("prototype")
public class EmployeeInitAction extends BaseAction {

	private static final long serialVersionUID = -4467332869138785404L;

	private static Logger logger = Logger.getLogger(EmployeeInitAction.class);

	private Employee employee;

	public Employee getEmployee() {		return employee;	}
	public void setEmployee(Employee employee) {		this.employee = employee;	}

	public String login() {
		Employee emp = null;
		try {
			emp = employeeService.login(employee.getName(), employee.getPassword());
			if (emp == null) {
				resultMap.put("success", false);
				resultMap.put("msg", "用户名或密码错误");
			} else {
				this.getSession().setAttribute("SYS_EMP", emp);
				this.getSession().setAttribute("ROOT", "/jboa2.0/");
				resultMap.put("success", true);
				resultMap.put("msg", "登录成功啦啦啦");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("登录发生异常 :" + e);
			resultMap.put("msg", "数据库错误，请稍后再试");
			resultMap.put("success", false);
		}
		return SUCCESS;
	}

	Pager<AbstractEmployee> pager;

	public Pager<AbstractEmployee> getPager() {		return pager;	}
	public void setPager(Pager<AbstractEmployee> pager) {		this.pager = pager;	}

	public String list() {
		try {
			System.out.println("list开始执行");
			if (pager == null) {
				pager = new Pager<>();
			}
			pager = employeeService.getEmployeesByPager(pager.getCurrentPage(), pager.getPageSize(), null);
			logger.debug("获取到的实体共：" + pager.getPageRecords().size() + "个。");
			setAttribute("contentPageName", "/jsp/employee/list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("查询用户列表失败：" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	DetailEmployee detailEmployee;

	public DetailEmployee getDetailEmployee() {		return detailEmployee;	}
	public void setDetailEmployee(DetailEmployee detailEmployee) {		this.detailEmployee = detailEmployee;	}

	public List<Role> roles;

	public List<Role> getRoles() {		return roles;	}
	public void setRoles(List<Role> roles) {		this.roles = roles;	}

	public String check() {
		String empId = getParameter("empId");
		logger.debug("获取的ID为:" + empId);
		if (empId == null) {
			try {
				detailEmployee = employeeService.getEmployeeById(((Employee) getAttributeFromSession("SYS_EMP")).getEmpId());
			} catch (Exception e) {
				logger.info("发生异常:" + e);
				e.printStackTrace();
				return ERROR;
			}
		}else{
			try {
				detailEmployee = employeeService.getEmployeeById(Integer.parseInt(empId));
				roles = roleRightService.getRoles(((Employee) getAttributeFromSession("SYS_EMP")).getRole().getId());
			} catch (Exception e) {
				logger.info("发生异常:" + e);
				e.printStackTrace();
				return ERROR;
			}
		}
		logger.debug("查询到的员工信息为： " + detailEmployee);
		setAttribute("contentPageName", "/jsp/employee/detail.jsp");
		return SUCCESS;
	}

	private List<Department> departments;

	public List<Department> getDepartments() {		return departments;	}
	public void setDepartments(List<Department> departments) {		this.departments = departments;	}

	public String add() {
		try {
			roles = roleRightService.getRoles(((Employee) getAttributeFromSession("SYS_EMP")).getRole().getId());
			departments = departmentService.list();
			setAttribute("contentPageName", "/jsp/employee/add.jsp");
		} catch (Exception e) {
			logger.info("初始化添加时发生异常" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	private String[] deleteIdList;

	public String[] getDeleteIdList() {		return deleteIdList;	}
	public void setDeleteIdList(String[] deleteIdList) {		this.deleteIdList = deleteIdList;	}

	/**
	 * 用于批量删除
	 * 无法删除经理
	 * @return
	 */
	public String delete() {
		logger.debug("待删除的员工的ID为：" + deleteIdList);
		try {
			boolean flag= false;
			Employee employee = (Employee) getAttributeFromSession("SYS_EMP");
			for (String id : deleteIdList) {
				if (employee.getEmpId().equals(Integer.parseInt(id)) || employeeService.getEmployeeById(Integer.parseInt(id)).getRole().getId().equals(3)){
					flag = true;
					continue;
				}
				employeeService.deleteEmployee(Integer.parseInt(id));
			}
			logger.debug("完成删除");
			if (flag){
				resultMap.put("success", true);
				resultMap.put("msg", "无法删除经理账户");
			}else{
				resultMap.put("success", true);
				resultMap.put("msg", "删除成功");
			}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("msg", "服务器错误，请稍后再试");
			logger.info("删除时发生异常" + e);
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
}