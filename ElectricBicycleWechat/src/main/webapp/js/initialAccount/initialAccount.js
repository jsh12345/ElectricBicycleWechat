/**
 * 期初入账
 */
//var url = baseurl + "/initialAccount/insertAccount";
/**
 * 页面加载函数
 */
$(function(){
	
	//获取当前登录用户信息
	getCurrentUser();
	
	//微信扫一扫
	$("#scanQRCode").click(function(){
		scanQR();
	});
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
	if(data.name=='' || data.name==null || data.currentAccount==null){
		window.location.href = baseurl + "/views/login/login.html";
	}else{
		$("input[name='currentUser']").val(data.name);
		$("input[name='sellerCode']").val(data.currentAccount.accountCode);
	}
	return false;
}

/**
 * 初期入账提交
 */
function submitForm(){
	var data = $("#initialAccountForm").serialize();
	var url = baseurl + "/initialAccount/insertAccount";
	sendRequest("post", url, data, getResult);
	return false;
};
/**
 * 初期入账提交请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getResult(result){
	$("input[type='submit']").attr("disabled","disabled");
	if(result == "true"){
		$("#msgContent").text("期初入账成功！");
	}else{
		$("#msgContent").text("期初入账失败！");
	}
	$('#iosDialog2').fadeIn(200);
}

/**
 * 根据条码查询电瓶车信息
 */
function findBicycleFromPurchase(barCode){
	var data = "barCode="+barCode;
	var url = baseurl + "/initialAccount/findBicycleFromPurchase";
	sendRequest("post", url, data, getfindBicycleFromPurchaseResult);
	return false;
};

/**
 * 根据条形码查询电瓶车信息的返回结果
 * @param result
 * @returns {Boolean}
 */
function getfindBicycleFromPurchaseResult(result){
	if(result=="false"){
		$("#msgContent").text("查询失败！");
		$('#iosDialog2').fadeIn(200);
	}else if(result=="exist"){
		$("#msgContent").text("该电瓶车已经入库，不能进行期初入库！");
		$('#iosDialog2').fadeIn(200);
	}else if(result=="non-existent"){
		$("#msgContent").text("不存在该电瓶车信息！");
		$('#iosDialog2').fadeIn(200);
	}else{
		var data = eval("(" + result + ")");
		$("input[name=sellerCode]").val(data.sellerCode);
		$("input[name=barCode]").val(data.barCode);
		$("input[name=frameCode]").val(data.frameCode);
		$("input[name=motorCode]").val(data.motorCode);
		$("input[name=materialCode]").val(data.materialCode);
		$("input[name=materialName]").val(data.materialName);
		$("input[name=maiterialSpec]").val(data.maiterialSpec);
		$("input[name=materialType]").val(data.materialType);
		$("input[name=colorDesc]").val(data.colorDesc);
		$("input[name=colorCode]").val(data.colorCode);
		$("input[name=makeDate]").val(data.makeDate);
		$("input[name=purchaseDate]").val(data.purchaseDate);
		$("#msgContent").text("查询成功！");
		$('#iosDialog2').fadeIn(200);
	}
	return false;
}


/**
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}

/**
 * 微信扫一扫
 */
function scanQR(){
   wx.scanQRCode({	
		needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
		success: function (res) {
		var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		var arr = new Array();
		arr=result.split(","); //字符分割 
		findBicycleFromPurchase(arr[1]);
		}
	});
}
