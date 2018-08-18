//URL中取参，防止中文乱码
function GetRequest() { 
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
			theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]); 
		} 
	} 
	return theRequest; 
}

$(function(){
	Request = GetRequest(); 
	var comp_id = Request['comp_id'];	
	var bill_no = Request['bill_no'];
	var data = "comp_id=" + comp_id + "&bill_no=" + bill_no;

	var url = baseurl +"/order/getOrderDetail";
	sendRequest("post",url, data ,getDetailResult);	
});

function getDetailResult(result){
	
	var data = eval("(" + result + ")");
	if(data != ''){
		$("#loadingToast").fadeOut(100);
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
							    "<div class='material_code' style='display:none'>"+data[i].material_code+"</div>"+
							    "<div class='color_code' style='display:none'>"+data[i].color_code+"</div>"+
							    "<div class='material_type' style='display:none'>"+data[i].material_type+"</div>"+
							    "<div class='unit_code' style='display:none'>"+data[i].unit_code+"</div>"+
							    "<div class='comp_id' style='display:none'>"+data[i].comp_id+"</div>"+
							    "<div class='bill_no' style='display:none'>"+data[i].bill_no+"</div>"+
							    "<div class='bill_date' style='display:none'>"+data[i].bill_date+"</div>"+
							    "<div class='s_n' style='display:none'>"+data[i].s_n+"</div>"+
							    "<div class='spec' id='spec'>"+data[i].material_spec+"</div>" +
							        "<span class='color' id='color'>"+data[i].color_desc+"</span>" +
											"<p class='now_value'>" +
											 "<i>￥</i>" +
											 "<b class='qu_su' id='price'>"+data[i].stand_price+"</b>" +
											"</p></div>" +
											"<div class='div_right'>" +
											"<i onclick='reducew(this)'>-</i>" +
											"<span class='zi' id='qty'>"+data[i].so_qty+"</span>" +
													"<input type='hidden' value='84'>" +
													"<i onclick='plusw(this)'>+</i> </div>" +
													"<button class='btn btn-sm btn-default' onclick=changePrice('"+data[i].comp_id+"','"+data[i].bill_no+"','"+data[i].s_n+"')>修改单价</button>" +		
													"<button class='btn btn-sm btn-default' onclick=deleteProduct('"+data[i].comp_id+"','"+data[i].bill_no+"','"+data[i].s_n+"','"+data[i].so_qty+"','"+data[i].stand_price+"')>删除</button>" +		
											"</li>";
		    shoppingList.append(html);
		}
		
   }
	commodityWhole();
}

function cancel(){
	$('#iosDialog2').fadeOut(200);
}
function cancel1(){
	$('#iosDialog1').fadeOut(200);
}
//删除电动车
function deleteProduct(comp_id , bill_no , s_n ,so_qty,price){
	$('#iosDialog2').fadeIn(200);
	$('#confirmDelete').click(function(){
	   var data="comp_id=" + comp_id + "&bill_no=" + bill_no + "&s_n=" + s_n + "&so_qty=" + so_qty + "&price=" + price;
	   var url = baseurl + "/userProperty/deleteProduct";
	   sendRequest("post" , url ,data ,deleteProductResult);
	});
}

function deleteProductResult(result){
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

function changePrice(comp_id , bill_no, s_n){
	$('#iosDialog1').fadeIn(200);
	$('#confirmChange').click(function(){
		var newPrice = document.getElementById("newPrice").value;	
		document.getElementById("price").innerHTML = newPrice;      
		
		var price = $(".commodity_box .select .qu_su");//单价
		var qty = $(".commodity_box .select .zi");//数量
		
		var total_amt = 0;
		for(var i=0 ; i < qty.length ; i++ ){  	
	
	    	var n = price.eq(i).text();
	    	var m = qty.eq(i).text();
		    total_amt = total_amt + (parseFloat(n) * parseFloat(m));
		}	  
        
		var data = "comp_id=" + comp_id + "&bill_no=" + bill_no + "&s_n=" + s_n + "&total_amt=" + total_amt + "&newPrice=" + newPrice;
		
		var url = baseurl + "/userProperty/changePrice";
		sendRequest("post" , url , data , changeResult);
	});
	
}
function changeResult(result){
	if(result == "true"){
		$('#iosDialog1').fadeOut(200);
		alert("成功更新单价！");
		window.location.reload();
	}else{
		$('#iosDialog1').fadeOut(200);
		alert("更新单价失败！");
		window.location.reload();
	}
}

function jumpToNewUrl(){
	var comp_id = $(".commodity_box .select .comp_id").eq(0).text();	
	var bill_no = $(".commodity_box .select .bill_no").eq(0).text();

	window.location.href="addNewProduct.html?comp_id="+comp_id+"&bill_no="+bill_no;//唯一确定订单中的某一产品
}

function saveOrder(){
	var price = $(".commodity_box .select .qu_su");//单价
	var qty = $(".commodity_box .select .zi");//数量
	var comp_id = $(".commodity_box .select .comp_id").eq(0).text();	
	var bill_no = $(".commodity_box .select .bill_no").eq(0).text();
	
    var detail = new Array();//封装成一个对象数组
	
	for(var i=0 ; i < qty.length ; i++ ){  		
		var obj = new Object();	
    	obj.price = price.eq(i).text();   				
    	obj.qty = qty.eq(i).text(); 
        detail.push(obj);
	}	  
    var total_amt = $("#total_price b").text();
    var total_qty = 0;
    for(var i=0 ; i < qty.length ; i++ ){
    	 total_qty  += parseInt(qty.eq(i).text());
    } 
    var url = baseurl + "/userProperty/saveOrder";   
   
    var data="comp_id="+comp_id +"&bill_no="+bill_no +"&total_amt=" + total_amt + "&total_qty=" + total_qty + "&detail="+JSON.stringify(detail);
	sendRequest("post",url, data ,saveResult);
}

function saveResult(result){
	if(result == "true"){				
		alert("保存成功！");	
	}else{
		alert("保存失败！");
	}
}

//确认订单
function confirmOrder(){
	var comp_id = $(".commodity_box .select .comp_id").eq(0).text();	
	var bill_no = $(".commodity_box .select .bill_no").eq(0).text();
	var bill_date = $(".commodity_box .select .bill_date").eq(0).text();
	var data = "comp_id=" + comp_id + "&bill_no=" + bill_no + "&bill_date=" + bill_date;
  
	var url = baseurl +"/userProperty/confirmOrder";
	sendRequest("post",url, data ,getConfirmResult);	
}

function getConfirmResult(result){
	if(result){		
		alert("成功确认订单！");
		window.location.href = "confirmOrder.html";
	}else{
		alert("确认订单失败！");
		window.location.href = "confirmOrder.html";
	}
}
