package org.electricbicyclewechat.configuration.util;

import org.electricbicyclewechat.configuration.pojo.AccessToken;
import org.electricbicyclewechat.configuration.pojo.Ticket;

public class TokenThread implements Runnable{
	
//	 public static String appId = "wx6c5e2b145ca4653b";
//	 public static String appSecret= "5bb27830661395610d14614d658b0faf";
	
	 public static String appId = "wxa3c839c1ca76bcc0";
	 public static String appSecret= "418a0710c843e346eee9201950373051";
	 
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
