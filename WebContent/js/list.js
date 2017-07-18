/***********************************************鼠标划过时的样式****************************************************************/
var  highlightcolor='#d5f4fe';
// 此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
		return;
	while(source.tagName!="TD")
		source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	// alert(cs.length);
	if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
}

function  changeback(){
	if (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
		return
	if (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
// source.style.backgroundColor=originalcolor
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
}

function  clickto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
		return;
	while(source.tagName!="TD")
		source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	// alert(cs.length);
	if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=clickcolor;
		}
	else
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
}


$(function(){
	$(":checked").removeAttr("checked");
});
/**
 * 批量删除
 */
function deleteSelectedItem(action) {
	if (!confirm("确认删除？")){
		return;
	}
	var options = {
			url : action,
			success: function (data){
				if (data.success){
					alert (data.msg);
					history.go(0);
				}else 
					alert("更新失败请稍后再试,错误信息为：" + data.msg);
			},
			error: function (state){
				alert ("发生错误 :" + state);
			},
			contentType: "application/x-www-form-urlencoded; charset=utf-8" 
	};
	var f = document.forms[0];
	$(f).ajaxSubmit(options);	
}
/**
 * 查看详情
 * @param ele 当前元素，this指针，用于获取ID
 * @param url 跳转的URL，会和ID拼接在一起
 * @returns
 */
function check (ele, url){
	var id = $(ele).parent().parent().prevAll().last().children().val();
	location.href=url + id;
}

/**
 * 全部选择、反选
 * @returns
 */
function selectAll(){  
	if ($("#selectAll").attr("checked")) {  
		$(":checkbox").attr("checked", false);  
	} else {  
		$(":checkbox").attr("checked", true);  
	}  
}  