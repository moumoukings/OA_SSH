package com.jboa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MailContent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mail_content")

public class MailContent implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -5483822932659152490L;
	private Integer contentId;
	private String content;
	private String attachFile;

	// Constructors

	/** default constructor */
	public MailContent() {
	}

	/** full constructor */
	public MailContent(String content, String attachFile) {
		this.content = content;
		this.attachFile = attachFile;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "content_id", unique = true, nullable = false)

	public Integer getContentId() {
		return this.contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	@Column(name = "content")

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "attach_file")

	public String getAttachFile() {
		return this.attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	@Override
	public String toString() {
		return "MailContent [contentId=" + contentId + ", content=" + content + ", attachFile=" + attachFile + "]";
	}
}