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
 * VoucherResult entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "voucher_result")

public class VoucherResult implements java.io.Serializable {


	//static field
	public final static Integer REFUSE = 0;
	public final static Integer AGGREE = 1;
	
	// Fields

	private static final long serialVersionUID = 5021001028471801927L;
	private Integer vresultId;
	private Employee employee;
	private Voucher voucher;
	private String comment;
	private Date approvalTime;
	private Integer result;

	// Constructors

	/** default constructor */
	public VoucherResult() {
	}

	/** minimal constructor */
	public VoucherResult(String comment, Date approvalTime, Integer result) {
		this.comment = comment;
		this.approvalTime = approvalTime;
		this.result = result;
	}

	/** full constructor */
	public VoucherResult(Employee employee, Voucher voucher, String comment, Date approvalTime, Integer result) {
		this.employee = employee;
		this.voucher = voucher;
		this.comment = comment;
		this.approvalTime = approvalTime;
		this.result = result;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "v_result_id", unique = true, nullable = false)

	public Integer getVresultId() {
		return this.vresultId;
	}

	public void setVresultId(Integer VresultId) {
		this.vresultId = VresultId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approver")

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voucher_id")

	public Voucher getVoucher() {
		return this.voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
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
		return "VoucherResult [vresultId=" + vresultId + ", employee=" + employee + ", voucher=" + voucher
				+ ", comment=" + comment + ", approvalTime=" + approvalTime + ", result=" + result + "]";
	}

}