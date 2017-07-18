package com.jboa.util;

import com.jboa.entity.Employee;
import com.jboa.entity.Leave;
import com.jboa.entity.Mail;
import com.jboa.entity.Voucher;
import com.jboa.entity.VoucherResult;
import com.jboa.entity.dto.AbstractEmployee;
import com.jboa.entity.dto.AbstractLeave;
import com.jboa.entity.dto.AbstractMail;
import com.jboa.entity.dto.AbstractVoucher;
import com.jboa.entity.dto.DetailEmployee;
import com.jboa.entity.dto.DetailLeave;
import com.jboa.entity.dto.DetailMail;

public class DtoEntityConvertUtil {
	private DtoEntityConvertUtil(){	}
	
	//将实体类转化为视图需要的摘要信息
	public static AbstractEmployee toAbstractEmployee (Employee employee){
		AbstractEmployee abstractEmployee = new AbstractEmployee();
		abstractEmployee.setAddress(employee.getAddress());
		abstractEmployee.setId(employee.getEmpId());
		abstractEmployee.setName(employee.getName());
		abstractEmployee.setPhoneNum(employee.getPhoneNum());
		return abstractEmployee;
	}
	public static AbstractLeave toAbstractLeave (Leave leave){
		AbstractLeave abstractLeave = new AbstractLeave();
		abstractLeave.setEndDate(leave.getEndDate());
		abstractLeave.setId(leave.getVacationId());
		abstractLeave.setStartDate(leave.getStartDate());
		abstractLeave.setState(getLeaveState(leave));
		abstractLeave.setType (Constant.DIC_MAP_LEAVE_TYPE.get(leave.getLeaveType()));
		abstractLeave.setCreator(leave.getCreator().getName());
		return abstractLeave;
	}
	public static AbstractMail toAbstractMail (Mail mail){
		AbstractMail abstractMail = new AbstractMail();
		abstractMail.setId(mail.getMailId());
		abstractMail.setReceiveState(Constant.DIC_MAP_RECEIVE_MAIL_STATE.get(mail.getReceiveState()));
		abstractMail.setSendState(Constant.DIC_MAP_SEND_MAIL_STATE.get(mail.getSendState()));
		abstractMail.setTime(mail.getCreateTime());
		abstractMail.setTitle(mail.getTitle());
		abstractMail.setContent(mail.getMailContent().getContent());
		return abstractMail;
	}
	public static AbstractVoucher toAbstractVoucher (Voucher voucher){
		AbstractVoucher abstractVoucher = new AbstractVoucher();
		
		return abstractVoucher;
	}
	
	//将dto类转化为相应的实体类
	public static Employee toEmployee (DetailEmployee detailEmployee){
		Employee employee = new Employee();
		employee.setEmpId(detailEmployee.getId());
		employee.setAddress(detailEmployee.getAddress());
		employee.setAge(detailEmployee.getAge());
		employee.setName(detailEmployee.getName());
		employee.setPassword(detailEmployee.getPassword());
		employee.setPhoneNum(detailEmployee.getPhoneNum());
		employee.setSex(detailEmployee.getSex());
		employee.setRole(detailEmployee.getRole());
		employee.setDepartment(detailEmployee.getDepartment());
		return employee;
	}
	/**
	 * 用于新增/更新
	 * @param detailLeave
	 * @param l
	 * @return
	 */
	public static Leave toLeave (DetailLeave detailLeave, Leave l){
		Leave leave ;
		if (l == null){
			leave = new Leave();
			leave.setState(0);
		}else {
			leave = l;
			leave.setState(l.getState());
		}
		if (detailLeave.getId() != null)
			leave.setVacationId(detailLeave.getId());
		if (detailLeave.getStartDate() != null)
			leave.setStartDate(detailLeave.getStartDate());
		if (detailLeave.getEndDate() != null)
			leave.setEndDate(detailLeave.getEndDate());
		if (detailLeave.getReason() != null)
			leave.setEvent(detailLeave.getReason());
		if (detailLeave.getLeaveType() != null)
			leave.setLeaveType(detailLeave.getLeaveType());
		return leave;
	}
	
	public static DetailMail toDetailMail (Mail mail){
		DetailMail detailMail = new DetailMail();
		detailMail.setMailId(mail.getMailId());
		detailMail.setContent(mail.getMailContent().getContent());
		detailMail.setFilename(mail.getMailContent().getAttachFile());
		detailMail.setReceiverId(mail.getReceiver().getEmpId());
		detailMail.setReceiverName(mail.getReceiver().getName());
		detailMail.setSenderId(mail.getSender().getEmpId());
		detailMail.setSendName(mail.getSender().getName());
		detailMail.setSendTime(mail.getCreateTime());
		detailMail.setTitle(mail.getTitle());
		return detailMail;
	}

	public static DetailEmployee toDetailEmloyee(Employee employee) {
		DetailEmployee detailEmployee = new DetailEmployee();
		detailEmployee.setAddress(employee.getAddress());
		detailEmployee.setAge(employee.getAge());
		detailEmployee.setId(employee.getEmpId());
		detailEmployee.setName(employee.getName());
		detailEmployee.setPassword(employee.getPassword());
		detailEmployee.setPhoneNum(employee.getPhoneNum());
		detailEmployee.setSex(employee.getSex());
		detailEmployee.setRole(employee.getRole());
		detailEmployee.setDepartment(employee.getDepartment());
		return detailEmployee;
	}

	public static DetailLeave toDetailLeave(Leave leave) {
		DetailLeave detailLeave = new DetailLeave();
		if(leave.getLeaveResult() != null){//已审批过的
			detailLeave.setApproveDate(leave.getLeaveResult().getApprovalTime());
			detailLeave.setComment(leave.getLeaveResult().getComment());
			detailLeave.setApproveName(leave.getLeaveResult().getApprover().getName());
			detailLeave.setResult(Constant.DIC_MAP_VOUCHER_RESULT.get(leave.getLeaveResult().getResult()));
			detailLeave.setIresult(leave.getLeaveResult().getResult());
		}
		detailLeave.setIstate(leave.getState());
		detailLeave.setState(Constant.DIC_MAP_LEAVE_STATE.get(leave.getState()));
		
		detailLeave.setCreator(leave.getCreator().getName());
		detailLeave.setEndDate(leave.getEndDate());
		detailLeave.setId(leave.getVacationId());
		detailLeave.setLeaveType(leave.getLeaveType());
		detailLeave.setReason(leave.getEvent());
		detailLeave.setStartDate(leave.getStartDate());
		return detailLeave;
	}
	
	private static String getLeaveState (Leave leave){
		if(leave.getLeaveResult() != null){//已审批过的
			if (leave.getLeaveResult().getResult().equals(VoucherResult.REFUSE)){
				//拒绝
				return "未通过";
			}else if (leave.getLeaveResult().getResult().equals(VoucherResult.AGGREE)){
				//同意
				return "已通过";
			}
		} else {//未审批过的
			if (leave.getState() != 0){	//未审批且已过期的
				return "已过期";
			}else {
				return "未审批";
			}
		}	
		return "无效状态";
	}
}
