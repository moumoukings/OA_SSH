package com.jboa.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class DetailMail implements Serializable{

	private static final long serialVersionUID = -6428335617643450017L;

	private Integer mailId;
	private String title;
	private String content;
	private Date sendTime;
	private Integer senderId;
	private String sendName;
	private Integer receiverId;
	private String receiverName;
	private String filename;
	
	public Integer getMailId() {		return mailId;	}
	public void setMailId(Integer mailId) {		this.mailId = mailId;	}
	public String getTitle() {		return title;	}
	public void setTitle(String title) {		this.title = title;	}
	public String getContent() {		return content;	}
	public void setContent(String content) {		this.content = content;	}
	public Date getSendTime() {		return sendTime;	}
	public void setSendTime(Date sendTime) {		this.sendTime = sendTime;	}
	public Integer getSenderId() {		return senderId;	}
	public void setSenderId(Integer senderId) {		this.senderId = senderId;	}
	public String getSendName() {		return sendName;	}
	public void setSendName(String sendName) {		this.sendName = sendName;	}
	public Integer getReceiverId() {		return receiverId;	}
	public void setReceiverId(Integer receiverId) {		this.receiverId = receiverId;}	
	public String getReceiverName() {		return receiverName;	}
	public void setReceiverName(String receiverName) {		this.receiverName = receiverName;	}
	public String getFilename() {		return filename;	}
	public void setFilename(String filename) {		this.filename = filename;	}
	
	@Override
	public String toString() {
		return "DetailMail [mailId=" + mailId + ", title=" + title + ", content=" + content + ", sendTime=" + sendTime
				+ ", senderId=" + senderId + ", sendName=" + sendName + ", receiverId=" + receiverId + ", receiverName="
				+ receiverName + ", filename=" + filename + "]";
	}
	
}
