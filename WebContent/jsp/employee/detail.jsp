<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>  
    <title>员工信息详情</title> 
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
	<link rel="stylesheet" href="${ctx }/css/send-email.css" />     
 </head>
 <body> 
 <div class="wrap">
		<div class="wrap-head">详情</div>
		<div class="wrap-body">
	 <form name="employeeDetail" id="employeeDetail">
		<s:hidden name="detailEmployee.id"></s:hidden> 	
		<div class="info">
		<p>
			<label>姓名：</label>
			<s:textfield name="detailEmployee.name" disabled="true" ></s:textfield>
			<s:hidden id="currentEmpName" name="currentEmpName" value="%{detailEmployee.name}"></s:hidden> 
		</p>
					<p>
			<label>年龄：</label>
			<s:textfield name="detailEmployee.age" disabled="true" cssClass="age"></s:textfield>
		</p>
		<s:if test="%{detailEmployee.id == #session.SYS_EMP.empId}">
			<script> var target = "/jboa2.0/welcome.jsp"; </script>
					<p>
				<label>职位：</label><s:property value="detailEmployee.role.rolename"/>
			</p>
		</s:if>
		<s:elseif test="%{#session.SYS_EMP.role.id == 3}">
			<script> var target="list_init.action"; </script>
			<p>
				<label>职位：</label>
				<s:select name="role" list="roles"  listKey="id" listValue="rolename" value="%{detailEmployee.role.id}" disabled="true"></s:select>
			</p>
		</s:elseif>
		<p>
			<label>手机：</label>
			<s:textfield name="detailEmployee.phoneNum" cssClass="phoneNum" disabled="true" ></s:textfield>
		</p>
					<p>
			<label>性别：</label>
			<s:select name="detailEmployee.sex" list="#{'男': '男', '女' :'女'}" headerKey="%{detailEmployee.sex }" disabled="true"></s:select>
		</p>
					<p>
			<label>住址：</label>
			<s:textfield name="detailEmployee.address" disabled="true" ></s:textfield>
		</p>
					<p>
			<input type="button" value="修改" onclick="alter(this)" name="operate"/>
			<input type="button" value="返回" onclick="javascript:history.go(-1)" name="return"/>
		</div>
	</form>
	</div>
	</div>
 </body>
 <script src="${jquery_js}"></script>
 <script src="${validate_js }"></script>
 <script src="${jquery_form_js }"></script>
 <script >
	$("#employeeDetail").validate({
		rules:{
			"detailEmployee.age": {
				required:true,
				digits:true
			},
			"detailEmployee.name":{
				remote: {
		        	url: "checkNameAvail_ajax.action",
		        	type: "post",
			       	data: {currentEmpName:function (){	return $("#currentEmpName").val(); }}
		      	}
			}
		},
		messages: {
			"detailEmployee.age" : {
				required:"请输入年龄",
				digits:"请输入整数"
			},
			"detailEmployee.name":{
				remote:$.format("用户名已被占用！")
			}
		}
	});
	$.validator.addMethod ("phoneNum", function (value, element){
		var length = value.length; 
		var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/ 
		return this.optional(element) || (length == 11 && mobile.test(value)); 
	},"手机号码必须已13或15开头的11位整数");
 	$.validator.addMethod ("age", function (value, element){
		var age = /^\d{2,3}$/ 
		return this.optional(element) || (value<100 && value>20 && age.test(value)); 
	},"年龄范围必须在20至100之间");
	
	function alter (ele){
		$(":disabled").removeAttr ("disabled");
		if ($(ele).val() == "保存"){
			if ($("#employeeDetail").valid()){
				alert ("验证通过");
				var options = {
					url : "update_ajax.action",
					success: function (data){
						if (data.success)
							location.href= target;
						else 
							alert("更新失败请稍后再试,错误信息为：" + data.msg);
						if (data.refresh){
							parent.parent.frames["topFrame"].history.go(0);
						}
					},
					error: function (state){
						alert ("发生错误 :" + state);
					},
					contentType: "application/x-www-form-urlencoded; charset=utf-8" 
				};
				$("#employeeDetail").ajaxSubmit(options);				
			}
		}
		$(ele).val("保存");
	}
 </script>
</html>