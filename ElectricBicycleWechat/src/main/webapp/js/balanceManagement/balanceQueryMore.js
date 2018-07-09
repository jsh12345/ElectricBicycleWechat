/**
 * 库存信息更多查询
 */
//var url = baseurl + "/initialAccount/insertAccount";
/**
 * 页面加载函数
 */
$(function(){
	//获取当前登录用户信息
	getCurrentUser();
	
	$("#searchDetail").click(function(){
		searchBalanceInfoForDetail();
	});
	
	$("#searchSum").click(function(){
		searchBalanceInfoForSum();
	});
	
	$("select[operation=blur]").blur(function(){
		document.removeEventListener('touchmove', preventDefault, false);  
	});
	
});

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
			$("#areaManager").show();
		}else if(accountType == '1'){
			$("#sellerAccount").hide();
		}else if(accountType == '2'){
			$("#area").show();
		}
		if(accountType == '2' || accountType == '4'){
			//获取经销商账号
			getSellerInfo();
		}
		if(accountType == '2'){
			//获取区域信息
			getAreaInfo();
		}
		
		//页面加载时查询库存信息
		searchBalanceInfoForDetail();
	}
	return false;
}

/**
 * 查询库存明细信息
 */
function searchBalanceInfoForDetail(){
	$("#wrapper").css("width","870px");
	$("#currentUser").css("margin-left","30px");
	$("#labelSellerCode").css("width","20%");
	$("#inputSellerCode").css("margin-left","0px");
	$("#inputSellerCode").css("width","160%");
	$("#inputManagerCode").css("margin-left","30px");
	$("#inputArea").css("margin-left","0px");
	$("#inputArea").css("width","112%");
	$("#inputMaterialName").css("margin-left","20px");
	$("#searchDetail").css("width","13%");
	$("#searchSum").css("width","13%");
	var sellerCode = $("select[name='sellerCode']").val();
	var areaManagerCode = $("input[name='managerCode']").val();
	var areaCode = $("select[name='area']").val();
	var materialName = $("input[name='materialName']").val();
	var startDate = $("input[name='startDate']").val();
	var endDate = $("input[name='endDate']").val();
	var data = "areaCode="+areaCode+"&sellerCode="+sellerCode+"&areaManagerCode="+areaManagerCode+"&materialName="+materialName+"&startDate="+startDate+"&endDate="+endDate;
	var url = baseurl + "/balanceQuery/searchBalanceInfoForDetail";
	sendRequest("post", url, data, getSearchBalanceInfoForDetailResult);
	return false;
};
/**
 * 查询库存明细信息请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getSearchBalanceInfoForDetailResult(result){
	$("#allForA").hide();
	$("#detailForA").show();
	$("#showDetailTable").empty();
	var data = eval("(" + result + ")");
	if(data!=''){
		/*var accountType = data.accountType;
		if(accountType == '4'){
			$("#areaManager").show();
		}else if(accountType == '1'){
			$("#sellerAccount").hide();
		}*/
		var div = $("#showDetailTable");
		for(var i=0;i<data.saleList.length;i++){
//			 var html = "<a onclick='balanceQueryDetail(this);' class='weui-cell weui-cell_access' href='javascript:;'>"+
//             "<div class='weui-cell__bd'><p><table>"+
//                 		"<tr><td>经销商代码：</td><td id='sellerCode'>"+data.saleList[i].sellerCode+"</td></tr>"+
//                 		"<tr><td>车辆条形码：</td><td id='barCode'>"+data.saleList[i].barCode+"</td></tr>"+
//                 		"<tr><td>车架号：</td><td>"+data.saleList[i].frameCode+"</td></tr>"+
//                 		"<tr><td>车型名称：</td><td>"+data.saleList[i].materialName+"</td></tr>"+
//                 		"<tr><td>规格：</td><td>"+data.saleList[i].maiterialSpec+"</td></tr>"+
//                 		"<tr><td>颜色：</td><td>"+data.saleList[i].colorDesc+"</td></tr>"+
//                 		"<tr><td>入库日期：</td><td>"+data.saleList[i].purchaseDate+"</td></tr></table> </p></div>"+
//             "<div class='weui-cell__ft'>查看详情</div> </a>";
			 var html = "<tr style='text-align:center;'><td style='border-bottom: 1px solid #e5e5e5;'>"+(i+1)+"</td>"+
                 		"<td id='sellerCode' style='border-bottom: 1px solid #e5e5e5;display:none;'>"+data.saleList[i].sellerCode+"</td>"+
                 		"<td id='barCode' style='border-bottom: 1px solid #e5e5e5;'>"+data.saleList[i].barCode+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.saleList[i].frameCode+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.saleList[i].materialName+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.saleList[i].maiterialSpec+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.saleList[i].colorDesc+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'><div onclick='balanceQueryDetail(this);' style='color:blue;'>详情</div></td></tr>";
//						"<td><a onclick='' class='weui-cell weui-cell_access' href='javascript:balanceQueryDetail(this);'>"+
//			      		"<div class='weui-cell__ft'>查看详情</div> </a></td></tr>";
			 div.append(html);
			 //小计
			 for(var j=0;j<data.Subtotal.length;j++){
				 var name = data.Subtotal[j].materialName;//车型名称
				 if(i==data.saleList.length-1){//最后一条
					 if(name==data.saleList[i].materialName){
						 var SubtotalHtml = "<tr><td colspan='7'><a  class='weui-cell weui-cell_access' href='javascript:;'>"+
			             "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>小计："+data.Subtotal[j].num+"</p></div>"+
	              		" </a></td></tr>";
						 div.append(SubtotalHtml);
						 break;
					 }
				 }else{
					 if(name==data.saleList[i].materialName && name!=data.saleList[i+1].materialName){
						 var SubtotalHtml = "<tr><td colspan='7'><a  class='weui-cell weui-cell_access' href='javascript:;'>"+
			             "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>小计："+data.Subtotal[j].num+"</p></div>"+
	              		" </a></td></tr>";
						 div.append(SubtotalHtml);
						 break;
					 }
				 }
				 
			 }
			 
			 //总计
			 if(i==data.saleList.length-1){
				 var totalHtml = "<tr><td colspan='7'><a  class='weui-cell weui-cell_access' href='javascript:;'>"+
	             "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>总计："+data.num+"</p></div>"+
          		" </a></td></tr>";
				 div.append(totalHtml);
			 }
			 
		}
	}
}

