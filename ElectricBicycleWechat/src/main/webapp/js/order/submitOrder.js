$(function(){
	Request = GetRequest(); 
	name = Request['name'];
	spec = Request['spec']; 
	var data = "name=" + name + "&spec=" + spec;
	var url = baseurl + "/order/getProductColor";     
	sendRequest("post",url, data ,getColorResult);	
});

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

function getColorResult(result){
	if(result.toString() == "\[\]"){
		alert("系统未查询到本条记录，请重新查询！！");
	}
	else{
		//解析json
		result = eval("("+ result +")");
		
		var dataLength = result.length;		
		var sel = document.getElementById("color");		
		sel.options.add(new Option("请选择",""));
		for (var i = 0; i < dataLength; i++)
	    {
	        sel.options.add(new Option(result[i],result[i]));
	    }
	}
}

function getPhoto(){
	Request = GetRequest(); 
	name = Request['name'];
	spec = Request['spec']; 
	var colorSelect = document.getElementById("color");
	var color = colorSelect.options[colorSelect.selectedIndex].value; 	
	var data = "name=" + name + "&spec=" + spec + "&color=" + color;
	
	var url = baseurl + "/order/getProductPhoto";     
	sendRequest("post",url, data ,getPhotoResult);	
}
function getPhotoResult(result){
	$uploaderFiles = $("#files");
	$uploaderFiles.append('<img class="weui-uploader__file" src="' + result + '" alt=""/>');
}

