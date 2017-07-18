package com.jboa.entity.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DetailLeave implements Serializable{

	private static final long serialVersionUID = -6493039086924173021L;
	
	//避免重复计算休假时间
	private boolean flag = true;
	//基础显示数据
	private Integer id;
	private String creator;
	private Date startDate;
	private Date endDate;
	private Integer days;
	private String reason;
	private Integer leaveType;
	/**
	 * 状态
	 * 0 时间前
	 * 1 时间内
	 * 2 时间后
	 */
	private Integer istate;
	private String state;
	
	//审批
	private String approveName;
	private String comment;
	private Date approveDate;
	/**
	 * 结果
	 * 0  拒绝
	 * 1 同意
	 */
	private Integer iresult;
	private String result;

	public Integer getIresult() {		return iresult;	}
	public void setIresult(Integer iresult) {		this.iresult = iresult;	}
	public String getResult() {		return result;	}
	public void setResult(String result) {		this.result = result;	}
	public Integer getId() {		return id;	}
	public void setId(Integer id) {		this.id = id;	}
	public String getCreator() {		return creator;	}
	public void setCreator(String creator) {		this.creator = creator;	}
	public Date getStartDate() {		return startDate;	}
	public void setStartDate(Date startDate) {		this.flag = true; this.startDate = startDate;	}
	public Date getEndDate() {		return endDate;	}
	public void setEndDate(Date endDate) {		this.flag = true; this.endDate = endDate;	}
	public Integer getIstate() {		return istate;	}
	public void setIstate(Integer istate) {		this.istate = istate;	}
	//-2未初始化 -1结束时间大于开始时间	
	public Integer getDays() {		
		if (flag){
			if (startDate == null || endDate == null){
				return -2;
			}
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);
			int year = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
			if (year < 0 ){
				return -1;
			}
			days = year * 365 + end.get(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR) + 1;
			flag = false;
		}
		return days;	
	}
	public void setDays(Integer days) {		this.days = days;	}
	public String getReason() {		return reason;	}
	public void setReason(String reason) {		this.reason = reason;	}
	public String getApproveName() {		return approveName;	}
	public void setApproveName(String approveName) {		this.approveName = approveName;	}
	public String getComment() {		return comment;	}
	public void setComment(String comment) {		this.comment = comment;	}
	public Date getApproveDate() {		return approveDate;	}
	public void setApproveDate(Date approveDate) {		this.approveDate = approveDate;	}
	public String getState() {		return state;	}
	public void setState(String state) {		this.state = state;	}
	public Integer getLeaveType() {		return leaveType;	}
	public void setLeaveType(Integer leaveType) {		this.leaveType = leaveType;	}
	
	@Override
	public String toString() {
		return "DetailLeave [flag=" + flag + ", id=" + id + ", creator=" + creator + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", days=" + days + ", reason=" + reason + ", leaveType=" + leaveType
				+ ", istate=" + istate + ", state=" + state + ", approveName=" + approveName + ", comment=" + comment
				+ ", approveDate=" + approveDate + ", iresult=" + iresult + ", result=" + result + "]";
	}
}
