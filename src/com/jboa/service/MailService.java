package com.jboa.service;

import com.jboa.common.Pager;
import com.jboa.entity.condition.MailCondition;
import com.jboa.entity.dto.AbstractMail;
import com.jboa.entity.dto.DetailMail;

public interface MailService {

	Pager<AbstractMail> list(int pageIndex, int pageSize, MailCondition condition) throws Exception ;

	void delete(int mailId, int empId)throws Exception;

	/**
	 * 发送邮件，将邮件状态设置为未读
	 * @param detailMail
	 * @throws Exception
	 */
	void save(DetailMail detailMail)throws Exception;

	/**
	 * 如果状态为未删除，将状态设置为Mail.DESERT(即移入垃圾箱)
	 * 如果状态已经为DESERT，将状态设为DELETED
	 * @param mailId 邮件ID
	 * @param empId 请求者
	 */
	void update(int mailId, int empId) throws Exception;
	
	/**
	 * 查询
	 * 如查询者是接收方则将邮件状态设为已读
	 * 否则不更新状态
	 * @param mailId 待查询的邮件ID
	 * @return 邮件详情
	 * @throws Exception
	 */
	DetailMail findObjectById(int mailId, int empId)throws Exception;

	/**
	 * 
	 */
	int getUnreadCount (int empId) throws Exception;
}
