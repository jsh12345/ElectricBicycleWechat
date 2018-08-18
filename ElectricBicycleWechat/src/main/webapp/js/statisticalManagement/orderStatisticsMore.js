/**
 * 订单信息更多查询
 */

/**
 * 页面加载函数
 */
$(function() {
	// 获取当前登录用户信息
	getCurrentUser();

	$("#searchSumNum").click(function() {
		searchOrderInfoForNum();
	});

	$("#searchSumType").click(function() {
		searchOrderInfoForType();
	});

	$("select[operation=blur]").blur(function() {
		document.removeEventListener('touchmove', preventDefault, false);
	});

});

/**
 * 获取经销商账号
 */
function getSellerInfo() {
	var data = "";
	var url = baseurl + "/orderStatistics/getSellerInfo";
	sendRequest("post", url, data, getSellerInfoResult);
	return false;
};

/**
 * 获取经销商账号的返回结果
 * 
 * @param result
 * @returns {Boolean}
 */
function getSellerInfoResult(result) {
	var data = eval("(" + result + ")");
	if (data == false) {
		$("#msgContent").text("查询经销商账号失败！");
		$("#iosDialog2").show();
	} else {
		for (var i = 0; i < data.seller.length; i++) {
			var select = $("select[name='sellerCode']");
			var option = "<option value=" + data.seller[i].sellerCode + ">"
					+ data.seller[i].sellerName + "</option>";
			select.append(option);
		}
	}
	return false;
}

/**
 * 获取当前登录用户信息
 */
function getCurrentUser() {
	var data = "";
	var url = baseurl + "/login/getCurrentUser";
	sendRequest("post", url, data, getGetCurrentUserResult);
	return false;
};

/**
 * 获取当前登录用户信息的返回结果
 * 
 * @param result
 * @returns {Boolean}
 */
function getGetCurrentUserResult(result) {
	var data = eval("(" + result + ")");
	if (data.name == '' || data.name == null|| data.currentAccount==null) {
		window.location.href = baseurl + "/views/login/login.html";
	} else {
		//$("input[name='currentUser']").val(data.name);
		var accountType = data.type;//账号类型
		if(accountType == '经销商'){//
//			$("#sellerAccount").hide();			
			 alert("您没有查看统计订单的权限，请登录厂商内部账号");
			window.location.href =  baseurl + "/views/login/personalInfoHome.html";	
	     }
		 else{			
			$("input[name='currentUser']").val(data.name);
			searchOrderInfoForNum();
	     }
		
	}
	return false;
}

/**
 * 查询订单数量汇总
 */
function searchOrderInfoForNum() {

	$("#wrapper").css("width", "1000px");
	$("#currentUser").css("margin-left", "30px");
	$("#inputMaterialName").css("margin-left", "30px");
	$("#searchSumNum").css("width", "150px");
	$("#searchSumType").css("width", "150px");
	
	$("#detailForType").hide();
	$("#detailForNum").show();
	$("#showNumTable").empty();

	var cust_name = $("input[name='materialName']").val();
	/***************************************************************************
	 * var comp_id = $("select[name='comp_id']").val(); var bill_no =
	 * $("select[name='bill_no']").val(); var bill_date =
	 * $("select[name='bill_date']").val(); var bill_type =
	 * $("select[name='bill_type']").val(); var cust_code =
	 * $("select[name='cust_code']").val(); var cust_name =
	 * $("select[name='cust_name']").val(); var freight_mode =
	 * $("select[name='freight_mode']").val(); var currency_code =
	 * $("select[name='freight_mode']").val(); var exchange_rate =
	 * $("select[name='exchange_rate']").val(); var depot_code =
	 * $("select[name='depot_code']").val(); var forecast_shipmentdate =
	 * $("select[name='depot_code']").val(); var cust_delivery_address =
	 * $("select[name='cust_delivery_address']").val(); var other_amt =
	 * $("select[name='cust_delivery_address']").val(); var total_qty =
	 * $("select[name='total_qty']").val(); var total_amt =
	 * $("select[name='total_amt']").val(); var login_empid =
	 * $("select[name='login_empid']").val(); var login_date =
	 * $("select[name='login_date']").val(); var check_sign =
	 * $("select[name='check_sign']").val(); var check_empid =
	 * $("select[name='check_empid']").val(); var check_date =
	 * $("select[name='check_date']").val(); var audit_sign =
	 * $("select[name='audit_sign']").val(); var audit_empid =
	 * $("select[name='audit_empid']").val(); var audit_date =
	 * $("select[name='audit_date']").val(); var indepot_sign =
	 * $("select[name='indepot_sign']").val(); var shipment_sign =
	 * $("select[name='shipment_sign']").val(); var outdepot_sign =
	 * $("select[name='outdepot_sign']").val(); var end_sign =
	 * $("select[name='end_sign']").val(); var end_date =
	 * $("select[name='end_date']").val(); var end_empid =
	 * $("select[name='end_date']").val(); var Transport_billno =
	 * $("select[name='Transport_billno']").val();
	 **************************************************************************/

	/***************************************************************************
	 * var data = "comp_id="+comp_id+"bill_no=" + bill_no
	 * +"bill_date="+bill_date+"bill_type="+bill_type
	 * +"cust_code="+cust_code+"cust_name="+cust_name+"freight_mode="+freight_mode
	 * +"currency_code="+currency_code+"exchange_rate="+exchange_rate+"depot_code="+depot_code
	 * +"forecast_shipmentdate="+forecast_shipmentdate+"cust_delivery_address="+cust_delivery_address
	 * +"other_amt="+other_amt+"total_qty="+total_qty+"total_amt="+total_amt
	 * +"login_empid="+login_empid+"login_date="+login_date
	 * +"check_sign="+check_sign+"check_empid="+check_empid+"check_date="+check_date
	 * +"audit_sign="+audit_sign+"audit_empid="+audit_empid
	 * +"audit_date="+audit_date+"indepot_sign="+indepot_sign+"shipment_sign="+shipment_sign
	 * +"outdepot_sign="+outdepot_sign
	 * +"end_sign="+end_sign+"outdepot_sign="+outdepot_sign+"end_sign="+end_sign
	 * +"end_date="+end_date+"end_empid="+end_empid
	 * +"Transport_billno="+Transport_billno;
	 **************************************************************************/

	var data = "cust_name=" + cust_name;

	var url = baseurl + "/orderStatistics/searchOrderInfoForNum";
	sendRequest("post", url, data, getSearchOrderInfoForNumResult);
	return false;
};
/**
 * 查询订单数量汇总请求的返回结果
 * 
 * @param result
 * @returns {Boolean}
 */
