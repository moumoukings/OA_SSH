<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/common/taglibs.jsp"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>回复邮件</title>
<link rel="stylesheet" href="${ctx }/css/reset.css" />
<link rel="stylesheet" href="${ctx }/css/send-email.css" />
</head>
<body>
	<script src="${jquery_js  }"></script>
	<script src="${jquery_form_js }"></script>
	<script src="${validate_js }"></script>
	<%
		String rname = request.getParameter("receiverName");
		String rid = request.getParameter("receiverId");
	%>
	<div class="wrap">
		<div class="wrap-head">回邮件</div>
		<div class="wrap-body">
			<form name="myForm" id="myForm" action="" method="post" enctype="multipart/form-data">
				<div class="info">
					<p>
						<span>&#12288;收件人:</span>
						<input name="detailMail.receiverName" value="<%=rname %>" readonly="readonly"/>
						<input name="detailMail.receiverId" id="emps" value="<%=rid %>" type="hidden">
					</p>
					<p>
						<span>邮件标题:</span><input name="detailMail.title" type="text"
							required />
					</p>
					<p>
						<span>邮件内容:</span>
						<textarea name="detailMail.content" cols="30" rows="10"></textarea>
					</p>
					<p>
						<span>上传附件:</span> <input id="file1" class="file1" type="file"
							name="upload" /> <span id="span1">(上传附件不能大于9M)</span>
					</p>
				</div>
				<div class="btn">
					<input type="button" id="btnsave" value="发送邮件" />
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function () {
		jQuery.validator.addMethod("file1", function(value, element) {
			var fileSize = null;
			if ($(".file1").val() != "") {
				fileSize = element.files[0].size;
			} else {
				fileSize = 0;
			}
			var maxSize = 9 * 1024 * 1024;
			if (fileSize > maxSize) {
				return false;
			} else {
				return true;
			}
		}, "请上传大小在9M以下的文件");

		$("#myForm").validate({
			rules : {
				"detailMail.title" : {
					required : true,
					maxlength : 20
				},
				"detailMail.content" : {
					required : true
				},
				"sendfile" : {
					file1 : true
				}
			},
			messages : {
				"detailMail.title" : {
					required : "标题不能为空",
					maxlength : "标题长度最多20个字符"
				},
				"detailMail.content" : {
					required : "内容不能为空",
				}
			}

		});
		$("#btnsave").click(function() {
			$("#span1").hide();
			if ($("#myForm").valid()) {
				if ($("#emps").val() == "-1"){
					alert ("请选择收件人");
					return;
				}
				var url = "/jboa2.0/mail/add_ajax.action";
				var options = {
					url : url,
					success : callback,
					type : 'post',
					dataType : 'json'
				};
				$("#myForm").ajaxSubmit(options);
			}
			;
		});
	});
	function callback(data) {
		if (data.success){
			location.href = "/jboa2.0/welcome.jsp";
		}else {
			alert("发送失败。请稍后再试");
		}
	}
</script>
</html>