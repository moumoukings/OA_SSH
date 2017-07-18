var newTr = "";
window.onload = function() {
	newTr = $('table').last().children().children().last().html();
	newTr = "<tr>" + newTr + "</tr>";
	getCurrentTime($("#currentTime"));
}
function autosum() {
	var arr = $(".cost");
	var sum = 0;
	for (var i = 0; i < arr.length; i++) {
		if (arr[i].value.match(/^\d+$/))
			sum += (arr[i].value - 0);
	}
	$("#totalCost").val(sum);
}
function add(ele) {
	var $td = $(ele);
	// 验证
	var $arr = $td.parent().parent().children();
	if ($($arr[0].firstChild).val() == -1) {
		alert('请选择项目类别');
		return;
	}
	if (!$($arr[1].firstChild).val().match(/^\d+$/)) {
		alert('金额必须为整数');
		return;
	}
	$($arr[0].firstChild).attr("disabled", "disabled");
	$($arr[1].firstChild).attr("readonly", "readonly");
	$($arr[2].firstChild).attr("readonly", "readonly");
	$td.parent().parent().parent().append(newTr);
	$td.html('<span  class="operate" onclick="remove(this)">x</span>');
}

function remove(ele) {
	var $td = $(ele);
	$td.parent().parent().parent().remove();
}
function commit(actionName) {
	// 验证数据合法
	var arr = $(".price");
	if (arr.length == 1 && (arr[0].value == null || arr[0].value == "")) {
		alert("请添加报销明细");
		return;
	}
	// 修改action
	$(document.forms[0]).attr("action", actionName);
	// 启用所有的disabled元素
	$("select :disabled").removeAttr('disabled');
	// 提交
	document.forms[0].submit();
}
function getCurrentTime(ele){
	var date = new Date();
	var year = date.getFullYear ();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var dStr = year + "-" + month +"-"+ day;
	$(ele).val(dStr);
}