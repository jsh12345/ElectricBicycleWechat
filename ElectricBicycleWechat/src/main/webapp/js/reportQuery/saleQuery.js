/**
 * 销售信息查询
 */
//var url = baseurl + "/initialAccount/insertAccount";
/**
 * 页面加载函数
 */
$(function(){
	//页面加载时查询近期销售信息
	searchPCInfoOnload();
});

/**
 * 查询近期销售信息
 */
function searchPCInfoOnload(){
	var data = "";
	var url = baseurl + "/saleQuery/searchPCInfoOnload";
	sendRequest("post", url, data, getSearchPCInfoOnloadResult);
	return false;
};
/**
 * 查询近期销售信息请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getSearchPCInfoOnloadResult(result){
	var data = eval("(" + result + ")");
	if(data!=''){
		var div = $("#showDiv");
		for(var i=0;i<data.length;i++){
			 var html = "<a onclick='saleQueryDetail(this);' class='weui-cell weui-cell_access' href='javascript:;'>"+
             "<div class='weui-cell__bd'><p><table>"+
                 		"<tr><td>经销商代码：</td><td id='sellerCode'>"+data[i].sellerCode+"</td></tr>"+
                 		"<tr><td>车辆条形码：</td><td id='barCode'>"+data[i].barCode+"</td></tr>"+
                 		"<tr><td>车架号：</td><td>"+data[i].frameCode+"</td></tr>"+
                 		"<tr><td>销售日期：</td><td>"+data[i].sellDate+"</td></tr></table> </p></div>"+
             "<div class='weui-cell__ft'>查看详情</div> </a>";
			 div.append(html);
		}
	}
}

/**
 * 查看销售详情
 * @param obj
 */
function saleQueryDetail(obj){
	var sellerCode = $.trim($(obj).find("#sellerCode").text());
	var barCode = $.trim($(obj).find("#barCode").text());
	var data = "sellerCode="+sellerCode+"&barCode="+barCode;
	var url = baseurl + "/views/reportQuery/saleQueryDetail.html";
	window.location.href = url+"?"+data;
}

/**
 * 查看更多
 */
function saleQueryMore(){
	window.location.href = baseurl + "/views/reportQuery/saleQueryMore.html";
}

/**
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}

