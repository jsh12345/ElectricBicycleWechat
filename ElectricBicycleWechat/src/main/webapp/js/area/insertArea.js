/**
 * 创建区域信息
 */
//	var url = baseurl + "/insertArea/insertArea";

/**
 * 区域信息提交
 */
function submitForm(){
	//校验是否为空
	var areaCode = $("input[name=areaCode]").val();
	if(areaCode==''){
		$("#msgContent").text("请输入区域代码!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var areaName = $("input[name=areaName]").val();
	if(areaName==''){
		$("#msgContent").text("请输入区域名称!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var areaManagerCode = $("input[name=areaManagerCode]").val();
	if(areaManagerCode==''){
		$("#msgContent").text("请输入区域经理代码!");
		$('#iosDialog2').fadeIn(200);
		return false;
	}
	var data = $("#insertAreaForm").serialize();//表单内容转换为字符串
	var url = baseurl + "/insertArea/insertArea";
	sendRequest("post", url, data, getResult);
	return false;
};

/**
 * 提交区域信息后,请求的返回结果
 * @param result
 * @returns {Boolean}
 */
function getResult(result){
	$("input[type='submit']").attr("disabled","disabled");
	if(result == "true"){
		$("#msgContent").text("区域信息创建成功！");
	}else{
		$("#msgContent").text("区域信息创建失败！");
	}
	$('#iosDialog2').fadeIn(200);
}

/**
 * 关闭弹出框
 */
function closeDialog2(){
	$('#iosDialog2').fadeOut(200);
}