/**
 * 查询库存汇总信息
 */
function searchBalanceInfoForSum(){
	$("#wrapper").css("width","500px");
	$("#currentUser").css("margin-left","50px");
	$("#inputSellerCode").css("margin-left","20px");
	$("#inputSellerCode").css("width","145%");
	$("#labelSellerCode").css("width","40%");
	$("#inputArea").css("margin-left","30px");
	$("#inputArea").css("width","97%");
	$("#inputManagerCode").css("margin-left","60px");
	$("#inputMaterialName").css("margin-left","60px");
	$("#searchDetail").css("width","25%");
	$("#searchSum").css("width","25%");
	var sellerCode = $("select[name='sellerCode']").val();
	var areaManagerCode = $("input[name='managerCode']").val();
	var areaCode = $("select[name='area']").val();
	var materialName = $("input[name='materialName']").val();
	var startDate = $("input[name='startDate']").val();
	var endDate = $("input[name='endDate']").val();
	var data = "areaCode="+areaCode+"&sellerCode="+sellerCode+"&areaManagerCode="+areaManagerCode+"&materialName="+materialName+"&startDate="+startDate+"&endDate="+endDate;
	var url = baseurl + "/balanceQuery/searchBalanceInfoForSum";
	sendRequest("post", url, data, getSearchBalanceInfoForSumResult);
	return false;
};
/**
 * 查询库存汇总信息请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getSearchBalanceInfoForSumResult(result){
	$("#detailForA").hide();
	$("#allForA").show();
	$("#showAllTable").empty();
	var data = eval("(" + result + ")");
	if(data!=''){
		/*var accountType = data.accountType;
		if(accountType == '4'){
			$("#areaManager").show();
		}else if(accountType == '1'){
			$("#sellerAccount").hide();
		}*/
		var div = $("#showAllTable");
		for(var i=0;i<data.saleList.length;i++){
			 var html = "<tr style='text-align:center;'><td style='border-bottom: 1px solid #e5e5e5;'>"+(i+1)+"</td>"+
                 		"<td id='sellerCode' style='border-bottom: 1px solid #e5e5e5;'>"+data.saleList[i].materialName+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.saleList[i].maiterialSpec+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.saleList[i].colorDesc+"</td>"+
                 		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data.saleList[i].num+"</td></tr>";
			 div.append(html);
			 //小计
			 for(var j=0;j<data.Subtotal.length;j++){
				 var name = data.Subtotal[j].materialName;//车型名称
				 if(i==data.saleList.length-1){//最后一条
					 if(name==data.saleList[i].materialName){
						 var SubtotalHtml = "<tr><td colspan='5'><a  style='width:100%;' class='weui-cell weui-cell_access' href='javascript:;'>"+
			             "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>小计："+data.Subtotal[j].num+"</p></div>"+
	              		" </a></td></tr>";
						 div.append(SubtotalHtml);
						 break;
					 }
				 }else{
					 if(name==data.saleList[i].materialName && name!=data.saleList[i+1].materialName){
						 var SubtotalHtml = "<tr><td colspan='5'><a  class='weui-cell weui-cell_access' href='javascript:;'>"+
			             "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>小计："+data.Subtotal[j].num+"</p></div>"+
	              		" </a></td></tr>";
						 div.append(SubtotalHtml);
						 break;
					 }
				 }
				 
			 }
			 
			 //总计
			 if(i==data.saleList.length-1){
				 var totalHtml = "<tr><td colspan='5'><a  class='weui-cell weui-cell_access' href='javascript:;'>"+
	             "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>总计："+data.num+"</p></div>"+
          		" </a></td></tr>";
				 div.append(totalHtml);
			 }
		}
	}
}

/**
 * 查看库存详情
 * @param obj
 */
function balanceQueryDetail(obj){
	var sellerCode = $.trim($(obj).parent().parent().find("#sellerCode").text());
	var barCode = $.trim($(obj).parent().parent().find("#barCode").text());
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

