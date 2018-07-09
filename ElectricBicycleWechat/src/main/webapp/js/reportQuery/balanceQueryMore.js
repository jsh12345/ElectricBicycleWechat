/**
 * 库存信息更多查询
 */
//var url = baseurl + "/initialAccount/insertAccount";
/**
 * 页面加载函数
 */
$(function(){
	//页面加载时查询库存信息
	searchBalanceInfo();
	$("#search").click(function(){
		searchBalanceInfo();
	});
});

/**
 * 查询库存信息
 */
function searchBalanceInfo(){
	var sellerCode = $("input[name='sellerCode']").val();
	var managerCode = $("input[name='managerCode']").val();
	var materialName = $("input[name='materialName']").val();
	var startDate = $("input[name='startDate']").val();
	var endDate = $("input[name='endDate']").val();
	var data = "sellerCode="+sellerCode+"&managerCode="+managerCode+"&materialName="+materialName+"&startDate="+startDate+"&endDate="+endDate;
	var url = baseurl + "/balanceQuery/searchBalanceInfo";
	sendRequest("post", url, data, getSearchBalanceInfoResult);
	return false;
};
/**
 * 查询库存信息请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getSearchBalanceInfoResult(result){
	$("#showDiv").empty();
	var data = eval("(" + result + ")");
	if(data!=''){
		var accountType = data.accountType;
		if(accountType == 'administrator'){
			$("#areaManager").show();
		}else if(accountType == 'sellerAccount'){
			$("#sellerAccount").hide();
		}
		var div = $("#showDiv");
		for(var i=0;i<data.saleList.length;i++){
			 var html = "<a onclick='balanceQueryDetail(this);' class='weui-cell weui-cell_access' href='javascript:;'>"+
             "<div class='weui-cell__bd'><p><table>"+
                 		"<tr><td>经销商代码：</td><td id='sellerCode'>"+data.saleList[i].sellerCode+"</td></tr>"+
                 		"<tr><td>车辆条形码：</td><td id='barCode'>"+data.saleList[i].barCode+"</td></tr>"+
                 		"<tr><td>车架号：</td><td>"+data.saleList[i].frameCode+"</td></tr>"+
                 		"<tr><td>入库日期：</td><td>"+data.saleList[i].purchaseDate+"</td></tr></table> </p></div>"+
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
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}

