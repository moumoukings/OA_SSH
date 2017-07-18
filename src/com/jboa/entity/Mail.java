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
 * Mail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mail")

public class Mail implements java.io.Serializable {

	//static fields
	
	public final static Integer RECEIVE_UNREAD = 0;
	public final static Integer RECEIVE_READED = 1;
	public final static Integer RECEIVE_DESERT = 2;
	public final static Integer RECEIVE_DELETED = 3;
	
	public final static Integer SEND_SENDED = 4;
	public final static Integer SEND_DESERT = 5;
	public final static Integer SEND_DELETED = 6;
	// Fields

	private static final long serialVersionUID = 5525651850909077044L;
	private Integer mailId;
	private Employee receiver;
	private Employee sender;
	private MailContent mailContent;
	private Date createTime;
	private Integer receiveState;
	private Integer sendState;
	private String title;

	// Constructors

	/** default constructor */
	public Mail() {
	}

	/** full constructor */
	public Mail(Integer mailId, Employee receiver, Employee sender, MailContent mailContent, Date createTime,
			Integer receiveState, Integer sendState, String title) {
		super();
		this.mailId = mailId;
		this.receiver = receiver;
		this.sender = sender;
		this.mailContent = mailContent;
		this.createTime = createTime;
		this.receiveState = receiveState;
		this.sendState = sendState;
		this.title = title;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "mail_id", unique = true, nullable = false)

	public Integer getMailId() {
		return this.mailId;
	}

	
	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver")
	public Employee getReceiver() {
		return receiver;
	}

	public void setReceiver(Employee receiver) {
		this.receiver = receiver;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender")
	public Employee getSender() {
		return sender;
	}

	public void setSender(Employee sender) {
		this.sender = sender;
	}	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_id")

	public MailContent getMailContent() {
		return this.mailContent;
	}

	public void setMailContent(MailContent mailContent) {
		this.mailContent = mailContent;
	}

	@Column(name = "title", length = 30)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "create_time", length = 19)

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "receive_state", nullable = false)
	public Integer getReceiveState() {
		return receiveState;
	}

	public void setReceiveState(Integer receiveState) {
		this.receiveState = receiveState;
	}
	@Column(name = "send_state", nullable = false)
	public Integer getSendState() {
		return sendState;
	}

	public void setSendState(Integer sendState) {
		this.sendState = sendState;
	}

	@Override
	public String toString() {
		return "Mail [mailId=" + mailId + ", createTime=" + createTime + ", receiveState=" + receiveState
				+ ", sendState=" + sendState + ", title=" + title + "]";
	}
}