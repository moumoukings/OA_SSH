<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>  
    <title>邮件阅读页</title>      
	<link rel="stylesheet" href="${ctx }/css/send-email.css" /> 
 </head>
 <body>
 	<script src="${jquery_js  }"></script>
 <div class="wrap">
		<div class="wrap-head">邮件详情</div>
		<div class="wrap-body">
			<form name="myForm" id="myForm" action="" method="post" enctype="multipart/form-data">
				<div class="info">
					<p>
						<span>&#12288;发件人:</span>
						<s:hidden name="detailMail.sendName" id="rname"></s:hidden>
						<s:hidden name="detailMail.senderId" id="rid"></s:hidden>
						<s:property value="detailMail.sendName"/>
					</p>
					<p>
						<span>&#12288;收件人:</span>
						<s:hidden name="detailMail.receiverName" id="rname"></s:hidden>
						<s:hidden name="detailMail.receiverId" id="rid"></s:hidden>
						<s:property value="detailMail.receiverName"/>
					</p>
					<p>
						<span>邮件标题:</span>
						<s:property value="detailMail.title"/>
					</p>
					<p>
						<span>邮件内容:</span>
						<textarea name="detailMail.content" cols="30" rows="10"><s:property value="detailMail.content"></s:property></textarea>
					</p>
					<s:if test="%{detailMail.filename != null}">
					<p>
						<span>附件:</span> 
						<a href="/jboa2.0/mail/download.action?fileName=<s:property value='%{detailMail.filename}'/>">点击下载附件</a>
					</p>
					</s:if>
				</div>
				<s:if test="%{#session.SYS_EMP.empId == detailMail.receiverId}">
					<div class="btn">
						<input type="button" onclick="reback()" value="回信" />
					</div>
				</s:if>
				<s:else>
				 	<div class="btn">
						<input type="button" onclick="javascript:history.go(-1)" value="返回" />
					</div>
				</s:else>
			</form>
		</div>
	</div>
 </body>
 
 <script>
	function reback (){
		var url = "/jboa2.0/jsp/mail/backMail.jsp?receiverName="+$("#rname").val()+"&receiverId="+$("#rid").val();
		location.href = url;
	}
 </script>
</html>