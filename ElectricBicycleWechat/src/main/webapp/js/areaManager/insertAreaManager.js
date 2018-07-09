/**
 * 创建区域经理信息
 */
//var url = baseurl + "/insertAreaManager/insertAreaManager";

/**
 * 区域经理信息提交
 */
function submitForm() {
	//校验是否为空
	var managerCode = $("input[name=managerCode]").val();
	if(managerCode==''){
		$("#msgContent").text("请输入区域经理代码!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var managerName = $("input[name=managerName]").val();
	if(managerName==''){
		$("#msgContent").text("请输入区域经理姓名!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var managerMobile = $("input[name=managerMobile]").val();
	if(managerMobile==''){
		$("#msgContent").text("请输入区域经理手机号码!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var mangaerWX = $("input[name=mangaerWX]").val();
	if(mangaerWX==''){
		$("#msgContent").text("请输入区域经理微信号!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var managerAccount = $("input[name=managerAccount]").val();
	if(managerAccount==''){
		$("#msgContent").text("请输入区域经理登陆账号!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var managerPassword = $("input[name=managerPassword]").val();
	if(managerPassword==''){
		$("#msgContent").text("请输入区域经理登陆密码!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var areaCode = $("input[name=areaCode]").val();
	if(areaCode==''){
		$("#msgContent").text("请输入区域经理负责的区域代码!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var data = $("#insertAreaManagerForm").serialize();
	var url = baseurl + "/insertAreaManager/insertAreaManager";
	sendRequest("post", url, data, getResult);
	return false;
};

/**
 * 提交区域经理信息后,请求的返回结果
 * 
 * @param result
 * @returns {Boolean}
 */
function getResult(result) {
	$("input[type='submit']").attr("disabled","disabled");	
	if (result == "true") {
		$("#msgContent").text("区域经理信息创建成功！");
	} else {
		$("#msgContent").text("区域经理信息创建失败！");
	}
	$('#iosDialog2').fadeIn(200);
}

/**
 * 关闭弹出框
 */
function closeDialog2() {
	$('#iosDialog2').fadeOut(200);
}

