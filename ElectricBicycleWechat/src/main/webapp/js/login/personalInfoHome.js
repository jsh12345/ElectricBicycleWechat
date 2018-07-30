
$(function(){
	var data = "";
	var url = baseurl + "/login/getCurrentUser";
	sendRequest("post", url, data, getResult);
	return false;
});

function getResult(result){
	data = eval("(" + result + ")");	
	document.getElementById("loginName").innerHTML = data.name;
	document.getElementById("accountKeyid").innerHTML = data.loginId;
	document.getElementById("loginPassword").innerHTML = data.password;   
	document.getElementById("accountType").innerHTML = data.type;
}