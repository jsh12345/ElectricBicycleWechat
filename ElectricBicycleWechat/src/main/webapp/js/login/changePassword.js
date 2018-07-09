/**
 * 页面加载函数
 */
$(function(){
	//获取当前登录用户信息
	getCurrentUser();
});
	
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
	if(data.currentAccount==null||data.currentAccount==''){
		window.location.href = baseurl + "/views/login/login.html";
	}else{
		$("#loginId").val(data.loginId);//赋值给页面上的文本框
		$("#oldpassword").val(data.password);
	}
}

/**
 * 判断旧密码输入是否正确
 */
$("#oldPassword").blur(function(){
	var oldpassword = $("#oldpassword").val();
	var oldPassword = $("#oldPassword").val()
	if(oldPassword!=oldpassword){
		$('#msgContent').text("输入的旧密码不正确！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
});

/**
 * 判断新密码和旧密码是否相同
 */
$("#newPassword").blur(function(){
	var newPassword = $("#newPassword").val();
	var oldpassword = $("#oldpassword").val();
	if(newPassword==oldpassword){
		$("#msgContent").text("新旧密码不能相同！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
});

/**
 * 判断确认密码和新密码是否相同
 */
$("#confirmPassword").blur(function(){
	var confirmPassword = $("#confirmPassword").val();
	var newPassword = $("#newPassword").val();
	if(newPassword!=confirmPassword){
		$("#msgContent").text("两次输入的不一致！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
});

/**
 * 检查是否填写全部信息
 */
function checkIsEmpty(){
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var confirmPassword = $("#confirmPassword").val();
	if(oldPassword==''){
		isEpFlag='1';
		return false;
	}
	if(newPassword==''){
		isEpFlag='2';
		return false;
	}
	if(confirmPassword==''){
		isEpFlag='3';
		return false;
	}
}

/**
 * 登录提交
 */
function submit(){
	isEpFlag='0';
	
	//检查是否填写全部信息
	checkIsEmpty();
	if(isEpFlag=='1'){
		$("#msgContent").text("请输入旧密码！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}else if(isEpFlag=='2'){
		$("#msgContent").text("请输入新密码！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}else if(isEpFlag=='3'){
		$("#msgContent").text("请输入确认密码！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var loginId = $("#loginId").val();
	var url = baseurl + "/login/updatePassword";
	var data ="oldPassword="+oldPassword +"&password="+newPassword+"&loginId="+loginId;
	sendRequest("post", url, data, getResult);
	return false;
}

/**
 * 登陆提交请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getResult(result){
	if(result == "true"){
		$("#msgContent").text("修改成功！");
		$('#iosDialog2').fadeIn(200);
		$('#iosDialog2').on('click', function(){
				closeDialog2();
	            window.location.href = baseurl + "/views/login/login.html";
	    });
		
		return false;
	}else{
		$("#msgContent").text("修改失败！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
}

/**
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}