package com.jboa.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Right entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "right_r")

public class Right implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 479555102333590860L;
	private String rightCode;
	private String rightParentCode;
	private String rightTitle;
	private String rightUrl;
	private Set<RoleRight> roleRights = new HashSet<RoleRight>(0);

	// Constructors

	/** default constructor */
	public Right() {
	}

	/** full constructor */
	public Right(String rightParentCode, String rightTitle, String rightUrl, Set<RoleRight> roleRights) {
		this.rightParentCode = rightParentCode;
		this.rightTitle = rightTitle;
		this.rightUrl = rightUrl;
		this.roleRights = roleRights;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "right_code", unique = true, nullable = false, length = 20)

	public String getRightCode() {
		return this.rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	@Column(name = "right_parent_code", length = 20)

	public String getRightParentCode() {
		return this.rightParentCode;
	}

	public void setRightParentCode(String rightParentCode) {
		this.rightParentCode = rightParentCode;
	}

	@Column(name = "right_title", length = 50)

	public String getRightTitle() {
		return this.rightTitle;
	}

	public void setRightTitle(String rightTitle) {
		this.rightTitle = rightTitle;
	}

	@Column(name = "right_url", length = 100)

	public String getRightUrl() {
		return this.rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "right")

	public Set<RoleRight> getRoleRights() {
		return this.roleRights;
	}

	public void setRoleRights(Set<RoleRight> roleRights) {
		this.roleRights = roleRights;
	}

	@Override
	public String toString() {
		return "Right [rightCode=" + rightCode + ", rightParentCode=" + rightParentCode + ", rightTitle=" + rightTitle
				+ ", rightUrl=" + rightUrl + "]";
	}

}