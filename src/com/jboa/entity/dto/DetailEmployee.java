package com.jboa.entity.dto;

import java.io.Serializable;

import com.jboa.entity.Department;
import com.jboa.entity.Role;

public class DetailEmployee implements Serializable{

	private static final long serialVersionUID = 6969971114640877003L;
	
	private Integer id;
	private String name;
	private String password;
	private Integer age;
	private String sex;
	private String phoneNum;
	private String address;
	private Role role;
	private Department department;
	
	public Department getDepartment() {		return department;	}
	public void setDepartment(Department department) {		this.department = department;	}
	public Integer getId() {		return id;	}
	public void setId(Integer id) {		this.id = id;	}
	public String getName() {		return name;	}
	public void setName(String name) {		this.name = name;	}
	public Integer getAge() {		return age;	}
	public void setAge(Integer age) {		this.age = age;	}
	public String getSex() {		return sex;	}
	public void setSex(String sex) {		this.sex = sex;	}
	public String getPhoneNum() {		return phoneNum;	}
	public void setPhoneNum(String phoneNum) {		this.phoneNum = phoneNum;	}
	public String getAddress() {		return address;	}
	public void setAddress(String address) {		this.address = address;	}
	public String getPassword() {		return password;	}
	public void setPassword(String password) {		this.password = password;	}
	public Role getRole() {		return role;	}
	public void setRole(Role role) {		this.role = role;	}

	@Override
	public String toString() {
		return "DetailEmployee [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age + ", sex="
				+ sex + ", phoneNum=" + phoneNum + ", address=" + address + ", role=" + role + "]";
	}
}
