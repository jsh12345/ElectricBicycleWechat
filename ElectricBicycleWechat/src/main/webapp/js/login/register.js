var url = baseurl + "/servlet/LoginServlet";
var openid;

$(function(){
	var locat=window.location.search;
	var locatopenid = locat.indexOf("&");
	openid = locat.substr(1);
});

/**
 * 选择不同的单选框，显示不同的界面
 */
function changetype(thisnode)
{
	var value = thisnode.options[thisnode.selectedIndex].value;
			if(value == 1)
				{
					var companyregister = document.getElementById("companyregister");
					companyregister.removeAttribute("style");
					var personregister = document.getElementById("personregister");
					personregister.setAttribute("style", "display:none");
				}
			else
				{
					var personregister = document.getElementById("personregister");
					personregister.removeAttribute("style");
					var companyregister = document.getElementById("companyregister");
					companyregister.setAttribute("style", "display:none");
				}
		
}

/**
 * 点击注册按钮时提交
 */
function register(){
			var accounttypeSelect = document.getElementById("select2");
			var accounttype = accounttypeSelect.options[accounttypeSelect.selectedIndex].value;
					if(accounttype == 0)
						{
							var username = document.getElementById("username").value;
							var password = document.getElementById("password").value;
							var name = document.getElementById("name").value;
							var contactPhone = document.getElementById("contactPhone").value;
						
							var data = "type=personregister&username=" + username + "&password=" + password + "&name=" + name + "&contactPhone=" + contactPhone + "&openid=" + openid;
							sendRequest("post", url, data, registerResult);
							return false;
						}
					else if(accounttype == 1)
						{
							var comusername = document.getElementById("comusername").value;
							var compassword = document.getElementById("compassword").value;
							var comname = document.getElementById("comname").value;
							var registertime = document.getElementById("registertime").value;
							var contactname = document.getElementById("contactname").value;
							var comcontactPhone = document.getElementById("comcontactPhone").value;
							var productrange = document.getElementById("productrange").value;
							var location = document.getElementById("location").value;
						
							var data = "type=companyregister&comusername=" + comusername + "&compassword=" + compassword + "&comname=" + comname + "&registertime=" + registertime +  "&contactname=" + contactname + "&comcontactPhone=" + comcontactPhone +
							"&productrange=" + productrange + "&location=" + location + "&openid=" + openid;
							sendRequest("post", url, data, registerComResult);
							return false;
						}
}
/**
 * 注册个人账号返回结果
 * @param result
 */
function registerResult(result){
	if(result == "true")
		{
			alert("个人账号，注册成功！！");
			window.location.href = "login.html?" + openid;
		}
	else
		{
			alert("注册失败");
		}
	
}
/**
 * 注册公司账号返回结果
 * @param result
 */
function registerComResult(result){
	if(result == "true")
		{
			alert("公司账号，注册成功！！");
			window.location.href = "login.html";
		}
	else
		{
			alert("注册失败");
		}
	
}