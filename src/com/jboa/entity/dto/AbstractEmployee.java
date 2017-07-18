package com.jboa.entity.dto;

import java.io.Serializable;

public class AbstractEmployee implements Serializable{

	private static final long serialVersionUID = -7312522396015926456L;

	private Integer id;
	private String name;
	private String phoneNum;
	private String address;

	public Integer getId() {		return id;	}
	public void setId(Integer id) {		this.id = id;	}
	public String getName() {		return name;	}
	public void setName(String name) {		this.name = name;	}
	public String getPhoneNum() {		return phoneNum;	}
	public void setPhoneNum(String phoneNum) {		this.phoneNum = phoneNum;	}
	public String getAddress() {		return address;	}
	public void setAddress(String address) {		this.address = address;	}
	
	@Override
	public String toString() {
		return "AbstractEmployee [id=" + id + ", name=" + name + ", phoneNum=" + phoneNum + ", address=" + address
				+ "]";
	}
	
}
