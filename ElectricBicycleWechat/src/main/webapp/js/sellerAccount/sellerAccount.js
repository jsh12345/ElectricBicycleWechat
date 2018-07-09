/**
 * 创建经销商账号
 */
//var url = baseurl + "/sellerAccount/insertSellerAccount";

/**
 * 创建经销商账号
 */
/*function submitForm(){
	//校验是否为空
	var sellerCode = $("input[name=sellerCode]").val();
	if(sellerCode==''){
		$("#msgContent").text("请输入经销商代码!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var accountName = $("input[name=accountName]").val();
	if(accountName==''){
		$("#msgContent").text("请输入登录账号名称！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var password = $("input[name=password]").val();
	if(password==''){
		$("#msgContent").text("请输入登录密码！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	
	var data = $("#sellerAccountForm").serialize();
	var url = baseurl + "/sellerAccount/insertSellerAccount";
	sendRequest("post", url, data, getResult);
	return false;
};*/
/**
 * 创建经销商账号提交请求的返回结果
 * @param result
 * @returns {Boolean}
 */
/*function getResult(result){
	$("input[type='submit']").attr("disabled","disabled");
	if(result == "true"){
		$("#msgContent").text("创建经销商账号成功！");
	}else{
		$("#msgContent").text("创建经销商账号失败！");
	}
	$('#iosDialog2').fadeIn(200);
}*/

/**
 * 关闭弹出框
 */
/*function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}*/