/**
 * 销售信息详情
 */
//var url = baseurl + "/initialAccount/insertAccount";
/**
 * 页面加载函数
 */
$(function(){
	//页面加载时销售信息详情
	saleQueryDetail();
});

/**
 * 获取地址栏参数
 * @param name
 * @returns
 */
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

/**
 * 查看销售详情
 * @param obj
 */
function saleQueryDetail(){
	var sellerCode = GetQueryString("sellerCode");
	var barCode = GetQueryString("barCode");
	var data = "sellerCode="+sellerCode+"&barCode="+barCode;
	var url = baseurl + "/saleQuery/saleQueryDetail";
	sendRequest("post", url, data, getSaleQueryDetailResult);
	return false;
}

/**
 * 查看销售详情请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getSaleQueryDetailResult(result){
	var data = eval("(" + result + ")");
	$("#showTable td[name='sellerName']").text(data.sellerName);
	$("#showTable td[name='barCode']").text(data.barCode);
	$("#showTable td[name='frameCode']").text(data.frameCode);
	$("#showTable td[name='motorCode']").text(data.motorCode);
	$("#showTable td[name='materialCode']").text(data.materialCode);
	$("#showTable td[name='materialName']").text(data.materialName);
	$("#showTable td[name='maiterialSpec']").text(data.maiterialSpec);
	$("#showTable td[name='materialType']").text(data.materialType);
	$("#showTable td[name='colorDesc']").text(data.colorDesc);
	$("#showTable td[name='makeDate']").text(data.makeDate);
	$("#showTable td[name='purchaseDate']").text(data.purchaseDate);
	$("#showTable td[name='buyerName']").text(data.buyerName);
	$("#showTable td[name='buyerID']").text(data.buyerID);
	$("#showTable td[name='buyerSex']").text(data.buyerSex);
	$("#showTable td[name='buyerAge']").text(data.buyerAge);
	$("#showTable td[name='buyerMobile']").text(data.buyerMobile);
	$("#showTable td[name='sellDate']").text(data.sellDate);
}


/**
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}

