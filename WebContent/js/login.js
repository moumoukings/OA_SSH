/**
 * 
 */
function changeValidateCode(obj) {
	// 获取当前的时间作为参数，无具体意义
	var timenow = new Date().getTime();
	// 每次请求需要一个不同的参数，否则可能会返回同样的验证码
	// 这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
	obj.src = "random.action?d=" + timenow;
}
function check() {
	var msg = document.getElementById("msg").value;
	if (msg.length != 0) {
		alert(msg);
		document.getElementById("msg").value = "";
	}
}

$(function(){
	$("#login").click(function(){
		var username = $("#username").val();
		var pwd = $("#password").val();
		if (isEmpty(username)){
			$("#errorInfo").html("用户名不能为空");
			return ;
		}
		if (isEmpty (pwd)){
			$("#errorInfo").html("密码不能为空");
			return ;
		}
		$.post(
				"employee/login.action",
				"employee.name="+username + "&employee.password=" + pwd,
				function (data){
					if (data.success){
						location.href="main.jsp";				
					}else{
						alert(data.msg);
					}
				}
		).error(function(){$("#errorInfo").html("服务器错误，请稍后再试");});		
	});
	
});
function isEmpty (str){
	if (str == null || $.trim(str) == "")
		return true;
	return false;
}