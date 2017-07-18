<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>员工列表</title>
</head>
	<script type="text/javascript" src="${jquery_js }"></script>
	<script type="text/javascript" src="${jquery_form_js }"></script>
		<script type="text/javascript" src="${ctx }/js/list.js"></script>
	<link type="text/css" href="${ctx }/css/list.css"></link>
	<script type="text/javascript">
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
	</script>
<body>
	<br />
	<br />
	<input type="button" value="新增员工信息"
		onclick="javascript:location.href='add_init.action'" />
	<form name="searchForm" action='${actionName }'>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="6%" height="19" valign="bottom">
												<div align="center">
													<img src="${ctx }/images/tb.gif" width="14" height="14" />
												</div>
											</td>
											<td width="94%" valign="bottom"><span class="STYLE1"> 员工基本信息列表</span></td>
										</tr>
									</table>
								</td>
								<td>
									<div align="right">
										<span class="STYLE1"> 
											<img src="${ctx }/images/del.gif" width="10" height="10" /> 
											<a href="javascript:void(0)" onclick="deleteSelectedItem('delete_ajax.action')">删除</a> &nbsp;&nbsp; 
										</span>
										<span class="STYLE1"> &nbsp;</span>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()" onmouseout="changeback()">
				<tr>
					<td></td>
					<td width="6%" height="20" bgcolor="d3eaef" class="STYLE10">
						<div align="center"> <input type="checkbox" id="selectAll" /> </div>
					</td>
					<td width="6%" height="20" bgcolor="d3eaef" class="STYLE6">
						<div align="center"><span class="STYLE10">ID</span>	</div>
					</td>
					<td width="20%" height="20" bgcolor="d3eaef" class="STYLE6">
						<div align="center"> <span class="STYLE10">用户名</span> </div>
					</td>
					<td width="30%" height="20" bgcolor="d3eaef" class="STYLE6">
						<div align="center"> <span class="STYLE10">手机</span> </div>
					</td>
					<td width="30%" height="20" bgcolor="d3eaef" class="STYLE6">
						<div align="center">
								<span class="STYLE10">住址</span>
						</div>
					</td>
					<td width="8%" height="20" bgcolor="d3eaef" class="STYLE6">
						<div align="center">
							<span class="STYLE10">操作</span>
						</div>
					</td>
				</tr>
						<s:iterator value="pager.pageRecords">
							<tr>
								<td><s:hidden name="id"></s:hidden></td>
								<td height="20" bgcolor="#FFFFFF">
									<div align="center">
										<input type="checkbox" name="deleteIdList"
											value="<s:property value='id'></s:property>">
									</div>
								</td>
								<td height="20" bgcolor="#FFFFFF" class="STYLE6">
									<div align="center">
										<span class="STYLE19"><s:property value="id"/></span>
									</div>
								</td>
								<td height="20" bgcolor="#FFFFFF" class="STYLE19">
									<div align="center">
										<s:property value="name"/>
									</div>
								</td>
								<td height="20" bgcolor="#FFFFFF" class="STYLE19">
									<div align="center">
										<s:property value="phoneNum"/>
									</div>
								</td>
								<td height="20" bgcolor="#FFFFFF" class="STYLE19">
									<div align="center">
										<s:property value="address"/>
									</div>
								</td>
								<td height="20" bgcolor="#FFFFFF">
									<div align="center" class="STYLE21">
										<a href="javascript:void(0)"
											onclick="check(this, 'check_init.action?empId=')">查看</a>
									</div>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
		</table>
		<%@include file="/common/page.jsp"%>
	</form>
</body>
</html>