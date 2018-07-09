/**
 * 创建经销商基本信息
 */
//var url = baseurl + "/seller/insertSeller";
/**
 * 创建经销商基本信息提交
 */
function submitForm(){
	//校验是否为空
	var sellerCode = $("input[name=sellerCode]").val();
	if(sellerCode==''){
		$("#msgContent").text("请输入经销商代码!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var sellerName = $("input[name=sellerName]").val();
	if(sellerName==''){
		$("#msgContent").text("请输入经销商名称!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var sellerShortName = $("input[name=sellerShortName]").val();
	if(sellerShortName==''){
		$("#msgContent").text("请输入经销商简称!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var sellerDeliveryAddress = $("input[name=sellerDeliveryAddress]").val();
	if(sellerDeliveryAddress==''){
		$("#msgContent").text("请输入经销商送货地址!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var contactor = $("input[name=contactor]").val();
	if(contactor==''){
		$("#msgContent").text("请输入经销商联系人姓名!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var contactorTele = $("input[name=contactorTele]").val();
	if(contactorTele==''){
		$("#msgContent").text("请输入联系人电话!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var areaCode = $("input[name=areaCode]").val();
	if(areaCode==''){
		$("#msgContent").text("请输入地区代码!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var data = $("#sellerForm").serialize();
	var url = baseurl + "/seller/insertSeller";
	sendRequest("post", url, data, getResult);
	return false;
};
/**
 * 创建经销商基本信息提交请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getResult(result){
	$("input[type='submit']").attr("disabled","disabled");
	if(result == "true"){
		$("#msgContent").text("创建经销商基本信息成功！");
	}else{
		$("#msgContent").text("创建经销商基本信息失败！");
	}
	$('#iosDialog2').fadeIn(200);
}

/**
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}