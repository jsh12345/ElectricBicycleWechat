/**
 * 订单信息查询
 */

/**
 * 页面加载函数
 */
$(function() {
	// 获取当前登录用户信息
	getCurrentUser();
	// 页面加载时查询近期订单信息
	searchOrderInfoOnload();
});

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
		/* if(accountType == '经销商' || accountType == '销售内勤'|| accountType == '财务'|| accountType == '销售内勤,财务'){//
//			$("#sellerAccount").hide();			
			 alert("您没有查看统计订单的权限，请登录普通员工账号");
			window.location.href =  baseurl + "/views/login/login.html";	
	     }
		 else if(accountType == '普通员工'){
			
				$("input[name='currentUser']").val(data.name);
		}*/
		
		if(accountType == '经销商'){//
//			$("#sellerAccount").hide();			
			 alert("您没有查看统计订单的权限，请登录厂商内部账号");
			window.location.href =  baseurl + "/views/login/personalInfoHome.html";	
	     }
		 else{			
			$("input[name='currentUser']").val(data.name);
	}
	return false;
	}
}

/**
 * 查询近期订单信息
 */
function searchOrderInfoOnload() {
	var data = "";
	var url = baseurl + "/orderStatistics/searchOrderInfoOnload";
	sendRequest("post", url, data, getSearchOrderInfoOnloadResult);
	return false;
};
/**
 * 查询近期订单信息请求的返回结果
 * 
 * @param result
 * @returns {Boolean}
 */
function getSearchOrderInfoOnloadResult(result) {
	var data = eval("(" + result + ")");
	if (data != '') {
		var div = $("#showDiv");
		for (var i = 0; i < data.length; i++) {
			var html = "<a onclick='orderStatisticsDetail(this);' class='weui-cell weui-cell_access' href='javascript:;'>"
					+ "<div class='weui-cell__bd'><p><table>"
					+ "<tr><td>订单号码：</td><td id='bill_no'>"
					+ data[i].bill_no
					+ "</td></tr>"
					+ "<tr><td>下单日期：</td><td id='bill_date'>"
					+ data[i].bill_date
					+ "</td></tr>"
					+ "<tr><td>经销商代码：</td><td id='cust_code'>"
					+ data[i].cust_code
					+ "</td></tr>"
					+ "<tr><td>总数量：</td><td id='total_qty'>"
					+ data[i].total_qty
					+ "</td></tr>"
					+ "<tr><td>总金额：</td><td id='total_amt'>"
					+ data[i].total_amt
					+ "</td></tr>"
					+ "</table> </p></div>"
					+ "<div class='weui-cell__ft'>查看详情</div> </a>";
			div.append(html);
		}
	}
}

/**
 * 查看订单详情
 * 
 * @param obj
 */
function orderStatisticsDetail(obj) {
	var bill_no = $.trim($(obj).find("#bill_no").text());
	var data = "bill_no="+bill_no;
	var url = baseurl
			+ "/views/statisticalManagement/orderStatisticsDetail.html";
	window.location.href = url + "?" +data;
}

/**
 * 查看更多
 */
function orderStatisticsMore() {
	window.location.href = baseurl
			+ "/views/statisticalManagement/orderStatisticsMore.html";
}

/**
 * 关闭弹出框
 */
function closeDialog2() {
	$('#iosDialog2').fadeOut(200);
}
