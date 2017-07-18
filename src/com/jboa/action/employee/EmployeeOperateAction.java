package com.jboa.action.employee;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.action.BaseAction;
import com.jboa.entity.Employee;

@Controller("employeeOperateAction")
@Scope("prototype")
public class EmployeeOperateAction extends BaseAction {

	private static final long serialVersionUID = 3195102569385103824L;
	
	private String oldPassword, newPassword, repeatPassword;

	public String getOldPassword() {		return oldPassword;	}
	public void setOldPassword(String oldPassword) {		this.oldPassword = oldPassword;	}
	public String getNewPassword() {		return newPassword;	}
	public void setNewPassword(String newPassword) {		this.newPassword = newPassword;	}
	public String getRepeatPassword() {		return repeatPassword;	}
	public void setRepeatPassword(String repeatPassword) {		this.repeatPassword = repeatPassword;	}
	
	public String alterPwd (){
		if (oldPassword == null || !newPassword.equals(repeatPassword)){
			return ERROR;
		}else {
			Employee employee = (Employee) getAttributeFromSession("SYS_EMP");
			if (oldPassword.equals(employee.getPassword())){
				employee.setPassword(newPassword);
				try {
					employeeService.updateEmployee(employee);
				} catch (Exception e) {
					e.printStackTrace();
					return ERROR;
				}
				putToSession("SYS_EMP", employee);
			}else {
				return ERROR;
			}
		}
		setAttribute("contentPageName", "/welcome.jsp");
		return SUCCESS;
	}
}
