$(function(){
	//获取当前登录用户信息
//	getCurrentUser();
	var data = "";
	var url = baseurl + "/audit/findAuditOrder";
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
		var accountType = data.currentAccount.type;//账号类型
		 if(accountType == '1'){//经销商
			$("#sellerAccount").hide();	
			alert("请登录销售内勤的账号");
			window.location.href =  baseurl + "/views/login/personalInfoHome.html";
		} 
		
	}
	return false;
}

function findOrderByCust(){
	var code = document.getElementById("searchInput").value;
	var data = "cust_code=" + code;
	var url = baseurl + "/audit/findAuditOrder";
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
			 if(data[i].check_sign == false){
			    	var state = "待复核";
			    }else if(data[i].check_sign == true && data[i].audit_sign == false){
			    	var state = "待财务二审";
			    }else if(data[i].check_sign == true && data[i].audit_sign == true && data[i].shipment_sign == false){
			    	var state = "待出货";
			    }else if(data[i].check_sign == true && data[i].audit_sign == true && data[i].shipment_sign == true && data[i].cust_sign == false){
			    	var state = "待客户回签";
			    }else if(data[i].check_sign == true && data[i].audit_sign == true && data[i].shipment_sign == true && data[i].cust_sign == true && data[i].end_sign == false){
			    	var state = "待结案";
			    }else if(data[i].check_sign == true && data[i].audit_sign == true && data[i].shipment_sign == true && data[i].end_sign == true){
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
									"<label class='order-item-fare' id='cust_code'>"+data[i].cust_code+"</label></div>" +
									"<p class='order-item-fare pull-right'>总数量："+s.substring(0,s.indexOf("."))+"</p>" +			
											"<p class='order-item-prince '>总金额：￥"+data[i].local_totalamt+"</p>" +
															"</div></div></div></a>" +
																	"<div class='order-item-btn pull-right' id='confirmButton'>" +
																	"<button class='btn btn-sm btn-default' onclick=\"cancelAuditOrder('"+data[i].comp_id+"','"+data[i].bill_no+"','"+data[i].bill_date+"')\">取消审核</button></div></div>" 	
			/*if(state == '待出货'){
				document.getElementById("confirmButton").removeAttribute('style');
			}		*/											
			orderList.append(html);		   
		}
	}
}
//查看订单详细
function getOrderDetail(comp_id,bill_no){
	var data = "comp_id=" + comp_id + "&bill_no=" + bill_no;
	var url = baseurl +"/audit/getOrderDetail";
	sendRequest("post",url, data ,getOrderDetailResult);	
}

function getOrderDetailResult(result){
	
	document.getElementById("overallOrder").setAttribute("style", "display:none");
	document.getElementById("orderDetail").removeAttribute("style");
	document.getElementById("orderHeader").setAttribute("style", "display:none");
	document.getElementById("detailHeader").removeAttribute("style");
	var data = eval("(" + result + ")");
	if(data != ''){
		var div = $("#showDetailTable");
		for(var i=0; i < data.length ; i++){
			var s = data[i].so_qty;
			var html = "<tr style='text-align:center;'>"+
     		"<td id='bill_no' style='border-bottom: 1px solid #e5e5e5;'>"+data[i].bill_no+"</td>"+
     		"<td id='cust_code' style='border-bottom: 1px solid #e5e5e5;'>"+data[i].cust_code+"</td>"+
     		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data[i].material_name+"</td>"+
     		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data[i].material_spec+"</td>"+
     		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data[i].color_desc+"</td>"+
     		"<td style='border-bottom: 1px solid #e5e5e5;'>"+data[i].stand_price+"</td>"+
     		"<td style='border-bottom: 1px solid #e5e5e5;'>"+s.substring(0,s.indexOf("."))+"</td>"+
     		"</tr>";
			div.append(html);
		}
	   
	}else{
		document.getElementById("orderDetail").setAttribute("style", "display:none");
		alert("未查出任何记录！");
	}
}

function cancelAuditOrder(comp_id,bill_no,bill_date){
	var data = "comp_id=" + comp_id + "&bill_no=" + bill_no + "&bill_date=" + bill_date;
	var url = baseurl + "/audit/cancelAuditOrder";
	sendRequest("post" , url , data , cancelAuditResult);
}

function cancelAuditResult(result){	
	
	if(result == "true"){	
		alert("取消审核订单成功！");
		window.location.reload();
	}else{
		var result = result.substring(0,result.indexOf('The error may involve'));
	    var reg = /[\,\u4e00-\u9fa5]/g;
		
	    var datas = result.match(reg);
	    result = datas.join("");
		alert("取消审核订单失败！原因："+result);
	}
}
