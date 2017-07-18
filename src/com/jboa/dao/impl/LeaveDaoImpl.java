package com.jboa.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.jboa.common.Pager;
import com.jboa.dao.LeaveDao;
import com.jboa.entity.Leave;
import com.jboa.exception.HibernateDaoSupportException;

@Repository("leaveDao")
public class LeaveDaoImpl extends BaseDaoImpl<Leave, Integer> implements LeaveDao {

	@Override
	public Leave findObjectById(Integer id) throws Exception {
		return super.findById(id, Leave.class);
	}

	@Override
	public void saveOrUpdate(Leave leave) throws Exception {
		log.debug("开始更新/新增");
		if (leave.getLeaveResult() != null) {
			logger.debug("更新的休假信息为：" + leave);
			logger.debug("保存的结果信息为：" + leave.getLeaveResult());
			Session currentSession = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
			currentSession.save(leave.getLeaveResult());
			logger.debug(" 结果ID为： " + leave.getLeaveResult().getLresutlId());
			/* 无法更新外键？ */
			/*
			 * leave.setLeaveResult((LeaveResult)
			 * currentSession.get(LeaveResult.class,
			 * leave.getLeaveResult().getLresutlId()));
			 * currentSession.update(leave);
			 */
			String sql = "update leave_l l set l.l_resutl_id =" + leave.getLeaveResult().getLresutlId()
					+ " where vacation_id=" + leave.getVacationId();
			currentSession.createSQLQuery(sql).executeUpdate();
		} else {
			super.saveOrUpdateEntity(leave);
		}
		log.debug("更新/新增成功");
	}

	/**
	 * 用于测试hql
	 */
	@Override
	public Pager<Leave> list(String hql, int pageIndex, int pageSize, Map<String, Object> params) throws Exception {
		return super.findPager(hql, pageIndex, pageSize, params);
	}

	/**
	 * 用于查询请假单集合 result为3时表示result不为空 result为null时表示为空
	 * Map集合的键必须以l.开头
	 */
	@Override
	public Pager<Leave> list(int pageIndex, int pageSize, Map<String, Object> params) throws Exception {
		StringBuilder hql = new StringBuilder("select l from Leave l left join l.leaveResult where 1=1 ");
		Map<String, Object> paraTemp = new HashMap<>();

		if (params.get("l.leaveResult.result") != null && params.get("l.leaveResult.result").equals(2)) {
			params.remove("l.leaveResult.result");
		}
		for (Entry<String, Object> entry : params.entrySet()) {
			if (entry.getKey().equals("l.leaveResult.result")) {
				if (params.get(entry.getKey()) == null) {
					hql.append(" and " + entry.getKey() + "=null ");
					continue;
				} else if (params.get("l.leaveResult.result").equals(3)) { // result为3
					hql.append(" and " + entry.getKey() + "!=null ");
					continue;
				}
			}
			if (entry.getKey().equals("l.leaveResult")){
				if (params.get(entry.getKey()) == null){
					hql.append(" and " + entry.getKey() + "=null ");
					continue;
				}
			}
			hql.append(" and " + entry.getKey() + "=:" + entry.getKey().replace(".", "_"));
			paraTemp.put(entry.getKey().replace(".", "_"), params.get(entry.getKey()));
		}
		logger.info("组装的hql语句是：" + hql);
		logger.info("原本条件为：" + params);
		logger.info("过滤后的条件为：" + paraTemp);
		return super.findPager(hql.toString(), pageIndex, pageSize, paraTemp);
	}

	@Override
	public void delete (Leave leave)  throws HibernateDaoSupportException{
		super.delete(leave);
		//this.getHibernateTemplate().getSessionFactory().getCurrentSession().delete(leave);
	}
}
