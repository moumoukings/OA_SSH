package com.jboa.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jboa.common.Pager;
import com.jboa.entity.Employee;
import com.jboa.entity.Mail;
import com.jboa.entity.MailContent;
import com.jboa.entity.condition.MailCondition;
import com.jboa.entity.dto.AbstractMail;
import com.jboa.entity.dto.DetailMail;
import com.jboa.service.MailService;
import com.jboa.util.DtoEntityConvertUtil;

@Service("mailService")
public class MailServiceImpl extends BaseService implements MailService{

	@Override
	public Pager<AbstractMail> list(int pageIndex, int pageSize, MailCondition condition) throws Exception {
		Map<String, Object> params = new HashMap<>();
		
		if (condition != null){
			if (condition.getReceiverId() != null)
				params.put("m.receiver.empId", condition.getReceiverId());
			if (condition.getReceiveState() != null)
				params.put("m.receiveState", condition.getReceiveState());
			if (condition.getSenderId() != null)
				params.put("m.sender.empId", condition.getSenderId());
			if (condition.getSendState() != null)
				params.put("m.sendState", condition.getSendState());
		}
		
		log.debug("封装的条件为："+params +  "  \n开始执行查询！");
		Pager<Mail> mails = mailDao.list (pageIndex, pageSize, params);
		log.debug("查询成功，返回的Pager信息为：" + mails + " 长度为：" + mails.getPageRecords().size());
		Pager<AbstractMail> pager = new Pager<>();
		mails.copy(pager);
		for (Mail mail : mails.getPageRecords()){
			pager.getPageRecords().add(DtoEntityConvertUtil.toAbstractMail(mail));
		}
		return pager;
	}

	@Override
	public void delete(int mailId, int empId) throws Exception {
		Mail mail = mailDao .findObjectById (mailId);
		if (mail.getReceiver().getEmpId().equals(empId)){//收件人移入垃圾箱
			if (mail.getReceiveState().equals(Mail.RECEIVE_DESERT) || mail.getReceiveState().equals(Mail.RECEIVE_DELETED)){
				mail.setReceiveState(Mail.RECEIVE_DELETED);
			}else {
				mail.setReceiveState(Mail.RECEIVE_DESERT);
			}
		}else if (mail.getSender().getEmpId().equals(empId)){//发件人移入垃圾箱
			if (mail.getSendState().equals(Mail.SEND_SENDED)){
				mail.setSendState(Mail.SEND_DESERT);
			}else {
				mail.setSendState(Mail.SEND_DELETED);
			}
		}
		mailDao.saveOrUpdate(mail);
	}
	//发送邮件
	@Override
	public void save(DetailMail detailMail) throws Exception {
		Mail mail = new Mail();
		Employee sender = employeeDao.findById(detailMail.getSenderId(), Employee.class);
		Employee receiver = employeeDao.findById(detailMail.getReceiverId(), Employee.class);
		mail.setSender(sender);
		mail.setReceiver(receiver);
		mail.setCreateTime(new Date());
		if (detailMail.getFilename() != null || detailMail.getContent()!= null){
			MailContent content = new MailContent();
			content.setAttachFile(detailMail.getFilename());
			content.setContent(detailMail.getContent());
			mail.setMailContent(content);
		}
		mail.setReceiveState(Mail.RECEIVE_UNREAD);
		mail.setSendState(Mail.SEND_SENDED);
		mail.setTitle(detailMail.getTitle());
		mailDao.saveOrUpdate(mail);
	}

	//从回收站中还原邮件
	@Override
	public void update(int mailId, int empId) throws Exception {
		Mail mail = mailDao.findObjectById (mailId);
		if (mail.getReceiver().getEmpId().equals(empId)){//还原到收件箱
			if (mail.getReceiveState().equals(Mail.RECEIVE_DESERT)){
				mail.setReceiveState(Mail.RECEIVE_READED);
			}
		}else if(mail.getSender().getEmpId().equals(empId)){//还原到发件箱
			if (mail.getSendState().equals(Mail.SEND_DESERT))
				mail.setSendState(Mail.SEND_SENDED);
		}
		mailDao.saveOrUpdate(mail);
	}

	@Override
	public DetailMail findObjectById(int mailId, int empId) throws Exception {
		Mail mail = mailDao.findObjectById(mailId);
		if (mail.getReceiver().getEmpId().equals(empId)){
			mail.setReceiveState(Mail.RECEIVE_READED);
		}
		mailDao.saveOrUpdate(mail);
		return DtoEntityConvertUtil.toDetailMail(mail);
	}

	@Override
	public int getUnreadCount(int empId) throws Exception {
		return mailDao.getUnreadCount (empId);
	}
}
