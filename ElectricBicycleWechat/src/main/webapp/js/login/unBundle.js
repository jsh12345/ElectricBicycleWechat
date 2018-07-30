//确认解绑
function confirm(){
	document.getElementById("dialogs").removeAttribute("style");
}

//解绑
function unbundle(){
	var data = "";
	var url = baseurl + "/login/unBundle";
	sendRequest("post", url, data, unbundleResult);
}

function unbundleResult(result){
	
	if(result){
		document.getElementById("operateResult").removeAttribute("style");
        document.getElementById("resultText").innerHTML = "解绑成功";
        document.getElementById("otherOperation").innerHTML = "重新绑定";
        document.getElementById("otherOperation").href = baseurl + "/views/login/login.html";
        document.getElementById("iosDialog1").setAttribute("style", "display:none");   
        document.getElementById("panel").setAttribute("style", "display:none");  
	}
}