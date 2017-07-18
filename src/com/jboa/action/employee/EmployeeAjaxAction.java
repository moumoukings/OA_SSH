package com.jboa.action.employee;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.action.BaseAction;
import com.jboa.entity.Employee;
import com.jboa.entity.dto.DetailEmployee;

@Controller("employeeAjaxAction")
@Scope("prototype")
public class EmployeeAjaxAction extends BaseAction{

	private static final long serialVersionUID = 6754263383255503180L;
	protected static Logger logger = Logger.getLogger(EmployeeAjaxAction.class);
	
	private boolean available;
	private String currentEmpName;
	
	public boolean isAvailable() {		return available;	}
	public void setAvailable(boolean available) {		this.available = available;	}
	public String getCurrentEmpName() {		return currentEmpName;	}
	public void setCurrentEmpName(String currentEmpName) {		this.currentEmpName = currentEmpName;	}
	
	public String checkNameAvail (){
		logger.debug("验证用户名开始执行,传入参数为 "+ detailEmployee.getName() + "  " +currentEmpName);
		try {
			if (currentEmpName != null && detailEmployee.getName().equals(currentEmpName)){
				logger.debug("用户名可用");
				available = true;
			}else if(employeeService.nameIsAvai(detailEmployee.getName())){
				logger.debug("用户名可用");
				available = true;
			}else{
				available = false;
				logger.debug("用户名不可用");
			}
		} catch (Exception e) {
			available = false;
			logger.info("发生异常" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	private DetailEmployee detailEmployee;
	
	public DetailEmployee getDetailEmployee() {		return detailEmployee;	}
	public void setDetailEmployee(DetailEmployee detailEmployee) {		this.detailEmployee = detailEmployee;	}

	public String update (){
		return saveOrUpdate();
	}	
	public String add (){
		return saveOrUpdate();
	}
	private String saveOrUpdate() {
		try {
			String address = detailEmployee.getAddress();
			String sex = detailEmployee.getSex();
			sex = new String (sex.getBytes("iso-8859-1"),"utf-8");
			address = new String(address.getBytes("iso-8859-1"),"utf-8");
			detailEmployee.setAddress(address);
			detailEmployee.setSex(sex);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			resultMap.put("msg", "参数不全或非法");
			resultMap.put("success", false);
			return ERROR;
		}
		try {
			System.out.println("开始更新/新增");
			logger.debug("获取到的用户信息为：" + detailEmployee);
			employeeService.saveOrUpdate(detailEmployee);
			needRefresh();
			resultMap.put("msg", "更新/新增成功");
			resultMap.put("success", true);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("更新/新增用户时发生异常：" + e);
			resultMap.put("msg", "服务器错误");
			resultMap.put("success", false);
			return ERROR;
		}
	}
	private void needRefresh() throws Exception{
		Integer updateEmpId = detailEmployee.getId();
		if (updateEmpId != null){
			Employee employee = (Employee) getAttributeFromSession("SYS_EMP");
			if (employee.getEmpId() != null){
				if (updateEmpId.equals(employee.getEmpId())){
					resultMap.put("refresh", true);
					employee.setName(detailEmployee.getName());
					putToSession("SYS_EMP", employee);
				}
			}
		}
	}
	public String verifyPwd (){
		String pwd = getParameter("password");
		if (pwd == null){
			resultMap.put("success", false);
			resultMap.put("msg", "旧密码不能空");
		}else {
			Employee employee = (Employee) getAttributeFromSession("SYS_EMP");
			if (pwd.equals(employee.getPassword())){
				resultMap.put("success", true);
				resultMap.put("msg", "旧密码正确可以修改");
			}else {
				resultMap.put("success", false);
				resultMap.put("msg", "旧密码不正确");
			}
		}
		return SUCCESS;
	}
}
