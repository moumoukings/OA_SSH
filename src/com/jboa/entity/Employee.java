package com.jboa.entity;

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
 * Employee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employee")

public class Employee implements java.io.Serializable {

	private static final long serialVersionUID = -9127455123734257787L;

	// Fields
	private Integer empId;
	private Department department;
	private Role role;
	private String name;
	private String password;
	private Integer age;
	private String phoneNum;
	private String address;
	private String sex;
	private Integer state;
	private Set<LeaveResult> leaveResults = new HashSet<LeaveResult>(0);
	private Set<Leave> leaves = new HashSet<Leave>(0);
	private Set<Mail> mailsForReceiver = new HashSet<Mail>(0);
	private Set<Mail> mailsForSender = new HashSet<Mail>(0);

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public Employee(Department department, Role role, String name, String password, Set<LeaveResult> leaveResults,
			Set<Leave> leaves, Set<Mail> mailsForReceiver, Set<Mail> mailsForSender) {
		this.department = department;
		this.role = role;
		this.name = name;
		this.password = password;
		this.leaveResults = leaveResults;
		this.leaves = leaves;
		this.mailsForReceiver = mailsForReceiver;
		this.mailsForSender = mailsForSender;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "emp_id", unique = true, nullable = false)

	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept")

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role")

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "name", nullable = false, length = 32)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "password", length = 40)

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "age", nullable = false, length = 11)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Column(name = "phoneNum", nullable = false, length = 12)
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Column(name = "address", nullable = false, length = 40)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "sex", nullable = false, length = 4)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "approver")

	public Set<LeaveResult> getLeaveResults() {
		return this.leaveResults;
	}

	public void setLeaveResults(Set<LeaveResult> leaveResults) {
		this.leaveResults = leaveResults;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creator")

	public Set<Leave> getLeaves() {
		return this.leaves;
	}

	public void setLeaves(Set<Leave> leaves) {
		this.leaves = leaves;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "receiver")

	public Set<Mail> getMailsForReceiver() {
		return this.mailsForReceiver;
	}

	public void setMailsForReceiver(Set<Mail> mailsForReceiver) {
		this.mailsForReceiver = mailsForReceiver;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sender")

	public Set<Mail> getMailsForSender() {
		return this.mailsForSender;
	}

	public void setMailsForSender(Set<Mail> mailsForSender) {
		this.mailsForSender = mailsForSender;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", password=" + password + ", age=" + age + ", phoneNum="
				+ phoneNum + ", address=" + address + ", sex=" + sex + "]";
	}

}