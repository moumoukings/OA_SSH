package com.jboa.action.leave;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.action.BaseAction;
import com.jboa.common.Pager;
import com.jboa.entity.Employee;
import com.jboa.entity.condition.LeaveCondition;
import com.jboa.entity.dto.AbstractLeave;
import com.jboa.entity.dto.DetailLeave;
import com.jboa.util.Constant;

@Controller("leaveInitAction")
@Scope("prototype")
public class LeaveInitAction extends BaseAction {

	private static final long serialVersionUID = -7963684946703198552L;

	private Map<Integer, String> types;

	public Map<Integer, String> getTypes() {
		return types;
	}

	public void setTypes(Map<Integer, String> types) {
		this.types = types;
	}

	public String add() {
		types = Constant.DIC_MAP_LEAVE_TYPE;
		setAttribute("contentPageName", "/jsp/leave/add.jsp");
		return SUCCESS;
	}

	private DetailLeave detailLeave;

	public DetailLeave getDetailLeave() {		return detailLeave;	}
	public void setDetailLeave(DetailLeave detailLeave) {		this.detailLeave = detailLeave;	}

	public String check() {
		String id = getParameter("leaveId");
		logger.debug("leaveId 的值为：" + id);
		if (id != null) {
			try {
				detailLeave = leaveService.findObjectById(Integer.parseInt(id));
				logger.debug("获取的detailLeave是： " + detailLeave);
				types = Constant.DIC_MAP_LEAVE_TYPE;
				setAttribute("contentPageName", "/jsp/leave/update.jsp");
				return SUCCESS;
			} catch (NumberFormatException e) {
				logger.info("传入的id格式非法" + e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.info("获取detailLeave时发生异常" + e);
				e.printStackTrace();
			}
		}
		return ERROR;
	}

	Pager<AbstractLeave> pager;

	public Pager<AbstractLeave> getPager() {		return pager;	}
	public void setPager(Pager<AbstractLeave> pager) {		this.pager = pager;	}
	
	LeaveCondition condition;

	public LeaveCondition getCondition() {		return condition;	}
	public void setCondition(LeaveCondition condition) {		this.condition = condition;	}

	// 根据不同的角色来进行请假记录的查询
	public String applyList() {
		// 员工请假记录的查询，部门经理、财务
		if (pager == null)
			pager = new Pager<>();
		try {
			pager = leaveService.applyList(((Employee) getAttributeFromSession("SYS_EMP")).getEmpId(),
					pager.getCurrentPage(), pager.getPageSize(), condition);
			for (AbstractLeave leave : pager.getPageRecords()) {
				System.out.println(leave);
			}
			setAttribute("contentPageName", "/jsp/leave/list.jsp");
			setAttribute("conditionSearch", 1);
			resultMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询列表时发生异常:" + e);
			resultMap.put("success", false);
			resultMap.put("msg", "数据库异常");
			return ERROR;
		}
		return SUCCESS;
	}

	String actionName;
	
	public String getActionName() {		return actionName;	}
	public void setActionName(String actionName) {		this.actionName = actionName;	}

	/**
	 * 三个功能状态分别如下
	 * 1-已审批  result不为空
	 * 2-审批 状态为0，且result为空
	 * 3-假期中 状态为0，且result.result = 1
	*/
	/**根据不同的角色进行审批列表的查询
	 * 未即时审批的请假会丢失(即状态为1且未审批的请假)
	 */
	
	/**
	 * 审批 状态为0且结果为空
	 * @return
	 */
	public String approveList() {
		if (condition == null)
			condition = new LeaveCondition();
		condition.setResult(null);
		condition.setState(0);
		actionName = "approveList_init.action";
		return list(condition);
	}

	/** 
	 * 进行正在休假员工的查询
	 * 状态为1且结果为1
	 * 
	 */
	public String leavingList() {
		if (condition == null)
			condition = new LeaveCondition();
		condition.setResult(1);
		condition.setState(1);
		actionName = "leavingList_init.action";
		return list(condition);
	}

	/**
	 * 进行已审批休假的查询 
	 * 结果不为空的所有请假单
	 * @return
	 */
	public String approvedList() {
		if (condition == null)
			condition = new LeaveCondition();
		condition.setResult(3);
		actionName = "approvedList_init.action";
		return list(condition);
	}

	private String list(LeaveCondition con) {
		if (pager == null)
			pager = new Pager<>();
		try {
			pager = leaveService.approveList(((Employee) getAttributeFromSession("SYS_EMP")), pager.getCurrentPage(),
					pager.getPageSize(), con);
			for (AbstractLeave leave : pager.getPageRecords()) {
				System.out.println(leave);
			}
			setAttribute("contentPageName", "/jsp/leave/list.jsp");
			resultMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询列表时发生异常:" + e);
			resultMap.put("success", false);
			resultMap.put("msg", "数据库异常");
			return ERROR;
		}
		return SUCCESS;
	}
	
	private String[] deleteIdList;
	
	public String[] getDeleteIdList() {		return deleteIdList;	}
	public void setDeleteIdList(String[] deleteIdList) {		this.deleteIdList = deleteIdList;	}
	
	/**
	 * 用于批量删除
	 * 非经理：只能删除本人的未审批的的请假单
	 * 经理： 只能删除已审核的请假单
	 * @return
	 */
	public String delete (){
		logger.debug("待删除的请假单的ID为：" + deleteIdList);
		try{
			List<Integer> failedList = new ArrayList<>();
			Employee applyer = (Employee) getAttributeFromSession("SYS_EMP");
			for (String id : deleteIdList){
				if (!leaveService.delete(applyer, Integer.parseInt(id))){
					failedList.add(Integer.parseInt(id));
				}
			}			
			logger.debug("完成删除");
			resultMap.put("success", true);
			if (failedList.size() == 0)
				resultMap.put("msg", "删除成功");
			else 
				resultMap.put("msg", failedList.size() + "条记录无法删除");
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
