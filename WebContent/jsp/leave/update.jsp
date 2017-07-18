<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>  
    <title>请假详情</title> 
        <!-- 
	    	用户修改（未审批时）请假详情
	    	经理审批请假
	    	经理修改已审批的未过期的请假
     	-->    
	<script src="${jquery_js }"></script>  
	<script src="${WdatePicker_js }"></script>
	<script src="${jquery_form_js }"></script>
	<link rel="stylesheet" href="${ctx }/css/send-email.css" /> 
</head>
<body>
 <div class="wrap">
		<div class="wrap-head">更新请假</div>
		<div class="wrap-body">
		<form name="leaveDetail" id="leaveDetail">
			<s:hidden name="detailLeave.id" id="leaveId"></s:hidden>
			<div class="info">
			<p>
				<label>申请人：</label>
				<s:property value="detailLeave.creator"/>
			</p><p>
				<label>开始时间：</label>
				<s:textfield  disabled="true"
					 name="detailLeave.startDate" cssClass="Wdate" 
						onfocus="WdatePicker({
							dateFmt: 'yyyy-MM-dd',
							minDate:'%y-%M-{%d+1}',
							maxDate: '%y-%M-{%d+30}',
							onpicked: function(){ $dp.$('endDate').focus()}
							})" 
						id="startDate">
						<s:param name="value" ><s:date name="detailLeave.startDate" format="yyyy-MM-dd" /></s:param> 
				</s:textfield>
						</p><p>
				<label>结束时间：</label>
				<s:textfield  disabled="true"
					 name="detailLeave.endDate" cssClass="Wdate" 
						onfocus="WdatePicker({
							dateFmt: 'yyyy-MM-dd',
							minDate: '#F{$dp.$D(\\'startDate\\',{d:0})}', 
							maxDate: '#F{$dp.$D(\\'startDate\\',{y:1})}',
							onpicked: autoSum('startDate', 'endDate', 'totalDay')})" 
						id="endDate">
						<s:param name="value" ><s:date name="detailLeave.endDate" format="yyyy-MM-dd" /></s:param>
				</s:textfield>
						</p><p>
				<label>请假天数：</label>
				<s:textfield disabled="true"  name="detailLeave.days" id="totalDay" readonly="true"></s:textfield>
						</p><p>
				<label>类型：</label>
				<s:select name="detailLeave.leaveType" list="types" 
					headerKey="detailLeave.leaveType"
					disabled="true">
				</s:select>
						</p><p>
				<label>请假事由：</label>
				<s:textarea cssClass="reason" name="detailLeave.reason" disabled="true"></s:textarea>
						</p><hr><p>
				<label>审批状态：</label>
				<s:property value="detailLeave.result"/>
						</p><p>
				<label>审批意见：</label>
				<s:textarea id="comment" name="detailLeave.comment" disabled="true"></s:textarea>
						</p><p>
				<s:if test="%{#session.SYS_EMP.role.id == 2 || #session.SYS_EMP.role.id == 3}">
					<s:if test="%{detailLeave.istate != 2 && detailLeave.creator != #session.SYS_EMP.name}">
						<s:if test="%{detailLeave.result == null}">
							<script>
								$("#comment").removeAttr("disabled");
								var target = "approveList_init.action";
							</script>
						</s:if>
						<s:else>
							<script>
								$("#comment").removeAttr("disabled");
								var target = "approvedList_init.action";
							</script>
						</s:else>
						<input type="button" value="通过" name="operate" onclick="aggree()"/>
						<input type="button" value="拒绝" name="operate" onclick="refuse()"/>
					</s:if>
				</s:if>
				<s:elseif test="%{detailLeave.istate != 2 && detailLeave.result == null }">
					<script src="${ctx }/js/leave/common.js"></script>  
					<!-- 本人修改未审批 -->
					<script>
						/* 将审批信息设为disabled */
						$(":disabled").removeAttr("disabled");
						$("#comment").attr("disabled", "disabled");
						var target = "applyList_init.action";
					</script>
					<input type="button" value="更新" name="operate" onclick="update()">
				</s:elseif>
				<input type="button" value="返回" onclick="javascript:history.go(-1)" 	name="return" />
			</div>
		</form>
	</div>
	</div>
	
	</body>
	<script>
		function aggree (){
			var url = "approve_ajax.action";
			var data = {"detailLeave.istate":1};
			commit (url ,data);
		}
		function refuse (){
			var url = "approve_ajax.action";
			var data = {"detailLeave.istate":0};
			commit (url ,data);
		}
		function update (){
			var url = "saveOrUpdate_ajax.action";
			commit (url,{});
		}
		
		function commit(action, data){
			var options = {
					url : action,
					success: function (data){
						if (data.success)
							location.href = target;
						else 
							alert("更新失败请稍后再试,错误信息为：" + data.msg);
					},
					data:data,
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