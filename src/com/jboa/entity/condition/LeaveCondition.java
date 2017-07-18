package com.jboa.entity.condition;

import java.io.Serializable;

public class LeaveCondition implements Serializable{
	private static final long serialVersionUID = 1395520725930708952L;
	/**
	 * 休假类型，参见LeaveType常量
	 */
	private Integer type;
	/**
	 * 0  请假时间前
	 * 1  请假时间内
	 * 2  请假时间后
	 */
	private Integer state;
	/**
	 * result
	 * 0 表示拒绝
	 * 1 表示同意
	 * 2 表示无此条件
	 * 3 时表示不为空
	 * null 表示无结果
	 */
	private Integer result;

	public Integer getResult() {		return result;	}
	public void setResult(Integer result) {		this.result = result;	}
	public Integer getType() {		return type;	}
	public void setType(Integer type) {		this.type = type;	}
	public Integer getState() {		return state;	}
	public void setState(Integer state) {		this.state = state;	}
	@Override
	public String toString() {
		return "LeaveCondition [type=" + type + ", state=" + state + ", result=" + result + "]";
	}
}