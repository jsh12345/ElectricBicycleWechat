/**
 * 查询经销商账号
 */
function searchSellerInfoForDetail(){
	$("#wrapper").css("width","870px");
	var sellerCode = $("input[name='sellerCode']").val();
	var sellerName = $("input[name='sellerName']").val();
	var data = "&sellerCode="+sellerCode+"&sellerName="+sellerName;
	var url = baseurl + "/seller/findSellerDetail";
	sendRequest("post", url, data, getSearchSellerInfoForDetailResult);
	return false;
};
/**
 * 查询经销商账号信息请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getSearchSellerInfoForDetailResult(result){
	$("#allForA").show();
	$("#showAllTable").empty();
	if(result=="none"){
		$('#msgContent').text("不存在该经销商！");
		$('#iosDialog2').fadeIn(200);
	}else{
	var data = eval("(" + result + ")");
	if(data!=''){
		var div = $("#showAllTable");
			 var html = "<tr style='text-align:center;'>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.sellerCode+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.sellerName+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.sellerDeliveryAddress+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.contactor+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.contactorTele+"</td>"+
                 		"</tr>";
			 div.append(html);
	}
	}
}

/**
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}

