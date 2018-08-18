//URL中取参，防止中文乱码
function GetRequest() { 
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
			theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]); 
		} 
	} 
	return theRequest; 
}

$(function(){
	Request = GetRequest(); 
	var comp_id = Request['comp_id'];	
	var bill_no = Request['bill_no'];
	var data = "comp_id=" + comp_id + "&bill_no=" + bill_no;
	var url = baseurl +"/order/getOrderDetail";
	sendRequest("post",url, data ,getDetailResult);	
});

function getDetailResult(result){
	$("#loadingToast").fadeOut(100);
	var data = eval("(" + result + ")");
	if(data != ''){
		var orderList = $("#detailList");
		orderList.html("");
		for(var i=0 ; i < data.length ; i++){
			var m = data[i].so_qty;
			var html = "<div class='find-group1'>" +
					"<div class='group1-bd clearfix'>" +
					"<div class='pull-left bd-l'>" +
					"<div class='bd-img'><img src='"+data[i].photo_name+"' alt=''></div></div> " +
					"<div class='pull-right bd-r'><h4 class='bd-title'>"+data[i].material_name+"</h4>" +
							"<span class='bd-title'>"+data[i].material_spec+"</span>" +
					"<span class='bd-intro'>"+data[i].color_desc+"</span>" +
					"<span class='bd-intro'>"+data[i].so_qty+"</span>" +
					"<p class='bd-price' style='margin-top:10px;'>￥"+data[i].stand_price+"</p>" +
					"</div></div></div>";
		    orderList.append(html);
		}
	}	
	
}