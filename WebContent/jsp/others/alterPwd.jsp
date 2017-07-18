<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>  
  	<meta charset="utf-8">
    <title>修改密码</title> 
	<link rel="stylesheet" href="${ctx }/css/send-email.css" />       
 </head>
 <body>
  <div class="wrap">
		<div class="wrap-head">修改密码</div>
		<div class="wrap-body">
 		<form id="alterPassword" action="/jboa2.0/employee/alterPwd_operate.action">
 		<div class="info">
 		<p>
		 	旧密码：<input type="password" id="oldPassword" name="oldPassword" class="password" onblur="verifyOldPwd()">
		 	<span id="errorMsg"></span>
		 	</p><p>
		 	<br/>新密码：<input type="password" id="newPassword" name="newPassword" class="password"><br/>
		 	</p><p>
		 	请确认：<input type="password" id="repeatPassword" name="repeatPassword" class="password"><br/>
		 	</p><p>
		 	<input type="button" value="确认修改" onclick="alter()">
		 	</p>
		 	</div>
	 	</form>
	 </div>
	 </div>
 </body>
	<script src="${jquery_js}"></script>
	<script src="${validate_js }"></script>
	<script src="${jquery_form_js }"></script>
	<script>
		$("#alterPassword").validate({
			rules:{
				"repeatPassword":{
					equalTo: "#newPassword"
				}
			},
			messages:{
				"repeatPassword":{
					equalTo: "两次输入不一致，请确认后再提交"
				}
			}
		});
		$.validator.addMethod ("password", function (value, element){
			var length = value.length; 
			var pwd = /^[\da-zA-Z]+$/ 
			return this.optional(element) || (length >3 && pwd.test(value)); 
		},"密码必须为三位以上（只支持数字，字母）");
		function verifyOldPwd(){
			var pwd = $("#oldPassword").val();
			if (!pwd.match())
				return;
			$().validate();
			$.ajax({
				url: "/jboa2.0/employee/verifyPwd_ajax.action",
				data:{password: pwd},
				success:function (data){
					if (!data.success){
						$("#errorMsg").html(data.msg);
					} else{
						$("#errorMsg").html(data.msg);
					}
				}
			}).error(function (state){alert(state);});
		}
		function alter(){
			if ($("#alterPassword").valid()){
				$("#alterPassword").submit();
			}
		}
	</script>
</html>