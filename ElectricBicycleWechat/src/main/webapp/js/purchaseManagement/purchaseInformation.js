/**
 * 记录销售信息
 */
//var url = baseurl + "/purchaseManagement/insertpurchaseInformation";
$(function(){
	
	//获取当前登录用户信息
	getCurrentUser();
	
	//查询按钮
	$("#search").click(function(){
		findBicycle();
	});
	
	//初始化购买日期
	initDate();
	
	//微信扫一扫
	$("#scanQRCode").click(function(){
		scanQR();
	});
	
	/*$("input:radio[name='buyerSex']").change(function (){ 
		var sex = $("input:radio[name='buyerSex'][checked='checked']").attr("id");
		alert(sex);
	});*/
	
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
		//如果不拥有该权限
		/*if(data.currentAccount.type!='1'){
			//$("#msgContent").text("您没有该权限,请登录经销商账号！");
			//$('#iosDialog2').fadeIn(200);
			alert("您没有该权限,请登录经销商账号！");
			window.location.href = baseurl + "/views/login/login.html";
		}else{
			$("input[name='currentUser']").val(data.name);
		}*/
		$("input[name='currentUser']").val(data.name);
	}
	return false;
}

/**
 * 根据车架号查询电瓶车信息
 */
function findBicycle(){
	//校验是否为空
	var frameCodeInput = $("input[name=frameCode]").val();
	var barCodeInput = $("input[name=barCode]").val();
	if(frameCodeInput=='' && barCodeInput==''){
		$("#msgContent").text("请填写至少一个搜索条件！");
		$('#iosDialog2').fadeIn(200);
		return false;
	}       
	//隐藏盘点按钮
	$("#inventory").css("display","none");
	var data = "frameCode="+$("input[name=frameCode]").val()+"&barCode="+$("input[name=barCode]").val();
	var url = baseurl + "/purchaseManagement/findBicycle";
	sendRequest("post", url, data, getResult);
	return false;
};
/**
 * 根据车架号查询电瓶车信息的返回结果
 * @param result
 * @returns {Boolean}
 */
function getResult(result){
	if(result=="false"){
		$("#msgContent").text("查询失败！");
		$('#iosDialog2').fadeIn(200);
	}else if(result=="inventory"){//盘点功能
		$("#inventory").css("display","inline-block");
		$("input[name=frameCode]").val("");
		$("input[name=materialName]").val("");
		$("input[name=maiterialSpec]").val("");
		$("input[name=materialType]").val("");
		$("input[name=colorDesc]").val("");
		$("input[name=makeDate]").val("");
		$("#msgContent").text("库存中没有该电瓶车信息，可对库存进行盘点！");
		$('#iosDialog2').fadeIn(200);
	}else{
		var data = eval("(" + result + ")");
		$("input[name=frameCodeForShow]").val(data.frameCode);
		$("input[name=materialName]").val(data.materialName);
		$("input[name=maiterialSpec]").val(data.maiterialSpec);
		$("input[name=materialType]").val(data.materialType);
		$("input[name=colorDesc]").val(data.colorDesc);
		$("input[name=makeDate]").val(data.makeDate);
		//$("#msgContent").text("查询成功！");
		//$('#iosDialog2').fadeIn(200);
	}
	return false;
}


/**
 * 系统保存销售信息
 */
function submitForm(){
	var data = $("#purchaseInformation").serialize();
	var sex = $("input:radio[name='buyerSex'][checked='checked']").attr("id");
	if(sex =='man'){
		sex ='M';
	}else if(sex == 'woman'){
		sex ='F';
	}
	//用于查询车辆信息的条件
	data += "&frameCode="+$("input[name='frameCodeForShow']").val()+"&sex="+sex;
	var url = baseurl + "/purchaseManagement/saveSellInfo";
	sendRequest("post", url, data, getPurchaseInfoResult);
	return false;
}

/**
 * 系统保存销售信息的返回结果
 * @param result
 * @returns {Boolean}
 */
function getPurchaseInfoResult(result){
	if(result=="false"){
		$("#msgContent").text("系统保存销售信息失败！");
		$('#iosDialog2').fadeIn(200);
	}else if(result="true"){
		$("input[type='submit']").attr("disabled","disabled");
		$("#msgContent").text("系统保存销售信息成功！");
		$('#iosDialog2').fadeIn(200);
	}
	return false;
}

/**
 * 根据条码查询电瓶车信息
 */
function findBicycleByBarCode(barCode){
	//隐藏盘点按钮
	$("#inventory").css("display","none");
	var data = "barCode="+barCode;
	var url = baseurl + "/purchaseManagement/findBicycleByBarCode";
	sendRequest("post", url, data, getFindBicycleByBarCodeResult);
	return false;
};
/**
 * 根据条形码查询电瓶车信息的返回结果
 * @param result
 * @returns {Boolean}
 */
function getFindBicycleByBarCodeResult(result){
	if(result=="false"){
		$("#msgContent").text("查询失败！");
		$('#iosDialog2').fadeIn(200);
	}else if(result=="inventory"){//盘点功能
		$("#inventory").css("display","inline-block");
		$("input[name=frameCode]").val("");
		$("input[name=materialName]").val("");
		$("input[name=maiterialSpec]").val("");
		$("input[name=materialType]").val("");
		$("input[name=colorDesc]").val("");
		$("input[name=makeDate]").val("");
		$("#msgContent").text("库存中没有该电瓶车信息，可对库存进行盘点！");
		$('#iosDialog2').fadeIn(200);
	}else{
		var data = eval("(" + result + ")");
		$("input[name=frameCodeForShow]").val(data.frameCode);
		$("input[name=materialName]").val(data.materialName);
		$("input[name=maiterialSpec]").val(data.maiterialSpec);
		$("input[name=materialType]").val(data.materialType);
		$("input[name=colorDesc]").val(data.colorDesc);
		$("input[name=makeDate]").val(data.makeDate);
		//$("#msgContent").text("查询成功！");
		//$('#iosDialog2').fadeIn(200);
	}
	return false;
}



/**
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}

//加载日期
function initDate()
{
	var nowDate = new Date();
	var nowYear = nowDate.getFullYear();      //获取年份
	var nowMonth = nowDate.getMonth();         //获取月份
	var nowDay = nowDate.getDate();           //获取日期
	if (nowMonth < 9) 
	{
		nowMonth = "0" + (nowMonth + 1);     //月份小于10,则在前面加上0
	} else nowMonth = nowMonth + 1;
	if (nowDay < 10) 
	{
		nowDay = "0" + nowDay;       //日期小于10,则在前面加上0
	}
	var time =nowYear + "-" + nowMonth + "-" + nowDay;
	$("input[name='sellDate']").val(time);
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
		findBicycleByBarCode(arr[1]);
		}
	});
}
