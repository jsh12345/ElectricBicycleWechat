$(function(){
	//获取当前登录用户信息
	getCurrentUser();
	var data = "";
	var url = baseurl + "/userProperty/findUnConfirmOrder";
	sendRequest("post" , url , data , findResult);
});

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
//		$("input[name='currentUser']").val(data.name);
		var accountType = data.type;//账号类型		
		if(accountType == 'everybody' || accountType == 'order' || accountType == 'checkaudit' || accountType == 'check'){
//			$("#sellerAccount").hide();			
		} else{
			alert("您没有确认订单的权限，请使用内勤账号登录");
			window.location.href = baseurl + "/views/login/personalInfoHome.html";
		}		
	}
	return false;
}

function findOrderByCust(){
	var code = document.getElementById("searchInput").value;
	var data = "cust_code=" + code;
	var url = baseurl + "/userProperty/findUnConfirmOrder";
	sendRequest("post" , url , data , findResult);
}

function findResult(result){
	if(result.toString() == "\[\]"){
//		alert("系统未查询到本条记录，请重新查询！！");
		$('#toast').fadeIn(100);
	       setTimeout(function () {
	    	   $('#toast').fadeOut(100);
	       }, 2000);		
	}else{
		var orderList = $("#orderList");
		orderList.html(""); //先清空div里的内容
		var data = eval("(" + result + ")");			
		
		for(var i=0 ; i < data.length ; i++){	
			var s = data[i].total_qty;
			if(data[i].check_sign == false && data[i].audit_sign == false && data[i].indepot_sign == false && data[i].shipment_sign == false && data[i].outdepot_sign == false && data[i].end_sign == false){
				var state = "待内勤审核";
			}else if(data[i].check_sign == true && data[i].audit_sign == false && data[i].indepot_sign == false && data[i].shipment_sign == false && data[i].outdepot_sign == false && data[i].end_sign == false){
				var state = "待财务审核";
			}else if(data[i].check_sign == true && data[i].audit_sign == true && data[i].indepot_sign == false && data[i].shipment_sign == false && data[i].outdepot_sign == false && data[i].end_sign == false){
				var state = "待生产入库";
			}else if(data[i].check_sign == true && data[i].audit_sign == true && data[i].indepot_sign == true && data[i].shipment_sign == false && data[i].outdepot_sign == false && data[i].end_sign == false){
				var state = "待开发货通知单";
			}else if(data[i].check_sign == true && data[i].audit_sign == true && data[i].indepot_sign == true && data[i].shipment_sign == true && data[i].outdepot_sign == false && data[i].end_sign == false){
				var state = "待扫描出库";
			}else if(data[i].check_sign == true && data[i].audit_sign == true && data[i].indepot_sign == true && data[i].shipment_sign == true && data[i].outdepot_sign == true && data[i].end_sign == false){				
				var state = "待结案";
			}else if(data[i].check_sign == true && data[i].audit_sign == true && data[i].indepot_sign == true && data[i].shipment_sign == true && data[i].outdepot_sign == true && data[i].end_sign == true){
				var state = "已结案";
			}
		    var html = "<a href=javascript:getOrderDetail('"+data[i].comp_id+"','"+data[i].bill_no+"')>" +
					"<div class='order-group-item clearfix'>" +
					"<div class='order-item-box'>" +
					"<label class='order-item-id'>订单号：</label>" +
					"<span class='order-item-id' id='bill_no'>"+data[i].bill_no+"</span>" +
							"<span class='order-item-state theme-color pull-right' id='state'>" + state + "</span> " +
									"<div class='media'>" +
									"<div class='media-body'>" +
									"<div class='order-item-info'>" +
									"<label class='order-item-fare'>经销商：</label>" +
									"<span class='order-item-fare' id='cust_name'>"+data[i].cust_name+"</span>" +
									"<label class='order-item-fare' id='cust_code'>"+data[i].cust_code+"</label></div>" +
									"<p class='order-item-fare pull-right'>总数量："+s.substring(0,s.indexOf("."))+"</p>" +			
											"<p class='order-item-prince '>总金额：￥"+data[i].total_amt+"</p>" +
											" <p class='order-item-length pull-right'>下单日期："+data[i].bill_date.substring(0,10)+"</p>" +
											" <p class='order-item-length'>期望到货日期："+data[i].receive_date.substring(0,10)+"</p>" +
															"</div></div></div></a>" +
																	"<div class='order-item-btn pull-right' id='confirmButton' style='display:none'>" +
																	"<button class='btn btn-sm btn-default' onclick=signOrder('"+data[i].comp_id+"','"+data[i].bill_no+"')>确认订单</button></div></div>" 	
																	
			orderList.append(html);
		   
		}
	}
	
}

//查看订单详细
function getOrderDetail(comp_id,bill_no){
	$("#loadingToast").fadeIn(100);
	window.location.href = "confirmOrderDetail.html?comp_id="+comp_id+"&bill_no="+bill_no;
}

