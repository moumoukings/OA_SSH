package com.jboa.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jboa.common.Pager;
import com.jboa.entity.Employee;
import com.jboa.entity.Leave;
import com.jboa.entity.LeaveResult;
import com.jboa.entity.condition.LeaveCondition;
import com.jboa.entity.dto.AbstractLeave;
import com.jboa.entity.dto.DetailLeave;
import com.jboa.service.LeaveService;
import com.jboa.util.DtoEntityConvertUtil;

@Service("leaveService")
public class LeaveServiceImpl extends BaseService implements LeaveService {

	/**
	 * 只用于本人新增和更新请假数据
	 * 
	 * @param detailLeave
	 *            包含了待持久化的信息
	 * @param creator
	 *            当前操作的员工对象
	 */
	@Override
	public void saveOrUpdate(DetailLeave detailLeave, Employee creator) throws Exception {
		Leave leave;
		if (detailLeave.getId() == null) {// 新增
			leave = DtoEntityConvertUtil.toLeave(detailLeave, null);
			leave.setCreator(creator);
			leave.setCreateTime(new Date());
		} else {// 更新
			leave = leaveDao.findObjectById(detailLeave.getId());
			leave = DtoEntityConvertUtil.toLeave(detailLeave, leave);
		}
		log.info("新增/更新的请假信息为： " + detailLeave);
		log.info("创建人信息为:" + creator);
		log.info("封装后的请假信息为：" + leave);
		leaveDao.saveOrUpdate(leave);
	}

	@Override
	public Pager<AbstractLeave> applyList(Integer empId, int pageIndex, int pageSize, LeaveCondition condition)
			throws Exception {
		Map<String, Object> params = new HashMap<>();
		if (empId == null) {
			throw new Exception("待查询员工的ID不能为空");
		}
		params.put("l.creator.empId", empId);
		if (condition != null) {
			if (condition.getType() != null){
				params.put("l.leaveType", condition.getType());
			}
			if (condition.getState() != null) {
				params.put("l.state", condition.getState());
			}
			params.put("l.leaveResult.result", condition.getResult());
		}
		log.debug("条件封装完成，相应的条件Map为： " + params);

		Pager<Leave> leaves = leaveDao.list(pageIndex, pageSize, params);
		log.debug("查询到的leavePager相关信息为：" + leaves + " \n长度为：" + leaves.getPageRecords().size());
		log.debug("准备封装为abstractLeave");

		Pager<AbstractLeave> pager = new Pager<>();
		leaves.copy(pager);
		for (Leave leave : leaves.getPageRecords()) {
			pager.getPageRecords().add(DtoEntityConvertUtil.toAbstractLeave(leave));
		}
		log.debug("封装的abstractleavePager相关信息为：" + pager + " \n长度为：" + pager.getPageRecords().size());
		return pager;
	}

	@Override
	public DetailLeave findObjectById(Integer leaveId) throws Exception {
		Leave leave = leaveDao.findObjectById(leaveId);
		return DtoEntityConvertUtil.toDetailLeave(leave);
	}

	@Override
	public void approve(DetailLeave detailLeave, Employee approver) throws Exception {
		if (detailLeave == null || approver == null || approver.getEmpId() == null || detailLeave.getIstate() == null
				|| detailLeave.getId() == null) {
			throw new Exception("数据不足");
		}
		Leave leave = leaveDao.findObjectById(detailLeave.getId());
		LeaveResult result = new LeaveResult();
		result.setApprovalTime(new Date());
		result.setApprover(approver);
		result.setComment(detailLeave.getComment());
		result.setResult(detailLeave.getIstate());
		leave.setLeaveResult(result);
		leaveDao.saveOrUpdate(leave);
	}

	/**
	 * 三个功能状态分别如下 
	 * 1-已审批 result不为空 
	 * 2-审批 状态为0，且result为空 
	 * 3-假期中 状态为0，且result.result= 1
	 */
	@Override
	public Pager<AbstractLeave> approveList(Employee approver, int pageIndex, int pageSize, LeaveCondition condition)
			throws Exception {
		Map<String, Object> params = new HashMap<>();
		if (approver.getRole().getId().equals(2)) {// 部门经理
			/* 查询所有本部门的员工的请假记录 */
			params.put("l.creator.department.deptId", approver.getDepartment().getDeptId()); // 本部门
			params.put("l.creator.role.id", 1); // 条件为员工
		} else if (approver.getRole().getId().equals(3)) { // 经理
			/* 查询所有部门经理的请假记录 */
			params.put("l.creator.role.id", 2); // 条件为部门经理
		}
		if (condition.getState() != null)
			params.put("l.state", condition.getState()); // 是否已过期
		if (condition.getResult() != null)
			params.put("l.leaveResult.result", condition.getResult()); // 是否已审批
		else //无结果，未审批
			params.put("l.leaveResult", null);
		log.debug("条件封装完成，相应的条件Map为： " + params);

		Pager<Leave> leaves = leaveDao.list(pageIndex, pageSize, params);
		log.debug("查询到的leavePager相关信息为：" + leaves + " \n长度为：" + leaves.getPageRecords().size());
		log.debug("准备封装为abstractLeave");

		Pager<AbstractLeave> pager = new Pager<>();
		leaves.copy(pager);
		for (Leave leave : leaves.getPageRecords()) {
			pager.getPageRecords().add(DtoEntityConvertUtil.toAbstractLeave(leave));
		}
		log.debug("封装的abstractleavePager相关信息为：" + pager + " \n长度为：" + pager.getPageRecords().size());
		return pager;
	}
	/**
	 * 非经理：只能删除本人的未审批的的请假单
	 * 经理： 只能删除已审核的请假单
	 * @param applyer 删除申请的发起人
	 * @param id 待删除请假单ID
	 * @return 成功返回true
	 */
	@Override
	public boolean delete(Employee applyer, Integer id) throws Exception{
		Leave leave = leaveDao.findObjectById(id);
		if (!applyer.getRole().getId().equals(3)){//非经理
			if (!leave.getCreator().getEmpId().equals(applyer.getEmpId()))
				return false;
			if (leave.getLeaveResult() != null)//已审批无法删除
				return false;
			leaveDao.delete (leave);
		}else { 	//经理
			if (leave.getLeaveResult() == null)
				return false;
			leaveDao.delete (leave);
		}
		return true;
	}

}
