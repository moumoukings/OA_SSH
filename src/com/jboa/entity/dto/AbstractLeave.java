package com.jboa.entity.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class AbstractLeave implements Serializable{

	private static final long serialVersionUID = -2516973064363231930L;
	
	//避免重复计算休假时间
	private boolean flag = true;
	
	private Integer id;
	private String creator;
	private Date startDate;
	private Date endDate;
	private Integer days;
	private String state;
	private String type;
	
	public Integer getId() {		return id;	}
	public void setId(Integer id) {		this.id = id;	}
	public Date getStartDate() {		return startDate;	}
	public void setStartDate(Date startDate) {		this.flag = true; this.startDate = startDate;	}
	public Date getEndDate() {		return endDate;	}
	public void setEndDate(Date endDate) {			this.flag = true; this.endDate = endDate;	}
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
	public String getState() {		return state;	}
	public void setState(String state) {		this.state = state;	}
	public String getType() {		return type;	}
	public void setType(String type) {		this.type = type;	}
	public String getCreator() {		return creator;	}
	public void setCreator(String creator) {		this.creator = creator;	}
	@Override
	public String toString() {
		return "AbstractLeave [id=" + id + ", creator=" + creator + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", days=" + days + ", state=" + state + ", type=" + type + "]";
	}
}
