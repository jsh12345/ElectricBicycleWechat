$(function(){
	var data="";
	var url = baseurl + "/order/getSort";     
	sendRequest("post",url, data ,getSortResult);	
});

function getSortResult(result){
	 
	if(result.toString() == "\[\]"){
		alert("系统未查询到本条记录，请重新查询！！");
	}
	else{
		//解析json
		result = eval("("+ result +")");
		var dataLength = result.length;		
		var sel = document.getElementById("sort");		
		sel.options.add(new Option("请选择",""));
		for (var i = 0; i < dataLength; i++)
	    {
	        sel.options.add(new Option(result[i],result[i]));
	    }
	}
}
/**
 * 根据大类获得车名
 */
function getName(){	
	
	var sortSelect = document.getElementById("sort");
	var sort = sortSelect.options[sortSelect.selectedIndex].value;
	var data="sort="+sort;
	var url =  baseurl + "/order/getMaterialName"; 
	sendRequest("post",url,data,getNameResult);
}

function getNameResult(result){   
	$("#materialName").find("option").remove();
    $("#productList").html("");
	if(result.toString() == "\[\]"){
		alert("系统未查询到本条记录，请重新查询！！");
	}
	else{
		//解析json
		result = eval("("+ result +")");
		var dataLength = result.length;		
		var sel = document.getElementById("materialName");		
		sel.options.add(new Option("请选择",""));
		for (var i = 0; i < dataLength; i++)
	    {
	        sel.options.add(new Option(result[i],result[i]));
	    }
	}
}

function getListByName(){
	var nameSelect = document.getElementById("materialName");
	var name = nameSelect.options[nameSelect.selectedIndex].value;
	var data = "material_name="+ name;
	var url = baseurl + "/order/getListByName";
	sendRequest("post" , url , data , getListByNameResult);
}

function getListByNameResult(result){

	if(result.toString() == "\[\]"){
		alert("系统未查询到本条记录，请重新查询！！");
	}
	else{
		//解析json
		result = eval("("+ result +")");
		var productList = $("#productList");
		productList.html("");
		if(result != ''){
		 for(var i=0 ; i < result.length ; i++){		 
			var html = "<a href=\"javascript:getProductDetail('"+result[i].spec+"','"+result[i].name+"')\">" + 
				    "<div class='find-group1' >" +
					"<div class='group1-bd clearfix'>" +
					"<div class='pull-left bd-l'>" +
					"<div class='bd-img'><img src='"+result[i].photo+"' alt=''></div>" +
					"</div>" +
					"<div class='pull-right bd-r'>" +
					"<h4 class='bd-title'>"+result[i].name+"</h4>" +
					"<span class='bd-intro'>"+result[i].spec+"</span>" +
					"<p class='bd-price' style='margin-top: 5px;'>"+result[i].price+"</p>" +
					"</div></div></div></a><hr />"
			productList.append(html);
		 }
	   }
	}
}

function getSpec(){
	var nameSelect = document.getElementById("materialName");
	var name = nameSelect.options[nameSelect.selectedIndex].value;
	var data="name="+name;
	var url =  baseurl + "/order/getSpec"; 
	sendRequest("post",url,data,getSpecResult);
}

function getSpecResult(result){
	$("#spec").find("option").remove();
	if(result.toString() == "\[\]"){
		alert("系统未查询到本条记录，请重新查询！！");
	}
	else{
		//解析json
		result = eval("("+ result +")");
		
		var dataLength = result.length;		
		var sel = document.getElementById("spec");		
		sel.options.add(new Option("请选择",""));
		for (var i = 0; i < dataLength; i++)
	    {
	        sel.options.add(new Option(result[i],result[i]));
	    }
	}
}
function getColor(){
	
	var nameSelect = document.getElementById("materialName");
	var name = nameSelect.options[nameSelect.selectedIndex].value;
	
	var specSelect = document.getElementById("spec");
	var spec = specSelect.options[specSelect.selectedIndex].value;
	var data = "name=" + name + "&spec=" + spec;
	var url = baseurl + "/order/getProductColor";     
	sendRequest("post",url, data ,getColorResult);	
}

function getColorResult(result){
	$("#color").find("option").remove();
	if(result.toString() == "\[\]"){
		alert("系统未查询到本条记录，请重新查询！！");
	}
	else{
		//解析json
		result = eval("("+ result +")");
		
		var dataLength = result.colorList.length;	
		var sel = document.getElementById("color");		
		sel.options.add(new Option("请选择",""));
		for (var i = 0; i < dataLength; i++)
	    {
	        sel.options.add(new Option(result.colorList[i],result.colorList[i]));
	    }
	}
}

function getQty(){
	var nameSelect = document.getElementById("materialName");
	var name = nameSelect.options[nameSelect.selectedIndex].value;	
	var specSelect = document.getElementById("spec");
	var spec = specSelect.options[specSelect.selectedIndex].value;
	var colorSelect = document.getElementById("color");
	var color = colorSelect.options[colorSelect.selectedIndex].value; 	
	var data = "name=" + name + "&spec=" + spec + "&color=" + color;
	var url = baseurl + "/order/getProductPhoto";     
	sendRequest("post",url, data ,getQtyResult);	
}
function getQtyResult(result){
	document.getElementById("stock_qty").value = "";
	var result = eval("(" + result + ")");
	var s = result.qty;
	document.getElementById("stock_qty").value = s.substring(0,s.indexOf("."));
}
/*function jumpToUrl(){
	
	var nameSelect = document.getElementById("materialName");
	var name = nameSelect.options[nameSelect.selectedIndex].value;
	
	var specSelect = document.getElementById("spec");
	var spec = specSelect.options[specSelect.selectedIndex].value;
	
	window.location.href="addToChart.html?spec="+spec+"&name="+name;
}*/

function getProductDetail(spec,name){
	
	window.location.href="addToChart.html?spec="+spec+"&name="+name;
}