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
	/*Request = GetRequest(); 
	var name = Request['name'];
	var spec = Request['spec']; 
	var sort = Request['sort'];
    if(name == null){*/
    	var data="";
    	var url = baseurl + "/order/getSort";     
    	sendRequest("post",url, data ,getSortResult);
   /* }else{
    	var sel = document.getElementById("sort");		
    	sel.options.add(new Option(sort,sort));
    	
    	var sel1 = document.getElementById("materialName");		
    	sel1.options.add(new Option(name,name));
    	$("#loadingToast").fadeIn(100);
    	var data = "material_name="+ name;
    	var url = baseurl + "/order/getListByName";
    	sendRequest("post" , url , data , getListByNameResult);
    }*/
    		
});

function getSortResult(result){
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
		var dataLength = result.length;		
		var sel = document.getElementById("sort");		
		sel.options.add(new Option("请选择电动车大类",""));
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
	$("#loadingToast").fadeIn(100);
	var sortSelect = document.getElementById("sort");
	var sort = sortSelect.options[sortSelect.selectedIndex].value;
	var data="sort="+sort;
	var url =  baseurl + "/order/getMaterialName"; 
	
	sendRequest("post",url,data,getNameResult);
	
}

function getNameResult(result){   
	$("#loadingToast").fadeOut(100);
	$("#materialName").find("option").remove();
    $("#productList").html("");
    
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
		var dataLength = result.length;		
		var sel = document.getElementById("materialName");		
		sel.options.add(new Option("请选择电动车名称",""));
		for (var i = 0; i < dataLength; i++)
	    {
	        sel.options.add(new Option(result[i],result[i]));
	    }
	}
}

function getListByName(){
	$("#loadingToast").fadeIn(100);
	var nameSelect = document.getElementById("materialName");
	var name = nameSelect.options[nameSelect.selectedIndex].value;
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
		/*$("#toast").fadeIn(100);
		setTimeout(function () {
			$("#toast").fadeOut(100);
	       }, 2000);*/
	}
	else{
		//解析json
		result = eval("("+ result +")");
		
		var dataLength = result.length;		
		var sel = document.getElementById("spec");		
		sel.options.add(new Option("请选择电动车规格",""));
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
		
		var dataLength = result.length;	
		var sel = document.getElementById("color");		
		sel.options.add(new Option("请选择电动车颜色",""));
		for (var i = 0; i < dataLength; i++)
	    {
	        sel.options.add(new Option(result[i].color,result[i].color));
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
	$("#loadingToast").fadeIn(100);
	var sortSelect = document.getElementById("sort");
	var sort = sortSelect.options[sortSelect.selectedIndex].value;
	
	window.location.href="addToChart.html?spec="+spec+"&name="+name+"&sort="+sort;
}
