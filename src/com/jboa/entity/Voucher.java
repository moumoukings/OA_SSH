package com.jboa.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Voucher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "voucher")

public class Voucher implements java.io.Serializable {

	//static field
	public final static Integer UNCOMMIT = 0;
	public final static Integer UNAPPROVE = 1;
	public final static Integer UNPAID = 2;
	public final static Integer PAID = 3;
	public final static Integer REFUSE = 4;
	
	// Fields

	private static final long serialVersionUID = 1695462845419112726L;
	private Integer voucherId;
	private Employee employee;
	private Timestamp createTime;
	private Integer totalCost;
	private String event;
	private Integer state;
	private Set<VoucherDetails> voucherDetailses = new HashSet<VoucherDetails>(0);
	private Set<VoucherResult> voucherResults = new HashSet<VoucherResult>(0);

	// Constructors

	/** default constructor */
	public Voucher() {
	}

	/** minimal constructor */
	public Voucher(String event, Integer state) {
		this.event = event;
		this.state = state;
	}

	/** full constructor */
	public Voucher(Employee employee, Timestamp createTime, Integer totalCost, String event, Integer state,
			Set<VoucherDetails> voucherDetailses, Set<VoucherResult> voucherResults) {
		this.employee = employee;
		this.createTime = createTime;
		this.totalCost = totalCost;
		this.event = event;
		this.state = state;
		this.voucherDetailses = voucherDetailses;
		this.voucherResults = voucherResults;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "voucher_id", unique = true, nullable = false)

	public Integer getVoucherId() {
		return this.voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator")

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "create_Time", length = 19)

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "totalCost")

	public Integer getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}

	@Column(name = "event", nullable = false, length = 200)

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Column(name = "state", nullable = false)

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voucher")

	public Set<VoucherDetails> getVoucherDetailses() {
		return this.voucherDetailses;
	}

	public void setVoucherDetailses(Set<VoucherDetails> voucherDetailses) {
		this.voucherDetailses = voucherDetailses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voucher")

	public Set<VoucherResult> getVoucherResults() {
		return this.voucherResults;
	}

	public void setVoucherResults(Set<VoucherResult> voucherResults) {
		this.voucherResults = voucherResults;
	}

	@Override
	public String toString() {
		return "Voucher [voucherId=" + voucherId + ", employee=" + employee + ", createTime=" + createTime
				+ ", totalCost=" + totalCost + ", event=" + event + ", state=" + state + ", voucherDetailses="
				+ voucherDetailses + ", voucherResults=" + voucherResults + "]";
	}

}