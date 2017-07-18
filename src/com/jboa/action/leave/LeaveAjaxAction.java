package com.jboa.action.leave;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.action.BaseAction;
import com.jboa.entity.Employee;
import com.jboa.entity.dto.DetailLeave;

@Controller("leaveAjaxAction")
@Scope("prototype")
public class LeaveAjaxAction extends BaseAction {

	private static final long serialVersionUID = 2110290392029961394L;

	private DetailLeave detailLeave;
	
	public DetailLeave getDetailLeave() {		return detailLeave;	}
	public void setDetailLeave(DetailLeave detailLeave) {		this.detailLeave = detailLeave;	}

	//只负责用户本人提交请假和修改请假
	public String saveOrUpdate (){
		try {
			logger.debug("开始ajax异步新增/更新");
			leaveService.saveOrUpdate(detailLeave, ((Employee)getAttributeFromSession("SYS_EMP")));
			logger.debug("新增/更新成功");
			resultMap.put("success", true);
			resultMap.put("msg", "新增/更新成功");
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("msg", "服务器错误，请稍后再试");
			logger.info("新增/更新发生异常" + e);
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	//用于经理审批请假/修改已审批且未失效的请假
	public String approve (){
		logger.debug("获得审批结果为：" + detailLeave);
		if( detailLeave == null ){
			logger.info("数据不足");
			return ERROR;
		}
		try{
			logger.debug("开始进行审批");
			leaveService.approve(detailLeave, (Employee) getAttributeFromSession("SYS_EMP"));
			logger.debug("审批成功");
			resultMap.put("success", true);
			resultMap.put("msg", "审批成功");
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("msg", "服务器错误，请稍后再试");
			logger.info("审批时发生异常" + e);
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

}
