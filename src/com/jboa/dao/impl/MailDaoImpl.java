package com.jboa.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jboa.common.Pager;
import com.jboa.dao.MailDao;
import com.jboa.entity.Mail;

@Repository("mailDao")
public class MailDaoImpl extends BaseDaoImpl<Mail, Integer> implements MailDao{

	@Override
	public void saveOrUpdate(Mail mail) throws Exception{
		log.debug("开始保存：" + mail + "  " + mail.getMailContent());
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		if (mail.getMailContent() != null)
			session.saveOrUpdate(mail.getMailContent());
		session.saveOrUpdate(mail);
		log.debug("保存成功");
	}

	@Override
	public Mail findObjectById(int mailId) throws Exception{
		return super.findById(mailId, Mail.class);
	}

	@Override
	public Pager<Mail> list(int pageIndex, int pageSize, Map<String, Object> params) throws Exception{
		StringBuilder hql = new StringBuilder(" from Mail m where 1=1 ");
		Map<String, Object> pMap = new HashMap<>();
		if (params.get("m.receiveState") != null && params.get("m.sendState") != null && params.get("m.receiver.empId") != null && params.get("m.sender.empId") != null){
			hql.append(" and m.receiveState=:" + "m.receiveState".replace(".", "_") + 
					" and m.receiver.empId=:" + "m.receiver.empId".replace(".", "_") );
			hql.append(" or m.sender.empId=:" + "m.sender.empId".replace(".", "_") +
					" and m.sendState=:" + "m.sendState".replace(".", "_") );
			
			pMap.put("m.receiveState".replace(".", "_"), params.get("m.receiveState"));
			pMap.put("m.sendState".replace(".", "_"), params.get("m.sendState"));
			pMap.put("m.receiver.empId".replace(".", "_"), params.get("m.receiver.empId"));
			pMap.put("m.sender.empId".replace(".", "_"), params.get("m.sender.empId"));
			params.remove("m.receiveState");
			params.remove("m.sendState");
			params.remove("m.receiver.empId");
			params.remove("m.sender.empId");
		}
		for (Entry<String, Object> entry: params.entrySet()){
			hql.append(" and  " + entry.getKey() + " =:" + entry.getKey().replace(".", "_"));
			pMap.put(entry.getKey().replace(".", "_"), params.get(entry.getKey()));
		}
		return super.findPager(hql.toString(), pageIndex, pageSize, pMap);
	}

	@Override
	public int getUnreadCount(int empId) throws Exception {
		String hql = " select count(*) from Mail m where m.receiveState=0 and m.receiver.empId="+empId;
		return super.getRecordCount(hql, null);
	}

}