function getSearchOrderInfoForNumResult(result) {
	$("#detailForType").hide();
	$("#detailForNum").show();
	$("#showNumTable").empty();
	
	var data = eval("(" + result + ")");
	if (data != '') {
		var div = $("#showNumTable");
		// System.out.println("data.saleList.length:"+data.saleList.length);
		for (var i = 0; i < data.saleList.length; i++) {
			var html = "<tr style='text-align:center;'><td style='border-bottom: 1px solid #e5e5e5;'>"
					+ (i + 1)
					+ "</td>"			
					+ "<td style='border-bottom: 1px solid #e5e5e5;'>"
					+ data.saleList[i].cust_code
					+ "</td>"	
					+ "<td id='bill_no' style='border-bottom: 1px solid #e5e5e5;'>"
					+ data.saleList[i].bill_no
					+ "</td>"
					+ "<td style='border-bottom: 1px solid #e5e5e5;'>"
					+ data.saleList[i].bill_date
					+ "</td>"						
					+ "<td style='border-bottom: 1px solid #e5e5e5;'>"
					+ data.saleList[i].total_qty
					+ "</td>"
					+ "<td style='border-bottom: 1px solid #e5e5e5;'>"
					+ data.saleList[i].total_amt
					+ "</td>"				
					+ "<td style='border-bottom: 1px solid #e5e5e5;'><div onclick='orderStatisticsDetail(this);' style='color:blue;'>详情</div></td></tr>";
			div.append(html);
			// 数量小计
			for (var j = 0; j < data.Subtotal.length; j++) {
				var name = data.Subtotal[j].cust_code;// 经销商代码
				if (i == data.saleList.length - 1) {// 最后一条
					if (name == data.saleList[i].cust_code) {
						var SubtotalHtml = "<tr><td colspan='7'><a  class='weui-cell weui-cell_access' href='javascript:;'>"
								+ "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>订单数量："
								+ data.Subtotal[j].number
								+ "</p></div>"
								+ " </a></td></tr>";
						div.append(SubtotalHtml);
						break;
					}
				} else {
					if (name == data.saleList[i].cust_code && name != data.saleList[i + 1].cust_code) {
						var SubtotalHtml = "<tr><td colspan='7'><a  class='weui-cell weui-cell_access' href='javascript:;'>"
								+ "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>订单数量："
								+ data.Subtotal[j].number
								+ "</p></div>"
								+ " </a></td></tr>";
						div.append(SubtotalHtml);
						break;
					}
				}

			}
			// 数量总计
			if (i == data.saleList.length - 1) {
				var totalHtml = "<tr><td colspan='7'><a  class='weui-cell weui-cell_access' href='javascript:;'>"
						+ "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>订单总量："
						+ data.num + "</p></div>" + " </a></td></tr>";
				div.append(totalHtml);
			}

		}
	}
}

/**
 * 查询订单车型汇总
 */
