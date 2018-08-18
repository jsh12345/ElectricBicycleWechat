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
function addProduct(){
	Request = GetRequest(); 
	var comp_id = Request['comp_id'];
	var bill_no = Request['bill_no']; 
	var s_n = Request['s_n'];
	
	if($('#sort').val().length == 0){
		alert("请选择大类！");
	}else{
	if($('#materialName').val().length == 0){
		alert("请选择车名！");
	}else{
		var nameSelect = document.getElementById("materialName");
		var name = nameSelect.options[nameSelect.selectedIndex].value;	
	}
	if($('#spec').val().length == 0){
		alert("请选择规格！");
//		$("#msgContent").text("请选择规格！");
	}else{
		var specSelect = document.getElementById("spec");
		var spec = specSelect.options[specSelect.selectedIndex].value;
	}
	if($('#color').val().length == 0){
		alert("请选择颜色！");
//		$("#msgContent").text("请选择颜色！");
	}else{
		var colorSelect = document.getElementById("color");
		var color = colorSelect.options[colorSelect.selectedIndex].value; 	
	}
	}
	    var so_qty = document.getElementById("so_qty").value;//采购数量
	    if(so_qty.length == 0){
	    	alert("请填写采购数量！");
	    }else{
			var data = "comp_id=" + comp_id + "&bill_no=" + bill_no + "&s_n=" + s_n + "&material_name=" + name + "&material_spec=" + spec + "&color_desc=" + color + "&so_qty=" + so_qty;;
			var url = baseurl + "/userProperty/addNewProduct";
			sendRequest("post" , url , data , addResult);
	    }
}
function addResult(result){
	if(result){
		alert("添加成功！");
		window.location.reload();
	}else{
		alert("添加失败！");
	}
}

function checkNumber(){
	var reg = /^[1-9][0-9]{0,}$/;
	var price = document.getElementById("so_qty").value;
	if(!reg.test(price)){
		alert("采购数量须为正整数！");
//		price.focus();
		document.getElementById("so_qty").value = "";
	}else if(price == ''){
		alert("采购数量不能为空！");
	}
}
/**
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}