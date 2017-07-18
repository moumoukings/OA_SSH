package com.jboa.dao;

import java.util.Map;

import com.jboa.common.Pager;
import com.jboa.entity.Leave;

public interface LeaveDao {

	/**
	 * 根据ID来获取相应实体类
	 * @param id 实体ID
	 * @return
	 * @throws Exception
	 */
	Leave findObjectById(Integer id) throws Exception;
	
	/**
	 * 级联保存或添加请假单
	 * @param leave 请假单实体
	 * @throws Exception
	 */
	void saveOrUpdate(Leave leave) throws Exception;
	
	/**
	 * 指定的页数和参数键值对来进行分页查询
	 * @param pageIndex
	 * @param PageSize
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Pager<Leave> list (int pageIndex, int PageSize, Map<String, Object> params) throws Exception;
	/**
	 * 用于测试hql
	 */
	Pager<Leave> list (String hql, int pageIndex, int pageSize, Map<String, Object> params) throws Exception;

	/**
	 * 非经理：只能删除本人的未审批的的请假单
	 * 经理： 只能删除已审核的请假单
	 * @param leave 待删除的实体
	 * @return 成功返回true
	 */
	void delete(Leave leave) throws Exception;
	
}
