package com.jboa.service;

import com.jboa.common.Pager;
import com.jboa.entity.Employee;
import com.jboa.entity.condition.LeaveCondition;
import com.jboa.entity.dto.AbstractLeave;
import com.jboa.entity.dto.DetailLeave;

public interface LeaveService {
	/**
	 * 只用于本人新增和更新请假数据
	 * @param detailLeave 包含了待持久化的信息
	 * @param employee 当前操作的员工对象
	 */
	void saveOrUpdate(DetailLeave detailLeave, Employee employee) throws Exception;
	/**
	 * 获取本人的请假记录列表
	 * @param empId 员工ID
	 * @param condition 条件，若无条件传null值入
	 * @param pager 包含了相应的分页信息
	 * @return 查询到的分页实体
	 * @throws Exception
	 */
	public Pager<AbstractLeave> applyList(Integer empId,  int pageIndex, int pageSize, LeaveCondition condition) throws Exception;
	
	/**
	 * 部门经理根据部门来查询待审核的请假单
	 * 经理查询经理的请假单
	 * @param approver 审批人
	 * @param pageIndex 当前页码
	 * @param pageSize 每页显示的个数
	 * @param condition 包含了条件的查询语句
	 * @return 返回一个封装了分页信息的实体
	 * @throws Exception
	 */
	public Pager<AbstractLeave> approveList (Employee approver, int pageIndex, int pageSize, LeaveCondition condition)throws Exception;
	
	DetailLeave findObjectById(Integer leaveId) throws Exception;

	/**
	 * 审批请假单
	 * @param detailLeave 包含了审批信息的实体
	 * @param approver 审批人
	 * @throws 在审批信息不足或是审批人信息不足时抛出异常
	 */
	void approve (DetailLeave detailLeave, Employee approver) throws Exception;
	/**
	 * 非经理：只能删除本人的未审批的的请假单
	 * 经理： 只能删除已审核的请假单
	 * @param applyer 删除申请的发起人
	 * @param id 待删除请假单ID
	 * @return 成功返回true
	 */
	boolean delete(Employee applyer, Integer id) throws Exception;
}
