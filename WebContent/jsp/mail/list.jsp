<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>请假记录列表</title>
<script type="text/javascript" src="${jquery_js }"></script>
<script type="text/javascript" src="${jquery_form_js }"></script>
<script type="text/javascript" src="${ctx }/js/list.js"></script>
<link type="text/css" href="${ctx }/css/list.css"></link>
<script type="text/javascript">

parent.parent.frames["topFrame"].history.go(0);

$(function (){
	$("#selectAll").click(selectAll);
});
function selectAll(){  
	if ($("#selectAll").attr("checked")) {  
		$(":checkbox").attr("checked", true);  
	} else {  
		$(":checkbox").attr("checked", false);  
	}  
}  

function restore (ele){
	var id = $(ele).parent().parent().prevAll().last().children().val();

	$.ajax ({
		url: "restore_ajax.action",
		data:{"mailId":id},
		success: function (data){
			if (data.success){
				alert("还原成功");
				history.go(0);
			}else{
				alert ("操作失败，请稍后再试" +data.msg);
				history.go(0);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
	});
}
function restoreSelectedItem(){
	if (!confirm("确认还原所有选中项？")){
		return;
	}
	var options = {
			url : "restoreSelected_ajax.action",
			success: function (data){
				if (data.success){
					alert (data.msg);
					history.go(0);
				}else 
					alert("还原失败请稍后再试,错误信息为：" + data.msg);
			},
			error: function (state){
				alert ("发生错误 :" + state);
			},
			contentType: "application/x-www-form-urlencoded; charset=utf-8" 
	};
	var f = document.forms[0];
	$(f).ajaxSubmit(options);
}
</script>
</head>
<body>
<form name="searchForm" action='${actionName }'>

	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="6%" height="19" valign="bottom"><div
														align="center">
														<img src="${ctx }/images/tb.gif" width="14" height="14" />
													</div></td>
												<td width="94%" valign="bottom"><span class="STYLE1">
														邮件基本信息列表</span></td>
											</tr>
										</table></td>
									<td><div align="right">
											<span class="STYLE1"> 
												<img src="${ctx }/images/del.gif" width="10" height="10" /> 
												<s:if test="%{deletedList }">
													<a href="javascript:void(0)" onclick="restoreSelectedItem()">批量还原</a>
												</s:if>
												<a href="javascript:void(0)" onclick="deleteSelectedItem('delete_ajax.action')">删除</a>
												&nbsp;&nbsp;
											</span><span class="STYLE1"> &nbsp;</span>
										</div></td>
								</tr>
							</table></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0"
					cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"
					onmouseout="changeback()">
					<tr>
						<td></td>
						<td width="4%" height="20" bgcolor="d3eaef" class="STYLE10">
							<div align="center">
								<input type="checkbox" id="selectAll" />
							</div>
						</td>
						<td width="10%" height="20" bgcolor="d3eaef" class="STYLE6">
							<div align="center">
								<span class="STYLE10">邮件标题</span>
							</div>
						</td>
						<td width="30%" height="20" bgcolor="d3eaef" class="STYLE6">
							<div align="center">
								<span class="STYLE10">内容</span>
							</div>
						</td>
						<td width="15%" height="20" bgcolor="d3eaef" class="STYLE6">
							<div align="center">
								<span class="STYLE10">状态</span>
							</div>
						</td>
						<td width="30%" height="20" bgcolor="d3eaef" class="STYLE6">
							<div align="center">
								<span class="STYLE10">时间</span>
							</div>
						</td>
						<td width="15%" height="20" bgcolor="d3eaef" class="STYLE6">
							<div align="center">
								<span class="STYLE10">操作</span>
							</div>
						</td>						
					</tr>
					<s:iterator value="pager.pageRecords">
						<tr>
							<td>
								<s:hidden name="id"></s:hidden>
							</td>
							<td height="20" bgcolor="#FFFFFF">
								<div align="center"> 
									<input type="checkbox" name="deleteIdList" value="<s:property value='id'></s:property>">
								</div>
							</td>
							<td height="20" bgcolor="#FFFFFF" class="STYLE6">
								<div align="center">
									<span class="STYLE19"><s:property value="title"/></span> 
								</div>
							</td>
							<td height="20" bgcolor="#FFFFFF" class="STYLE19">
								<div align="center">
									<s:property value="content"/>
								</div>
							</td>
							<td height="20" bgcolor="#FFFFFF" class="STYLE19">
								<s:if test="%{send}">
									<div align="center">已发送</div>
								</s:if>
								<s:else>
									<div align="center">
										<s:property value="receiveState"/>
									</div>
								</s:else>
							</td>
							<td height="20" bgcolor="#FFFFFF" class="STYLE19">
								<div align="center"><s:date format="yyyy-MM-dd HH:mm:ss" name="time"/></div>
							</td>
							<td height="20" bgcolor="#FFFFFF">
								<s:if test="%{deleted }">
									<div align="center" class="STYLE21">
										<a href="javascript:void(0)" 
										onclick="restore(this)">还原</a>
									</div>
								</s:if>
								<s:else>
									<div align="center" class="STYLE21">
										<a href="javascript:void(0)" 
										onclick="check(this, 'check_init.action?mailId=')">查看</a>
									</div>
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</table>
			</td>
		</tr>
	</table>
	 		<%@include file="/common/page.jsp" %>
 	</form>
</body>
</html>