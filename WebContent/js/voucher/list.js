/**
 * 
 */
//删除
function vRemove (ele){
	var id = getCurrentId(ele);
	if (confirm("确认删除？")){
		location.href = "voucher/delete_operatevoucher?voucher.voucherId=" + id;
	}
}
//修改
function vAlter (ele){
	var id = getCurrentId(ele);
	location.href = "voucher/initAlter_initvoucher?voucherId=" + id;
}
function getCurrentId (ele){
	return $(ele).parent().parent().children().first().html();
}