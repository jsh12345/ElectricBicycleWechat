$(function(){
	//获取当前登录用户信息
	getCurrentUser();
	
	var data ="" ;
	var url = baseurl + "/order/getOrderList";   
	sendRequest("post",url, data ,getListResult);	
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
		if(accountType == '经销商'){
			$("#sellerAccount").hide();
		}else if(accountType == '销售内勤'){
			alert("请用经销商账号重新登录");
			window.location.href = baseurl + "/views/login/personalInfoHome.html";
		}
	}
	return false;
}

function getListResult(result){
	if(result.toString() == "\[\]"){	
		alert("空空如也");
	}else{
		var data = eval("(" + result + ")");			
		var orderList = $("#orderList");
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
									"<label class='order-item-fare'>总数量：</label>" +
									"<span class='order-item-fare' id='total_qty'>"+s.substring(0,s.indexOf("."))+"</span></div>" +
											"<div class='order-item-info'>" +
											"<label class='order-item-prince'>总金额：￥</label>" +
											"<span class='order-item-prince' id='total_amt'>"+data[i].total_amt+"</span></div> " +
													"<div class='order-item-info'> <label class='order-item-length'>下单时间：</label>" +
															"<span class='order-item-length' id='bill_date'>"+data[i].bill_date.substring(0,10)+"</span></div></div></div></div></a>" +
																	"<div class='order-item-btn pull-right' id='confirmButton' style='display:none'>" +
																	"<button class='btn btn-sm btn-default' onclick=signOrder('"+data[i].comp_id+"','"+data[i].bill_no+"')>签收订单</button></div></div>" 
			
			orderList.append(html);
			if(state == "待结案"){				
				 document.getElementById("confirmButton").removeAttribute("style");
		    }
		}
	
	}
	
}
//获取对应订单的详细信息
function getOrderDetail(comp_id, bill_no){		
	var data = "comp_id=" + comp_id + "&bill_no=" + bill_no;
	var url = baseurl +"/order/getOrderDetail";
	sendRequest("post",url, data ,getDetailResult);	
}

function getDetailResult(result){	
	document.getElementById("overallOrder").setAttribute("style", "display:none");
	document.getElementById("orderDetail").removeAttribute("style");
	document.getElementById("orderHeader").setAttribute("style", "display:none");
	document.getElementById("detailHeader").removeAttribute("style");
	var data = eval("(" + result + ")");
	if(data != ''){
		var orderList = $("#detailList");
		for(var i=0 ; i < data.length ; i++){
			var m = data[i].so_qty;
			var html = "<div class='find-group1'>" +
					"<div class='group1-bd clearfix'>" +
					"<div class='pull-left bd-l'>" +
					"<div class='bd-img'><img src='"+data[i].photo_name+"' alt=''></div></div> " +
					"<div class='pull-right bd-r'><h4 class='bd-title'>"+data[i].material_name+"</h4>" +
							"<span class='bd-title'>"+data[i].material_spec+"</span>" +
					"<span class='bd-intro'>"+data[i].color_desc+"</span>" +
					"<span class='bd-intro'>"+data[i].so_qty+"</span>" +
					"<p class='bd-price' style='margin-top:10px;'>￥"+data[i].stand_price+"</p>" +
					"</div></div></div>";
		    orderList.append(html);
		}
	}	
	
}

//签收订单
function signOrder(comp_id, bill_no){
	
	var data = "comp_id=" + comp_id + "&bill_no=" + bill_no;
	var url = baseurl +"/order/signOrder";
	sendRequest("post",url, data ,signOrderResult);	
}

function signOrderResult(result){
	if(result == "true"){
		alert("已成功签收订单！");
		document.getElementById("confirmButton").setAttribute("style", "display:none");
		window.location.reload();
	}else{
		alert("签收订单失败！");
		window.location.reload();
	}
}

