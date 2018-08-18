$(function(){
	//获取当前登录用户信息
	getCurrentUser();
	var data = "";
	var url = baseurl + "/audit/findUnConfirmOrder";
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
		 if(accountType == 'everybody' || accountType == 'everyaudit' || accountType == 'order' || accountType == 'auditOrder'){
//			$("#sellerAccount").hide();	
			
		} else{
			alert("您没有审核订单的权限，请登录财务账号");
			window.location.href =  baseurl + "/views/login/personalInfoHome.html";
		}
		
	}
	return false;
}

function findOrderByCust(){
	var code = document.getElementById("searchInput").value;
	var data = "cust_code=" + code;
	var url = baseurl + "/audit/findUnConfirmOrder";
	sendRequest("post" , url , data , findResult);
}

function findResult(result){
	var orderList = $("#orderList");
	orderList.html(""); //先清空div里的内容
	if(result.toString() == "\[\]"){
//		alert("系统未查询到本条记录，请重新查询！！");
		 $('#toast').fadeIn(100);
	       setTimeout(function () {
	    	   $('#toast').fadeOut(100);
	       }, 2000);			
	}else{
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
	var data = "comp_id=" + comp_id + "&bill_no=" + bill_no;
	var url = baseurl +"/order/getOrderDetail";
	sendRequest("post",url, data ,getOrderDetailResult);	
}

function getOrderDetailResult(result){
//	$("#loadingToast").fadeOut(100);
	document.getElementById("loadingToast").setAttribute("style", "display:none");
	document.getElementById("overallOrder").setAttribute("style", "display:none");
	document.getElementById("orderDetail").removeAttribute("style");
	document.getElementById("orderHeader").setAttribute("style", "display:none");
	document.getElementById("detailHeader").removeAttribute("style");

	var data = eval("(" + result + ")");
	if(data != ''){
		var html = "";
	    var shoppingList = $("#listterm");
	    shoppingList.html("");
		for(var i=0 ; i < data.length ; i++){
			var s = data[i].so_qty;
			html="<li class='select'> " +
					/*"<em aem='0' cart_id='84'></em>" +*/
					"<img src='"+data[i].photo_name+"'/>" +
							" <div class='div_center'>" +
							"<h4 class='name' id='name'>"+data[i].material_name+"</h4>" +
							    "<div class='bill_date' style='display:none'>"+data[i].bill_date+"</div>"+
							    "<div class='material_code' style='display:none'>"+data[i].material_code+"</div>"+
							    "<div class='color_code' style='display:none'>"+data[i].color_code+"</div>"+
							    "<div class='material_type' style='display:none'>"+data[i].material_type+"</div>"+
							    "<div class='unit_code' style='display:none'>"+data[i].unit_code+"</div>"+
							    "<div class='comp_id' style='display:none'>"+data[i].comp_id+"</div>"+
							    "<div class='bill_no' style='display:none'>"+data[i].bill_no+"</div>"+
							    "<div class='s_n' style='display:none'>"+data[i].s_n+"</div>"+
							    "<div class='spec' id='spec'>"+data[i].material_spec+"</div>" +
							        "<span class='color' id='color'>"+data[i].color_desc+"</span>" +
											"<p class='now_value'>" +
											 "<i>￥</i>" +
											 "<b class='qu_su' id='price'>"+data[i].stand_price+"</b>" +
											"</p></div>" +
												"</li>";
		    shoppingList.append(html);
		}		
   }
}
function firstAuditOrder(){
	var comp_id = $(".commodity_box .select .comp_id").eq(0).text();
	var bill_no = $(".commodity_box .select .bill_no").eq(0).text();
	var bill_date = $(".commodity_box .select .bill_date").eq(0).text();
	var data = "comp_id=" + comp_id + "&bill_no=" + bill_no + "&bill_date=" + bill_date;
	
	var url = baseurl + "/audit/firstAuditOrder";
	sendRequest("post" , url , data ,firstAuditOrderResult);
}

function firstAuditOrderResult(result){
	if(result){
		alert("成功审核订单！");
		window.location.reload();
	}else{
		alert("审核订单失败！");
		window.location.reload();
	}
	
}