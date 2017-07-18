<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>请假申请</title>
	<link rel="stylesheet" href="${ctx }/css/send-email.css" /> 
 </head>
 <body>
 	<script src="${jquery_js  }"></script>
 <div class="wrap">
		<div class="wrap-head">申请休假</div>
		<div class="wrap-body">
		<form name="leaveDetail" id="leaveDetail">
			<s:hidden name="detailLeave.id"></s:hidden>
			<div class="info">
			<p>
				<label>申请人：</label>
				<s:property value="#session.SYS_EMP.name" />
			</p><p>
				<label>开始时间：</label> 
				<input type="text" name="detailLeave.startDate" class="Wdate" 
					onfocus="WdatePicker({
						dateFmt: 'yyyy-MM-dd',
						minDate:'%y-%M-{%d+1}',
						maxDate: '%y-%M-{%d+30}',
						onpicked: function(){ $dp.$('endDate').focus()}
						})" 
				id="startDate"/>
			</p><p>
				<label>结束时间</label> 
				<input type="text" name="detailLeave.endDate" class="Wdate" 
					onfocus="WdatePicker({
						dateFmt: 'yyyy-MM-dd',
						minDate: '#F{$dp.$D(\'startDate\',{d:0})}', 
						maxDate: '#F{$dp.$D(\'startDate\',{y:1})}',
						onpicked: autoSum('startDate', 'endDate', 'totalDay')})" 
				id="endDate">
			</p><p>
				<label>共计时间(单位:天)：</label> 
				<input type="text" name="detailLeave.days" id="totalDay" readonly="readonly"/>
			</p><p>		
				<label>类型：</label>
				<s:select name="detailLeave.leaveType" list="types" headerKey="detailLeave.leaveType"></s:select>
			</p><p>
				<label>请假事由：</label>
				<s:textarea cssClass="reason" name="detailLeave.reason" ></s:textarea>
			</p><p>
				<input type="button" value="提交" name="operate" onclick="commit()"/>
				<input type="button" value="返回" onclick="javascript:history.go(-1)"
					name="return" />
			</p>
			</div>
		</form>
				</div>
	</div>
</body>
<script src="${WdatePicker_js }"></script>
<script src="${jquery_form_js }"></script>
<script src="${ctx }/js/leave/common.js"></script>
<script>
	function commit(){
		var options = {
				url : "saveOrUpdate_ajax.action",
				success: function (data){
					if (data.success)
						location.href= "applyList_init.action";
					else 
						alert("更新失败请稍后再试,错误信息为：" + data.msg);
				},
				error: function (state){
					alert ("发生错误 :" + state);
				},
				contentType: "application/x-www-form-urlencoded; charset=utf-8" 
			};
		$("#totalDay").val('');
		$("#leaveDetail").ajaxSubmit(options);	
	}
</script>
</html>