package com.jboa.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class AbstractMail implements Serializable{

	private static final long serialVersionUID = -6592903828372964530L;
	
	private Integer id;
	private String title;
	private String content;
	private String receiveState;
	private String sendState;
	private Date time;
	//判断是否在回收站
	private boolean deleted;
	//判断是否是发件箱
	private boolean send;
	
	public boolean isSend() {		return send;	}
	public void setSend(boolean send) {		this.send = send;	}
	public boolean isDeleted() {		return deleted;	}
	public void setDeleted(boolean deleted) {		this.deleted = deleted;	}
	public Integer getId() {		return id;	}
	public void setId(Integer id) {		this.id = id;	}
	public String getTitle() {		return title;	}
	public void setTitle(String title) {		this.title = title;	}
	public String getContent() {		
		if (content.length() >10){
			return content.substring(0, 10).concat("...");
		}
		return content;	
	}
	public void setContent(String content) {		this.content = content;	}
	public Date getTime() {		return time;	}
	public void setTime(Date time) {		this.time = time;	}
	public String getReceiveState() {		return receiveState;	}
	public void setReceiveState(String receiveState) {		this.receiveState = receiveState;	}
	public String getSendState() {		return sendState;	}
	public void setSendState(String sendState) {		this.sendState = sendState;	}
	@Override
	public String toString() {
		return "AbstractMail [id=" + id + ", title=" + title + ", content=" + content + ", receiveState=" + receiveState
				+ ", sendState=" + sendState + ", time=" + time + "]";
	}
}
