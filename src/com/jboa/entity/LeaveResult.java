package com.jboa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LeaveResult entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "leave_result")

public class LeaveResult implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 4922724390534834085L;
	private Integer lresutlId;
	private Employee approver;
	private String comment;
	private Date approvalTime;
	private Integer result;

	// Constructors

	/** default constructor */
	public LeaveResult() {
	}

	/** full constructor */
	public LeaveResult( Employee approver, String comment, Date approvalTime, Integer result) {
		this.approver = approver;
		this.comment = comment;
		this.approvalTime = approvalTime;
		this.result = result;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "l_resutl_id", unique = true, nullable = false)

	public Integer getLresutlId() {
		return this.lresutlId;
	}

	public void setLresutlId(Integer LResutlId) {
		this.lresutlId = LResutlId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approver", nullable = false)

	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	@Column(name = "comment", nullable = false, length = 200)

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "approval_Time", nullable = false, length = 19)

	public Date getApprovalTime() {
		return this.approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	@Column(name = "result", nullable = false)

	public Integer getResult() {
		return this.result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "LeaveResult [lresutlId=" + lresutlId + ", employee=" + approver + ", comment="
				+ comment + ", approvalTime=" + approvalTime + ", result=" + result + "]";
	}
	
}