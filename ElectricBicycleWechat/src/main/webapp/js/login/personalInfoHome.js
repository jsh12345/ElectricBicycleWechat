$(function(){
	var data = "";
	var url = baseurl + "/login/getCurrentUser";
	sendRequest("post", url, data, getResult);
	return false;
});

function getResult(result){
	data = eval("(" + result + ")");
	if(data.name=='' || data.name==null || data.currentAccount==null){
		document.getElementById("panel").setAttribute("style", "display:none");
		var $toast = $('#toast');	       
        $toast.fadeIn(100);
        setTimeout(function () {
            $toast.fadeOut(100);
        }, 2000);
//	    window.location.href= baseurl + "/login/login.html";        
	}     		
	else{		
		document.getElementById("loginName").innerHTML = data.name;
		document.getElementById("accountKeyid").innerHTML = data.loginId;
		document.getElementById("loginPassword").innerHTML = data.password;   
		if(data.type == '1'){
			type = "经销商";
		}else{
			type = "厂商";
		}
		document.getElementById("accountType").innerHTML = type;
	}
	
}