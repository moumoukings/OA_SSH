<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link type="text/css" rel="stylesheet" href="${ctx }/dtree_/dtree.css">
	<script type="text/javascript" src="${ctx }/dtree_/dtree.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
</head>
<body style="background-color:#ADD2DA">
	<div id="menuDiv"></div>
	<script type="text/javascript">
	var d = new dTree("d");

	$(function(){
		var url="getCurrentRights.action";
		$.post(
			url,
			{},
			function(data){
				if(data.retCode=="0"){
					d.add("ROOT_MENU","-1","OA管理系统");
					$.each(data.rightList,function(i,value){
						d.add(value.rightCode, value.rightParentCode, value.rightTitle, value.rightUrl,
								'' , 'rightFrame', '', '', '' ,0);
					});
					$("#menuDiv").html(d.toString());
				}else{
					alert(data.retMsg);
					window.parent.parent.location.href ="login.html";
				}
			}
		);
	});
	</script>
</body>
</html>