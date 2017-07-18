package com.jboa.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.common.RetCode;
import com.jboa.entity.Employee;
import com.jboa.entity.Right;

@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction{

	private static final long serialVersionUID = -7288676659480253610L;
	private List<Right> rightList;
	private Logger logger = Logger.getLogger(RoleAction.class);
	
	public String getCurrentRights(){
		try {
			logger.debug("开始查询角色权限");
			Employee employee = (Employee) getAttributeFromSession("SYS_EMP");
			if (null == employee){
				resultMap.put("retCode", RetCode.INVALID_SESSION);
				resultMap.put("retMsg", "尚未登录或长时间为操作");
				logger.debug("未登录");
			}else{
				logger.debug("角色ID为：" + employee.getRole().getId());
				rightList=roleRightService.getRightsByRoleId(employee.getRole().getId());
				logger.debug("获取列表的长度为： " + rightList.size());
				resultMap.put("rightList", rightList);
				resultMap.put("retCode", RetCode.SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("method getCurrentRights bug:{}",e);
			resultMap.put("retCode", RetCode.UNKOWN_WRONG);
			resultMap.put("retMsg", "未知错误，请重试或联系管理员");
		}
		return SUCCESS;
	}

	public List<Right> getRightList() {		return rightList;	}
	public void setRightList(List<Right> rightList) {		this.rightList = rightList;	}
}