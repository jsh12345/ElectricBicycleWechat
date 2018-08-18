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
	name = Request['name'];
	spec = Request['spec']; 
	
	document.getElementById("simpleName").innerHTML = name+" " +spec;	
	var data = "name=" + name + "&spec=" + spec;
	var url = baseurl + "/order/getProductColor";     
	sendRequest("post",url, data ,getColorResult);	
});

function getColorResult(result){
	if(result.toString() == "\[\]"){
		alert("系统未查询到本条记录，请重新查询！！");
	}
	else{
		$("#loadingToast").fadeOut(100);
		//解析json
		result = eval("("+ result +")");
		var imageList = $("#imglist");
//		imageList.html("");		
		var sel = document.getElementById("colorcolor");		
		sel.options.add(new Option("请选择颜色",""));
		for(var i=0 ; i < result.length ; i++){
			 var html="<div class='swiper-slide'>" +
 		                "<img src=" + result[i].photo + " alt=''/>" +
 		              "</div>"
            imageList.append(html);		    
			 var swiper = new Swiper('.item-img', {
					autoplay : true,
					delay : 7000,
					slidesPerView : 1,
					spaceBetween : 0,
					keyboard : {
						enabled : true,
					},
					pagination : {
						el : '.swiper-pagination',
						type : 'fraction',
					},
				});
				var MAX = 10, MIN = 1;
				$('.weui-count__decrease').click(
						function(e) {
							var $input = $(e.currentTarget).parent().find(
									'.weui-count__number');
							var number = parseInt($input.val() || "0") - 1
							if (number < MIN)
								number = MIN;
							$input.val(number)
						});
				$('.weui-count__increase').click(
						function(e) {
							var $input = $(e.currentTarget).parent().find(
									'.weui-count__number');
							var number = parseInt($input.val() || "0") + 1
							if (number > MAX)
								number = MAX;
							$input.val(number)
						});
		}	
		
		for(var j=0 ; j < result.length ;j++){
			var pprice = result[0].price;
			document.getElementById("price").innerHTML = pprice.substring(0,pprice.indexOf(".")+3); //此行重要，不过数据库表中的值为0
			
			sel.options.add(new Option(result[j].color,result[j].color));
		    
		}
		
	}
}

function getPhoto(){
	Request = GetRequest(); 
	name = Request['name'];
	spec = Request['spec']; 
	var colorSelect = document.getElementById("colorcolor");
	var color = colorSelect.options[colorSelect.selectedIndex].value; 	
	var data = "name=" + name + "&spec=" + spec + "&color=" + color;
	var url = baseurl + "/order/getProductPhoto";     
	sendRequest("post",url, data ,getPhotoResult);	
}
function getPhotoResult(result){
	var result = eval("(" + result + ")");
	$uploaderFiles = $("#files");
	$uploaderFiles.html("");
	$uploaderFiles.append('<img class="weui-uploader__file" src="' + result.photo + '" alt=""/>');
	var s = result.qty;
	document.getElementById("stockqty").innerHTML = Number(s);
}

/*
 * 点击加入购物车时调用
 */
function ShoppingCart(){
	//获取当前登录用户信息
	getCurrentUser();
	
	var color = $("#colorcolor").val();//检查颜色是否为空
	if(color == ''){
		alert("请先选定颜色！");	
	}else{
		$('#iosDialog1').fadeIn(200);
	}
	
}

function checkNumber(){
	var reg = /^[1-9][0-9]{0,}$/;
	
	var price = document.getElementById("buynumber").value;
	if(!reg.test(price)){
		alert("采购数量须为正整数！");

		document.getElementById("buynumber").value = "";
	}else if(price == ''){
		alert("采购数量不能为空！");
	}
}

function cancel(){
	$('#iosDialog1').fadeOut(200);
}

/**
 * 加入购物车
 */
function addToCart(){
	var so_qty = document.getElementById("buynumber").value;//采购数量
	Request = GetRequest(); 
	var material_name = Request['name'];//车型名称
	var material_spec = Request['spec']; //规格
	
	var colorSelect = document.getElementById("colorcolor");
	var color_desc = colorSelect.options[colorSelect.selectedIndex].value;//颜色描述

	var stand_price = document.getElementById("price").innerHTML;//标准售价	
	var data = "so_qty="+so_qty+"&material_name="+material_name+"&material_spec="+material_spec+"&color_desc="+color_desc+"&stand_price="+stand_price;
	var url = baseurl + "/order/insertToCart";     
	sendRequest("post",url, data ,insertToCartResult);	
}

function insertToCartResult(result){
	$('#iosDialog1').fadeOut(200);
	$('#toast').fadeIn(100);
     setTimeout(function () {
    	 $('#toast').fadeOut(100);
     }, 2000);
	window.location.reload();
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
//		$("input[name='currentUser']").val(data.name);
		var accountType = data.type;//账号类型
	    if(accountType != '1'){//经销商
	    	alert("请重新用经销商账号登录");	    	
			window.location.href = baseurl + "/views/login/personalInfoHome.html";
	    	
		}else {
			$("#sellerAccount").hide();
		}		
	}
	return false;
}

/*function comeback(){
	Request = GetRequest(); 
	var name = Request['name'];
	var spec = Request['spec']; 
	var sort = Request['sort'];

	window.location.href = "searchProduct.html?name="+name+"&spec="+spec +"&sort="+ sort;
}*/
