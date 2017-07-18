package com.jboa.dao;

import java.util.Map;

import com.jboa.common.Pager;
import com.jboa.entity.Mail;

public interface MailDao {

	void saveOrUpdate(Mail mail) throws Exception;

	Mail findObjectById(int mailId) throws Exception;

	Pager<Mail> list(int pageIndex, int pageSize, Map<String, Object> params) throws Exception;

	int getUnreadCount(int empId) throws Exception;
	
}