function searchOrderInfoForType() {
	$("#wrapper").css("width", "1000px");
	$("#currentUser").css("margin-left", "30px");
	$("#inputMaterialName").css("margin-left", "30px");
	$("#searchSumNum").css("width", "150px");
	$("#searchSumType").css("width", "150px");

	$("#detailForNum").hide();
	$("#detailForType").show();
	$("#showTypeTable").empty();
	// var cust_code = $("select[name='materialName']").val();
	var cust_code = $("input[name='materialName']").val();
	
	var data = "cust_code=" + cust_code;

	var url = baseurl + "/orderStatistics/searchOrderInfoForType";
	sendRequest("post", url, data, getSearchOrderInfoForTypeResult);
	return false;
};
/**
 * 查询订单车型汇总请求的返回结果
 * 
 * @param result
 * @returns {Boolean}
 */
function getSearchOrderInfoForTypeResult(result) {
	$("#detailForNum").hide();
	$("#detailForType").show();
	$("#showTypeTable").empty();
	var data = eval("(" + result + ")");
	if (data != '') {
		var div = $("#showTypeTable");
		for (var i = 0; i < data.saleList.length; i++) {
			var html = "<tr style='text-align:center;'><td style='border-bottom: 1px solid #e5e5e5;'>"
					+ (i + 1)
					+ "</td>"

					+ "<td style='border-bottom: 1px solid #e5e5e5;'>"
					+ data.saleList[i].material_name
					+ "</td>"
					+ "<td style='border-bottom: 1px solid #e5e5e5;'>"
					+ data.saleList[i].color_desc
					+ "</td>"
					+ "<td style='border-bottom: 1px solid #e5e5e5;'>"
					+ data.saleList[i].so_qty
					+ "</td>"
					+ "<td style='border-bottom: 1px solid #e5e5e5;'>"
					+ data.saleList[i].material_code
					+ "</td>"
					+ "<td style='border-bottom: 1px solid #e5e5e5;'>"
					+ data.saleList[i].color_code
					+ "</td></tr>";
			div.append(html);
			// 车型小计
			for (var j = 0; j < data.Subtotal.length; j++) {
				var code = data.Subtotal[j].material_code;// 物料代码
				var type = data.Subtotal[j].color_code;// 颜色代码
				if (i == data.saleList.length-1) {// 最后一条
					if (code == data.saleList[i].material_code && type ==data.saleList[i].color_code) {
						var SubtotalHtml = "<tr><td colspan='5'><a  style='width:100%;' class='weui-cell weui-cell_access' href='javascript:;'>"
								+ "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>该车型订单数量："
								+ data.Subtotal[j].num
								+ "</p></div>"
								+ " </a></td></tr>";
						div.append(SubtotalHtml);
						break;
					}
				} else {
					if ((code == data.saleList[i].material_code && type == data.saleList[i].color_code)
							&& (code != data.saleList[i + 1].material_code || type !=data.saleList[i+1].color_code)) {
						var SubtotalHtml = "<tr><td colspan='5'><a  class='weui-cell weui-cell_access' href='javascript:;'>"
								+ "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>该车型订单数量："
								+ data.Subtotal[j].num
								+ "</p></div>"
								+ " </a></td></tr>";
						div.append(SubtotalHtml);
						break;
					}/**else if(((code == data.saleList[i].material_code && type != data.saleList[i].color_code)||(code != data.saleList[i].material_code && type == data.saleList[i].color_code)) && (code != data.saleList[i + 1].material_code || type !=data.saleList[i+1].color_code)){
						var SubtotalHtml = "<tr><td colspan='5'><a  class='weui-cell weui-cell_access' href='javascript:;'>"
							+ "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>该车型订单数量："
							+ data.Subtotal[j].num
							+ "</p></div>"
							+ " </a></td></tr>";
					div.append(SubtotalHtml);
					break;	
					}**/
				}

			}
			// 总计
			if (i == data.saleList.length - 1) {
				var totalHtml = "<tr><td colspan='5'><a  class='weui-cell weui-cell_access' href='javascript:;'>"
						+ "<div class='weui-cell__bd' style='text-align:left;margin-right:20px;'><p>所有车型数量总计："
						+ data.num + "</p></div>" + " </a></td></tr>";
				div.append(totalHtml);
			}
		}
	}
}

/**
 * 更多订单查询查看订单详情
 * 
 * @param obj
 */
function orderStatisticsDetail(obj) {
	
	var bill_no = $.trim($(obj).parent().parent().find("#bill_no").text());
	// var bill_no = $.trim($(obj).find("#bill_no").text());
	var data = "bill_no=" + bill_no;

	var url = baseurl+ "/views/statisticalManagement/orderStatisticsDetail.html";
	window.location.href = url + "?" + data;
}

/**
 * 关闭弹出框
 */
function closeDialog2() {
	$('#iosDialog2').fadeOut(200);
}
