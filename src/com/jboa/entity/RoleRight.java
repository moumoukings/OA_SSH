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
 * RoleRight entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role_right")

public class RoleRight implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 5555409054785590741L;
	private Integer id;
	private Right right;
	private Role role;

	// Constructors

	/** default constructor */
	public RoleRight() {
	}

	/** full constructor */
	public RoleRight(Right right, Role role) {
		this.right = right;
		this.role = role;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "right_code")

	public Right getRight() {
		return this.right;
	}

	public void setRight(Right right) {
		this.right = right;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "RoleRight [id=" + id + ", right=" + right + ", role=" + role + "]";
	}

}