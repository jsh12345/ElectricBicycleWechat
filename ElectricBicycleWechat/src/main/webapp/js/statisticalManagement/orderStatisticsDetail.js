/**
 * 订单信息详情
 */

/**
 * 页面加载函数
 */
$(function() {
	// 页面加载时订单信息详情
	
	orderStatisticsDetail();
});

/**
 * 获取地址栏参数
 * 
 * @param name
 * @returns
 */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

/**
 * 查看订单详情
 * 
 * @param obj
 */
function orderStatisticsDetail() {
	
	//var comp_id = GetQueryString("comp_id");
//    alert("123"+comp_id);
	var bill_no = GetQueryString("bill_no");
	/**var bill_date = GetQueryString("bill_date");
	var bill_type = GetQueryString("bill_type");	
	var cust_code = GetQueryString("cust_code");
	var cust_name = GetQueryString("cust_name");
	var freight_mode = GetQueryString("freight_mode");
	var currency_code = GetQueryString("currency_code");
	var exchange_rate = GetQueryString("exchange_rate");
	var depot_code = GetQueryString("depot_code");
	var forecast_shipmentdate = GetQueryString("forecast_shipmentdate");
	var cust_delivery_address = GetQueryString("cust_delivery_address");
	var other_amt = GetQueryString("other_amt");
	var total_qty = GetQueryString("total_qty");
	var total_amt = GetQueryString("total_amt");
	var login_empid = GetQueryString("login_empid");
	var login_date = GetQueryString("login_date");
	var check_sign = GetQueryString("check_sign");
	var check_empid = GetQueryString("check_empid");
	var check_date = GetQueryString("check_date");
	var audit_sign = GetQueryString("audit_sign");
	var audit_empid = GetQueryString("audit_empid");
	var audit_date = GetQueryString("audit_date");
	var indepot_sign = GetQueryString("indepot_sign");
	var shipment_sign = GetQueryString("shipment_sign");
	var outdepot_sign = GetQueryString("outdepot_sign");
	var end_sign = GetQueryString("end_sign");
	var end_date = GetQueryString("end_date");
	var end_empid = GetQueryString("end_empid");
	var Transport_billno = GetQueryString("Transport_billno");
	**/
	/**
	var data = "comp_id="+comp_id+"bill_no=" + bill_no +"bill_date="+bill_date+"bill_type="+bill_type
	+"cust_code="+cust_code+"cust_name="+cust_name+"freight_mode="+freight_mode
	+"currency_code="+currency_code+"exchange_rate="+exchange_rate+"depot_code="+depot_code
	+"forecast_shipmentdate="+forecast_shipmentdate+"cust_delivery_address="+cust_delivery_address
	+"other_amt="+other_amt+"total_qty="+total_qty+"total_amt="+total_amt
	+"login_empid="+login_empid+"login_date="+login_date
	+"check_sign="+check_sign+"check_empid="+check_empid+"check_date="+check_date
	+"audit_sign="+audit_sign+"audit_empid="+audit_empid
	+"audit_date="+audit_date+"indepot_sign="+indepot_sign+"shipment_sign="+shipment_sign
	+"outdepot_sign="+outdepot_sign
	+"end_sign="+end_sign+"outdepot_sign="+outdepot_sign+"end_sign="+end_sign
	+"end_date="+end_date+"end_empid="+end_empid
	+"Transport_billno="+Transport_billno;**/
	
	var data = "bill_no="+bill_no;
	var url = baseurl + "/orderStatistics/orderDetail";
	sendRequest("post", url, data, getOrderStatisticsDetailResult);
	return false;
}

/**
 * 查看订单详情请求的返回结果
 * 
 * @param result
 * @returns {Boolean}
 */
function getOrderStatisticsDetailResult(result) {
	var data = eval("(" + result + ")");
	
	$("#showTable td[name='comp_id']").text(data.comp_id);
	$("#showTable td[name='bill_no']").text(data.bill_no);
	$("#showTable td[name='bill_date']").text(data.bill_date);
	$("#showTable td[name='bill_type']").text(data.bill_type);
	$("#showTable td[name='cust_code']").text(data.cust_code);
	$("#showTable td[name='cust_name']").text(data.cust_name);
	$("#showTable td[name='freight_mode']").text(data.freight_mode);
	$("#showTable td[name='currency_code']").text(data.currency_code);
	$("#showTable td[name='exchange_rate']").text(data.exchange_rate);
	$("#showTable td[name='depot_code']").text(data.depot_code);
	$("#showTable td[name='forecast_shipmentdate']").text(
			data.forecast_shipmentdate);
	$("#showTable td[name='cust_delivery_address']").text(
			data.cust_delivery_address);
	$("#showTable td[name='other_amt']").text(data.other_amt);
	$("#showTable td[name='total_qty']").text(data.total_qty);
	$("#showTable td[name='total_amt']").text(data.total_amt);
	$("#showTable td[name='login_empid']").text(data.login_empid);
	$("#showTable td[name='login_date']").text(data.login_date);
	$("#showTable td[name='check_sign']").text(data.check_sign);
	$("#showTable td[name='check_empid']").text(data.check_empid);
	$("#showTable td[name='check_date']").text(data.check_date);
	$("#showTable td[name='audit_sign']").text(data.audit_sign);
	$("#showTable td[name='audit_empid']").text(data.audit_empid);
	$("#showTable td[name='audit_date']").text(data.audit_date);
	$("#showTable td[name='indepot_sign']").text(data.indepot_sign);
	$("#showTable td[name='shipment_sign']").text(data.shipment_sign);
	$("#showTable td[name='outdepot_sign']").text(data.outdepot_sign);
	$("#showTable td[name='end_sign']").text(data.end_sign);
	$("#showTable td[name='end_date']").text(data.end_date);
	$("#showTable td[name='end_empid']").text(data.end_empid);
	
	
	$("#showTable td[name='Transport_billno']").text(data.transport_billno);	
}

/**
 * 关闭弹出框
 */
function closeDialog2() {
	$('#iosDialog2').fadeOut(200);
}
