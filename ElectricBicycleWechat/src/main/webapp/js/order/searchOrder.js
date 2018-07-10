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

function getSpec(){
	var nameSelect = document.getElementById("materialName");
	var name = nameSelect.options[nameSelect.selectedIndex].value;
	var data="name="+name;
	var url =  baseurl + "/order/getSpec"; 
	sendRequest("post",url,data,getSpecResult);
}

function getSpecResult(result){
	
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

function jumpToUrl(){
	
	var nameSelect = document.getElementById("materialName");
	var name = nameSelect.options[nameSelect.selectedIndex].value;
	
	var specSelect = document.getElementById("spec");
	var spec = specSelect.options[specSelect.selectedIndex].value;
	
	window.location.href="submitOrder.html?spec="+spec+"&name="+name;

}

