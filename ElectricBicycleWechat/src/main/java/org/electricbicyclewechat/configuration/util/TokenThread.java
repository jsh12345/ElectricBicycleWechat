package org.electricbicyclewechat.configuration.util;

import org.electricbicyclewechat.configuration.pojo.AccessToken;
import org.electricbicyclewechat.configuration.pojo.Ticket;

public class TokenThread implements Runnable{
	
	 public static String appId = "wxf1172d4291aafa24";
	 public static String appSecret= "e945f6f799c771f2133054bf677f97e2";
	 public static AccessToken accessToken = null;
	 public static Ticket ticket = null;
	  
	 public void run(){
		 while (true){
			  try{
			  accessToken = BaseUtil.getAccessToken(appId,appSecret);
			  if(null!=accessToken){
			   new Thread(new TicketThread()).start(); //启动进程
			   Thread.sleep(7000 * 1000); //获取到access_token 休眠7000秒
			  }else{
			   Thread.sleep(1000*3); //获取的access_token为空 休眠3秒
			  }
			  }catch(Exception e){
			  System.out.println("发生异常："+e.getMessage());
			  e.printStackTrace();
			  try{
			   Thread.sleep(1000*10); //发生异常休眠1秒
			  }catch (Exception e1){
			  
			  }
		  }
		 }
	 }
	  
	  

}
