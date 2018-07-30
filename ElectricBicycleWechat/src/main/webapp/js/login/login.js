//var url = baseurl + "/login/checkSeller";
var openid;

var isEpFlag='0';//验证信息是否填写完全
var info;
$(function(){
	
	var locat=window.location.search;
	//var locatopenid = locat.indexOf("&");
	openid = locat.substr(1);
	info = locat.substr(6);
	if(info=='1'){
		$('#msgContent').text("该权限不足，请登录经销商账号！");
		$('#iosDialog2').fadeIn(200);
	}
		
	
/**
 * 填充名称
 */
$("#loginId").blur(function(){
	$("#name").val('');
	var loginId = $("#loginId").val();
	var data='';
	if(loginId!=''){//检查是否存在该账号
		var url = baseurl + "/login/checkAccount";
		data += "loginId="+loginId;
		sendRequest("post", url, data, getcheckAccountResult);
		return false;
	}
});

});


/**
 * 检查是否填写全部信息
 */
function checkIsEmpty(){
	var loginId = $("#loginId").val();
	var password = $("#password").val();
	if(loginId==''){
		isEpFlag='1';
		return false;
	}
	if(password==''){
		isEpFlag='2';
		return false;
	}
}


/**
 * 检查账号的返回结果
 * @param result
 * @returns {Boolean}
 */
function getcheckAccountResult(result){
	if(result == 'false'){
		$('#msgContent').text("查询错误！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}else{
		var data = eval("(" + result + ")");
		var code = data.code;
		if(code=='0'){//查询成功
			$("#name").val(data.name);
			return false;
		}else if(code=='1'){
			$('#msgContent').text("查询错误！");
			$('#iosDialog2').fadeIn(200);
			return false;
		}else{
			$('#msgContent').text(data.msg);
			$('#iosDialog2').fadeIn(200);
			return false;
		}
	}
}

/**
* 链接到注册页面
*/
function linkRegister(){
	window.location.href = "register.html?" + openid;
}
/**
 * 登录提交
 */
function submit(){
	
	isEpFlag='0';
	
	//检查是否填写全部信息
	checkIsEmpty();
	if(isEpFlag=='1'){
		$("#msgContent").text("请输入账号！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}else if(isEpFlag=='2'){
		$("#msgContent").text("请输入密码！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var loginId = $("#loginId").val();
	var name = $("#name").val();
	var password = $("#password").val();
	var url = baseurl + "/login/AccountLogin";
	var data ="loginId="+loginId +"&password="+password+"&name="+name;
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
//		window.location.href = "../balanceManagement/balanceQueryMore.html";
		window.location.href = "../order/index.html"
	} else if (result == "true1"){//内勤登陆跳转
		window.location.href = "../userProperty/confirmOrder.html";
	}else{
		$("#msgContent").text("密码错误！");
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