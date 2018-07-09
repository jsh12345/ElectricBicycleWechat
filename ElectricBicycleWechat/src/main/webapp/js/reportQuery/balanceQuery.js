/**
 * 库存信息查询
 */
//var url = baseurl + "/initialAccount/insertAccount";
/**
 * 页面加载函数
 */
$(function(){
	//页面加载时查询近期库存信息
	searchBalanceInfoOnload();
});

/**
 * 查询近期库存信息
 */
function searchBalanceInfoOnload(){
	var data = "";
	var url = baseurl + "/balanceQuery/searchBalanceInfoOnload";
	sendRequest("post", url, data, getSearchBalanceInfoOnloadResult);
	return false;
};
/**
 * 查询近期库存信息请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getSearchBalanceInfoOnloadResult(result){
	var data = eval("(" + result + ")");
	if(data!=''){
		var div = $("#showDiv");
		for(var i=0;i<data.length;i++){
			 var html = "<a onclick='balanceQueryDetail(this);' class='weui-cell weui-cell_access' href='javascript:;'>"+
             "<div class='weui-cell__bd'><p><table>"+
                 		"<tr><td>经销商代码：</td><td id='sellerCode'>"+data[i].sellerCode+"</td></tr>"+
                 		"<tr><td>车辆条形码：</td><td id='barCode'>"+data[i].barCode+"</td></tr>"+
                 		"<tr><td>车架号：</td><td>"+data[i].frameCode+"</td></tr>"+
                 		"<tr><td>入库日期：</td><td>"+data[i].purchaseDate+"</td></tr></table> </p></div>"+
             "<div class='weui-cell__ft'>查看详情</div> </a>";
			 div.append(html);
		}
	}
}

/**
 * 查看库存详情
 * @param obj
 */
function balanceQueryDetail(obj){
	var sellerCode = $.trim($(obj).find("#sellerCode").text());
	var barCode = $.trim($(obj).find("#barCode").text());
	var data = "sellerCode="+sellerCode+"&barCode="+barCode;
	var url = baseurl + "/views/reportQuery/balanceQueryDetail.html";
	window.location.href = url+"?"+data;
}

/**
 * 查看更多
 */
function balanceQueryMore(){
	window.location.href = baseurl + "/views/reportQuery/balanceQueryMore.html";
}

/**
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}

