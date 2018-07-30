$(function(){
	var data ="" ;
	var url = baseurl + "/order/getShoppingList";     
	sendRequest("post",url, data ,getListResult);	
});

function getListResult(result){
	var data = eval("(" + result + ")");
	if(data != ''){
	    var shoppingList = $("#listterm");
		for(var i=0 ; i < data.length ; i++){
			var s = data[i].so_qty;
			var html="<li class='select'> " +
					/*"<em aem='0' cart_id='84'></em>" +*/
					"<img src='"+data[i].photo_name+"'/>" +
							" <div class='div_center'>" +
							"<h4 class='name' id='name'>"+data[i].material_name+"</h4>" +
							    "<div class='material_code' style='display:none'>"+data[i].material_code+"</div>"+
							    "<div class='color_code' style='display:none'>"+data[i].color_code+"</div>"+
							    "<div class='material_type' style='display:none'>"+data[i].material_type+"</div>"+
							    "<div class='unit_code' style='display:none'>"+data[i].unit_code+"</div>"+
							    "<div class='spec' id='spec'>"+data[i].material_spec+"</div>" +
							        "<span class='color' id='color'>"+data[i].color_desc+"</span>" +
											"<p class='now_value'>" +
											 "<i>￥</i>" +
											 "<b class='qu_su' id='price'>"+data[i].stand_price+"</b>" +
											"</p></div>" +
											"<div class='div_right'>" +
											"<i onclick='reducew(this)'>-</i>" +
											"<span class='zi' id='qty'>"+s.substring(0,s.indexOf("."))+"</span>" +
													"<input type='hidden' value='84'>" +
													"<i onclick='plusw(this)'>+</i> </div>"+
													"<div class='order-item-btn pull-right' id='confirmButton'" +
													"<button class='btn btn-sm btn-default' onclick=deleteOrder('"+data[i].material_code+"','"+data[i].color_code+"')>删除</button></div></li>";
		    shoppingList.append(html);
		}
   }
}

function confirmOrder(){
	var total_amt = $("#total_price b").text();
	if(total_amt == 0 ){
		alert("请先点击计算总金额");
	}else{
	    $('#iosDialog1').fadeIn(200);
	}
}
function cancelOrder(){
	$('#iosDialog1').fadeOut(200);
}
function cancel(){
	$('#iosDialog2').fadeOut(200);
}

function deleteOrder(material_code,color_code){	
	$('#iosDialog2').fadeIn(200);
	$('#confirmDelete').click(function(){
		var data="material_code="+material_code+"&color_code="+color_code;
		var url = baseurl + "/order/deleteOrder";
		sendRequest("post" , url ,data ,deleteOrderResult);
	});		
}

function deleteOrderResult(result){
	if(result == "true"){
		$('#iosDialog2').fadeOut(200);
		alert("成功删除此类车！");
		window.location.reload();
	}else{
		$('#iosDialog2').fadeOut(200);
		alert("删除该电动车失败！");
		window.location.reload();
	}
}
/**
 * 提交订单，利用数组批量操作提交数据
 */
function submitOrder(){
	
	var material_code = $(".commodity_box .select .material_code");
	var material_type = $(".commodity_box .select .material_type");
	var color_code = $(".commodity_box .select .color_code");
	var unit_code = $(".commodity_box .select .unit_code");
	var name = $(".commodity_box .select .name");
	var spec = $(".commodity_box .select .spec");
	var color = $(".commodity_box .select .color");
	var price = $(".commodity_box .select .qu_su");
	var qty = $(".commodity_box .select .zi");
	var detail = new Array();//封装成一个对象数组
	
	for(var i=0 ; i < name.length ; i++ ){  		
		var obj = new Object();
		
		obj.name = name.eq(i).text();    	    	
    	obj.spec = spec.eq(i).text();    		
    	obj.color = color.eq(i).text();   			
    	obj.price = price.eq(i).text();   				
    	obj.qty = qty.eq(i).text(); 
        obj.material_code = material_code.eq(i).text(); 
    	obj.material_type = material_type.eq(i).text();
    	obj.color_code = color_code.eq(i).text();
    	obj.unit_code = unit_code.eq(i).text(); 
				
	    detail.push(obj);
	}	
	  
    var total_amt = $("#total_price b").text();
    var total_qty = 0;
    for(var i=0 ; i < qty.length ; i++ ){
    	 total_qty  += parseInt(qty.eq(i).text());
    } 
    
    var receive_date = document.getElementById("receive_date").value;
	var data="total_amt=" + total_amt + "&total_qty=" + total_qty + "&receive_date="+ receive_date + "&detail="+JSON.stringify(detail);

	var url = baseurl + "/order/submitOrder";     
	sendRequest("post",url, data ,submitResult);	
}
function submitResult(result){
	
	if(result == "true"){		
		$('#iosDialog1').fadeOut(200);
		alert("订单提交成功！");
		window.location.reload();
	}else{
		$('#iosDialog1').fadeOut(200);
		alert("订单提交失败！");
		window.location.reload();
	}
}
