package com.jboa.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Leave entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "leave_l")

public class Leave implements java.io.Serializable {

	//static fields
	public static final Integer SHIJIA = 0;
	public static final Integer HUNJIA = 1;
	public static final Integer BINGJIA =2;
	
	public static final Integer STATE_BEFORE = 0;
	public static final Integer STATE_ON = 1;
	public static final Integer STATE_AFTER = 2;
	// Fields
	private static final long serialVersionUID = 5011682450775247953L;
	private Integer vacationId;
	private Employee creator;
	private LeaveResult leaveResult;
	private Date createTime;
	private Date startDate;
	private Date endDate;
	private String event;
	private Integer leaveType;
	private Integer state;

	// Constructors

	/** default constructor */
	public Leave() {
	}

	/** minimal constructor */
	public Leave(Date startDate, Date endDate, String event) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.event = event;
	}

	/** full constructor */
	public Leave(Employee creator, LeaveResult leaveResult, Date createTime, Date startDate, Date endDate,
			String event, Integer leaveType, Integer state) {
		this.creator = creator;
		this.leaveResult = leaveResult;
		this.createTime = createTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.event = event;
		this.leaveType = leaveType;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "vacation_id", unique = true, nullable = false)

	public Integer getVacationId() {
		return this.vacationId;
	}

	public void setVacationId(Integer vacationId) {
		this.vacationId = vacationId;
	}

	@Column(name="state", nullable=true)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator")

	public Employee getCreator() {
		return this.creator;
	}

	public void setCreator(Employee creator) {
		this.creator = creator;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "l_resutl_id")

	public LeaveResult getLeaveResult() {
		return this.leaveResult;
	}

	public void setLeaveResult(LeaveResult leaveResult) {
		this.leaveResult = leaveResult;
	}

	@Column(name = "create_Time", length = 19)

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "leaveType", length = 11)
	public Integer getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(Integer leaveType) {
		this.leaveType = leaveType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_Date", nullable = false, length = 10)

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_Date", nullable = false, length = 10)

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "event", nullable = false, length = 200)

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Leave [vacationId=" + vacationId + ", createTime=" + createTime + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", event=" + event + ", leaveType=" + leaveType + ", state=" + state + "]";
	}

}