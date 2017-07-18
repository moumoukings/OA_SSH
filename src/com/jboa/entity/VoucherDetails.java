package com.jboa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * VoucherDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "voucher_details")

public class VoucherDetails implements java.io.Serializable {

	
	//static field
	public static final Integer TYPE_CITY_TRAFFIC = 0;	//市内交通
	public static final Integer TYPE_INTER_CITY_TRAFFIC = 1;	//城际交通
	public static final Integer TYPE_CATERING = 2;	//餐饮
	public static final Integer TYPE_DORMITORY = 3;	//住宿
	public static final Integer TYPE_GIFT = 4;	//礼品
	
	// Fields

	private static final long serialVersionUID = -8295499356427904714L;
	private Integer detailId;
	private Voucher voucher;
	private Integer type;
	private Integer cost;
	private String remarks;

	// Constructors

	/** default constructor */
	public VoucherDetails() {
	}

	/** minimal constructor */
	public VoucherDetails(Voucher voucher, Integer type, Integer cost) {
		this.voucher = voucher;
		this.type = type;
		this.cost = cost;
	}

	/** full constructor */
	public VoucherDetails(Voucher voucher, Integer type, Integer cost, String remarks) {
		this.voucher = voucher;
		this.type = type;
		this.cost = cost;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "detail_id", unique = true, nullable = false)

	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voucher_id", nullable = false)

	public Voucher getVoucher() {
		return this.voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	@Column(name = "type", nullable = false)

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "cost", nullable = false)

	public Integer getCost() {
		return this.cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	@Column(name = "remarks", length = 20)

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "VoucherDetails [detailId=" + detailId + ", voucher=" + voucher + ", type=" + type + ", cost=" + cost
				+ ", remarks=" + remarks + "]";
	}

}