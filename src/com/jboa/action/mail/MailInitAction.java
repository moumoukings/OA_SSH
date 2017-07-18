package com.jboa.action.mail;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.action.BaseAction;
import com.jboa.common.Pager;
import com.jboa.entity.Employee;
import com.jboa.entity.Mail;
import com.jboa.entity.condition.MailCondition;
import com.jboa.entity.dto.AbstractMail;
import com.jboa.entity.dto.DetailMail;

@Controller("mailInitAction")
@Scope("prototype")

public class MailInitAction extends BaseAction {

	private static final long serialVersionUID = -4467307718386859936L;

	private Pager<AbstractMail> pager;
	private MailCondition condition;
	
	public MailCondition getCondition() {		return condition;	}
	public void setCondition(MailCondition condition) {		this.condition = condition;	}
	public Pager<AbstractMail> getPager() {		return pager;	}
	public void setPager(Pager<AbstractMail> pager) {		this.pager = pager;	}

	//垃圾箱
	public String deletedList() {
		setAttribute("actionName", "deletedList_init.action");
		setAttribute("deletedList", true);
		if (condition == null)
			condition = new MailCondition();
		condition.setReceiveState(Mail.RECEIVE_DESERT);
		condition.setSendState(Mail.SEND_DESERT);
		try {
			condition.setSenderId(getSYS_EMPId());
			condition.setReceiverId(getSYS_EMPId());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		if (list(condition).equals(SUCCESS)){
			for (AbstractMail mail : pager.getPageRecords()){
				mail.setDeleted(true);
			}
			return SUCCESS;
		}
		return ERROR;
	}
	//收件箱已读
	public String readedList() {
		setAttribute("actionName", "readedList_init.action");
		if (condition == null)
			condition = new MailCondition();
		condition.setReceiveState(Mail.RECEIVE_READED);
		try {
			condition.setReceiverId(getSYS_EMPId());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return list(condition);
	}
	//收件箱未读
	public String unreadList() {
		setAttribute("actionName", "readList_init.action");
		if (condition == null)
			condition = new MailCondition();
		condition.setReceiveState(Mail.RECEIVE_UNREAD);
		try {
			condition.setReceiverId(getSYS_EMPId());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return list(condition);
	}
	//发件箱
	public String sendList (){
		setAttribute("actionName", "readList_init.action");
		if (condition == null)
			condition = new MailCondition();
		try {
			condition.setSenderId(getSYS_EMPId());
			condition.setSendState(Mail.SEND_SENDED);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		if (list(condition).equals(SUCCESS)){
			for (AbstractMail mail : pager.getPageRecords()){
				mail.setSend(true);
			}
			return SUCCESS;
		}
		return ERROR;
	}
	
	private String list(MailCondition condition) {
		if (pager == null)
			pager = new Pager<>();
		try {
			pager = mailService.list(pager.getCurrentPage(), pager.getPageSize(), condition);
			for (AbstractMail leave : pager.getPageRecords()) {
				System.out.println(leave);
			}
			setAttribute("contentPageName", "/jsp/mail/list.jsp");
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
	
	private DetailMail detailMail;
	
	public DetailMail getDetailMail() {		return detailMail;	}
	public void setDetailMail(DetailMail detailMail) {		this.detailMail = detailMail;	}
	
	public String check (){
		try {
			String mailId = getParameter("mailId");
			logger.debug("要查看的的邮件ID为：" + mailId);
			detailMail = mailService.findObjectById (Integer.parseInt(mailId), getSYS_EMPId());
			setAttribute("contentPageName", "/jsp/mail/detail.jsp");
			logger.debug("查询到的邮件为：" + detailMail);
			logger.debug("查询完成");
		} catch (Exception e) {
			logger.info("查询失败:" +e);
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	private Integer getSYS_EMPId ()throws Exception{
		Employee employee = (Employee) getAttributeFromSession("SYS_EMP");
		return employee.getEmpId();
	}
}
