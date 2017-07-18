package com.jboa.util;

import java.util.HashMap;
import java.util.Map;

import com.jboa.entity.Leave;
import com.jboa.entity.Mail;
import com.jboa.entity.Voucher;
import com.jboa.entity.VoucherDetails;
import com.jboa.entity.VoucherResult;

public class Constant {
	
	private Constant(){}
	//报销单报销种类
	public static final Map<Integer, String> DIC_MAP_DETAIL_TYPE = new HashMap<>();
	static {
		DIC_MAP_DETAIL_TYPE.put(VoucherDetails.TYPE_CATERING, "餐饮");
		DIC_MAP_DETAIL_TYPE.put(VoucherDetails.TYPE_CITY_TRAFFIC, "市内交通");
		DIC_MAP_DETAIL_TYPE.put(VoucherDetails.TYPE_DORMITORY, "住宿");
		DIC_MAP_DETAIL_TYPE.put(VoucherDetails.TYPE_GIFT, "礼品");
		DIC_MAP_DETAIL_TYPE.put(VoucherDetails.TYPE_INTER_CITY_TRAFFIC, "城际交通");		
	}
	//报销单的五种状态
	public static final Map<Integer, String > DIC_MAP_VOUCHRE_STATE = new HashMap<>();
	static {
		DIC_MAP_VOUCHRE_STATE.put(Voucher.UNCOMMIT, "未提交");
		DIC_MAP_VOUCHRE_STATE.put(Voucher.UNAPPROVE, "待审核");
		DIC_MAP_VOUCHRE_STATE.put(Voucher.UNPAID, "待付款");
		DIC_MAP_VOUCHRE_STATE.put(Voucher.PAID, "已付款");
		DIC_MAP_VOUCHRE_STATE.put(Voucher.REFUSE, "未通过");
	}
	//报销单审批结果
	public static final Map<Integer, String> DIC_MAP_VOUCHER_RESULT = new HashMap<>();
	static {
		DIC_MAP_VOUCHER_RESULT.put(VoucherResult.AGGREE, "同意");
		DIC_MAP_VOUCHER_RESULT.put(VoucherResult.REFUSE, "拒绝");
	}
	//请假审批结果
	public static final Map<Integer, String> DIC_MAP_LEAVE_STATE = new HashMap<>();
	static {
		DIC_MAP_LEAVE_STATE.put(Leave.STATE_BEFORE, "有效");
		DIC_MAP_LEAVE_STATE.put(Leave.STATE_ON, "有效");
		DIC_MAP_LEAVE_STATE.put(Leave.STATE_AFTER, "已失效");
	}
	//邮件的状态
	public static final Map<Integer, String> DIC_MAP_RECEIVE_MAIL_STATE = new HashMap<>();
	static {
		DIC_MAP_RECEIVE_MAIL_STATE.put(Mail.RECEIVE_UNREAD, "未读");
		DIC_MAP_RECEIVE_MAIL_STATE.put(Mail.RECEIVE_READED, "已读");
		DIC_MAP_RECEIVE_MAIL_STATE.put(Mail.RECEIVE_DESERT, "已删除");		
		DIC_MAP_RECEIVE_MAIL_STATE.put(Mail.RECEIVE_DELETED, "不存在");
	}
	public static final Map<Integer, String> DIC_MAP_SEND_MAIL_STATE = new HashMap<>();
	static {
		DIC_MAP_SEND_MAIL_STATE.put(Mail.SEND_SENDED, "已发送");
		DIC_MAP_SEND_MAIL_STATE.put(Mail.SEND_DESERT, "已删除");
		DIC_MAP_SEND_MAIL_STATE.put(Mail.SEND_DELETED, "不存在");
	}
	//请假的种类
	public static final Map<Integer, String> DIC_MAP_LEAVE_TYPE = new HashMap<>();
	static {
		DIC_MAP_LEAVE_TYPE.put(Leave.SHIJIA, "事假");
		DIC_MAP_LEAVE_TYPE.put(Leave.BINGJIA, "病假");
		DIC_MAP_LEAVE_TYPE.put(Leave.HUNJIA, "婚假");
	}
}
