$(function(){  
    var url1 = window.location.href;  
    //ajax注入权限验证  
    $.ajax({  
    	type: 'POST',
    	//url:'http://localhost:8080/ElectricBicycleWechat/GetSignature',
    	url:'http://supplywechat.tunnel.qydev.com/ElectricBicycleWechat/GetSignature',
    	//url:'http://supplierwechat.free.ngrok.cc/ElectricBicycleWechat/GetSignature',
    	dataType: 'json',  
        data: {"url" : url1},  
        
        complete: function(XMLHttpRequest, textStatus){  
              
        },  
        error: function(XMLHttpRequest, textStatus, errorThrown){  
            alert("发生错误："+errorThrown);  
        },  
        success: function(res){  
            var appId = res.appId;  
            var noncestr = res.noncestr;  
            var jsapi_ticket = res.jsapi_ticket;  
            var timestamp = res.timestamp;  
            var signature = res.signature;  
            wx.config({  
                debug: false, //开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。  
                appId: appId, //必填，公众号的唯一标识  
                timestamp: timestamp, // 必填，生成签名的时间戳  
                nonceStr: noncestr, //必填，生成签名的随机串  
                signature: signature,// 必填，签名，见附录1  
                jsApiList: ['scanQRCode','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ',  
                            'onMenuShareWeibo','onMenuShareQZone','chooseImage',  
                            'uploadImage','downloadImage','startRecord','stopRecord',  
                            'onVoiceRecordEnd','playVoice','pauseVoice','stopVoice',  
                            'translateVoice','openLocation','getLocation','hideOptionMenu',  
                            'showOptionMenu','closeWindow','hideMenuItems','showMenuItems',  
                            'showAllNonBaseMenuItem','hideAllNonBaseMenuItem'] //必填，需要使用的JS接口列表，所有JS接口列表 见附录2  
            });  
           
            wx.ready(function(){
                // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
            });
            wx.error(function(res){
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
            });
        }  
    });  
  
});  