$(function(){
	$("#loadingToast").fadeIn(100);
	//特价促销款
	var data = "";
	var url = baseurl + "/order/getProductBySort";
	sendRequest("post",url , data , getBySortResult);	
});

function getBySortResult(result){
	$("#loadingToast").fadeOut(100);
	if(result.toString() == "\[\]"){
		alert("系统未查询到本条记录，请重新查询！！");
		/*$("#toast").fadeIn(100);
		setTimeout(function () {
			$("#toast").fadeOut(100);
	       }, 2000);*/
	}
	else{
		//解析json
		result = eval("("+ result +")");
		var hotProduct = $("#hotProduct");
		hotProduct.html("");
		var headHtml = "<div class='wares-title'>特价促销款</div>";
		hotProduct.append(headHtml);
		if(result != ''){
		 for(var i=0 ; i < result.length ; i++){	
			var pprice = result[i].price; 
			var html = "<a href='javascript:getProductDetail(\""+result[i].spec+"\",\""+result[i].name+"\")'>" +
				    "<div class='find-group1' >" +
					"<div class='group1-bd clearfix'>" +
					"<div class='pull-left bd-l'>" +
					"<div class='bd-img'><img src='"+result[i].photo+"' alt=''></div>" +
					"</div>" +
					"<div class='pull-right bd-r'>" +
					"<h4 class='bd-title'>"+result[i].name+"</h4>" +
					"<span class='bd-intro'>"+result[i].spec+"</span>" +
					"<p class='bd-price' style='margin-top: 5px;'>"+pprice.substring(0,pprice.indexOf(".")+3)+"</p>" +
					"</div></div></div></a><hr />"
					hotProduct.append(html);
		 }
	   }
	}
}

function getListName(){
	var simpleName = document.getElementById("simpleName").value;
	var data="simpleName="+simpleName;
	var url = baseurl + "/order/getListName";
	sendRequest("post" , url , data ,getListResult);
}

function getListResult(result){
	
	if(result.toString() == "\[\]"){
		/*alert("无该车型");*/
	}else{
		document.getElementById("index").setAttribute("style", "display:none");
		document.getElementById("searchResult").removeAttribute("style");
		var nameList = $("#nameList");
		nameList.html("");
	    var data = eval("("+result+")");
	    for(var i=0 ; i < data.length ; i++){	
	    	var html = "<a href=javascript:getListByName('"+data[i]+"')>" +
	    			   "<p style='color:#000000'>"+data[i]+"</p>" +
	    			   "<hr></a>";
	    	nameList.append(html);
	    }
	}
}

function getListByName(name){
	document.getElementById("simpleName").value = name;
	$("#loadingToast").fadeIn(100);
	var data = "material_name="+ name;
	var url = baseurl + "/order/getListByName";
	sendRequest("post" , url , data , getListByNameResult);
}
function getListByNameResult(result){
	$("#loadingToast").fadeOut(100);
	if(result.toString() == "\[\]"){
		alert("系统未查询到本条记录，请重新查询！！");
		/*$("#toast").fadeIn(100);
		setTimeout(function () {
			$("#toast").fadeOut(100);
	       }, 2000);*/
	}
	else{		
		document.getElementById("searchResult").setAttribute("style", "display:none");
		document.getElementById("index").removeAttribute("style");
		document.getElementById("hotProduct").setAttribute("style", "display:none");
		document.getElementById("productList").removeAttribute("style"); 
		//解析json
		result = eval("("+ result +")");
		var productList = $("#productList");
		productList.html("");
		if(result != ''){
		 for(var i=0 ; i < result.length ; i++){	
			var pprice = result[i].price; 
			var html = "<a href='javascript:getProductDetail(\""+result[i].spec+"\",\""+result[i].name+"\")'>" + 
				    "<div class='find-group1' >" +
					"<div class='group1-bd clearfix'>" +
					"<div class='pull-left bd-l'>" +
					"<div class='bd-img'><img src='"+result[i].photo+"' alt=''></div>" +
					"</div>" +
					"<div class='pull-right bd-r'>" +
					"<h4 class='bd-title'>"+result[i].name+"</h4>" +
					"<span class='bd-intro'>"+result[i].spec+"</span>" +
					"<p class='bd-price' style='margin-top: 5px;'>"+pprice.substring(0,pprice.indexOf(".")+3)+"</p>" +
					"</div></div></div></a><hr />"
			productList.append(html);
		 }
	   }
	}
}

function getProductDetail(spec,name){
	$("#loadingToast").fadeIn(100);	
	window.location.href="addToChart.html?spec="+spec+"&name="+name;
}