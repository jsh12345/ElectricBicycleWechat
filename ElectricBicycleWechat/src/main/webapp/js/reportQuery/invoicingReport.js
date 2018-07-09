/**
 * 销售信息更多查询
 */

//var url = baseurl + "/initialAccount/insertAccount";
/**
 * 页面加载函数
 */
$(function(){
	
	//获取当前登录用户信息
	getCurrentUser();
	//入库查询
	$("#search").click(function(){
		search();
	});
});
//

/**
 * 获取当前登录用户信息
 */
function getCurrentUser(){
	var data = "";
	var url = baseurl + "/login/getCurrentUser";
	sendRequest("post", url, data, getGetCurrentUserResult);
	return false;
};

/**
 * 获取当前登录用户信息的返回结果
 * @param result
 * @returns {Boolean}
 */
function getGetCurrentUserResult(result){
	var data = eval("(" + result + ")");
	if(data.name=='' || data.name==null || data.currentAccount==null){
		window.location.href = baseurl + "/views/login/login.html";
	}else{
		$("input[name='currentUser']").val(data.name);
		var accountType = data.currentAccount.type;//账号类型
		if(accountType == '4'){
			//$("#areaManager").show();
		}else if(accountType == '1'){
			$("#sellerAccount").hide();
		}else if(accountType == '2'){
			//$("#area").show();
		}
		if(accountType == '2' || accountType == '4'){
			//获取经销商账号
			getSellerInfo();
		}
		if(accountType == '2'){
			//获取区域信息
			//getAreaInfo();
		}
		
		//页面加载时查询信息
		search();
	}
	return false;
}

/**
 * 获取经销商账号
 */
function getSellerInfo(){
	var data = "";
	var url = baseurl + "/balanceQuery/getSellerInfo";
	sendRequest("post", url, data, getSellerInfoResult);
	return false;
};

/**
 * 获取经销商账号的返回结果
 * @param result
 * @returns {Boolean}
 */
function getSellerInfoResult(result){
	var data = eval("(" + result + ")");
	if(data==false){
		$("#msgContent").text("查询经销商账号失败！");
		$("#iosDialog2").show();
	}else{
		for(var i=0;i<data.seller.length;i++){
			var select = $("select[name='sellerCode']");
			var option = "<option value="+data.seller[i].sellerCode+">"+data.seller[i].sellerName+"</option>";
			select.append(option);
		}
	}
	return false;
}

/**
 * 获取区域信息
 */
function getAreaInfo(){
	var data = "";
	var url = baseurl + "/balanceQuery/getAreaInfo";
	sendRequest("post", url, data, getAreaInfoResult);
	return false;
};

/**
 * 获取区域信息的返回结果
 * @param result
 * @returns {Boolean}
 */
function getAreaInfoResult(result){
	var data = eval("(" + result + ")");
	if(data==false){
		$("#msgContent").text("查询区域信息失败！");
		$("#iosDialog2").show();
	}else{
		for(var i=0;i<data.area.length;i++){
			var select = $("select[name='area']");
			var option = "<option value="+data.area[i].areaCode+">"+data.area[i].areaName+"</option>";
			select.append(option);
		}
	}
	return false;
}

/**
 * 查询信息
 */
function search(){
	var sellerCode = $("select[name='sellerCode']").val();
	//var areaManagerCode = $("input[name='managerCode']").val();
	//var areaCode = $("select[name='area']").val();
	//var materialName = $("input[name='materialName']").val();
	var startDate = $("input[name='startDate']").val();
	var endDate = $("input[name='endDate']").val();
	var data = "sellerCode="+sellerCode+"&startDate="+startDate+"&endDate="+endDate;
	var url = baseurl + "/invoicingReport/search";
	sendRequest("post", url, data, getSearchResult);
	return false;
};
/**
 * 查询信息请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getSearchResult(result){
	var data = eval("(" + result + ")");
	$("#showTable").empty();
	if(data!=''){
		var tbody = $("#showTable");
		for(var i=0;i<data.dataList.length;i++){
			 var html = "<tr style='text-align:center;'><td style='border-bottom: 1px solid #e5e5e5;'>"+(i+1)+"</td>"+
			 			"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.dataList[i].material_name+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.dataList[i].material_type+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.dataList[i].material_spec+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.dataList[i].color_desc+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.dataList[i].init_qty+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.dataList[i].purchase_qty+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.dataList[i].sale_qty+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.dataList[i].end_qty+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.dataList[i].stock_qty+"</td></tr>";
			 tbody.append(html);
		}
	}
}



/**
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}

