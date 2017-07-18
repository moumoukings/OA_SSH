package com.jboa.entity.condition;

import java.io.Serializable;

public class MailCondition implements Serializable{

	private static final long serialVersionUID = -5380897967927547478L;
	private Integer receiverId;
	private Integer senderId;
	private Integer receiveState;
	private Integer sendState;
	
	public Integer getReceiveState() {		return receiveState;	}
	public void setReceiveState(Integer receiveState) {		this.receiveState = receiveState;	}
	public Integer getSendState() {		return sendState;	}
	public void setSendState(Integer sendState) {		this.sendState = sendState;	}
	public Integer getReceiverId() {		return receiverId; }
	public void setReceiverId(Integer receiverId) {		this.receiverId = receiverId;	}
	public Integer getSenderId() {		return senderId;	}
	public void setSenderId(Integer senderId) {		this.senderId = senderId;	}
	@Override
	public String toString() {
		return "MailCondition [receiverId=" + receiverId + ", senderId=" + senderId + ", receiveState=" + receiveState
				+ ", sendState=" + sendState + "]";
	}
}
