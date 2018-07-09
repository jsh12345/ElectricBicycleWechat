package org.electricbicyclewechat.configuration.util;

import org.electricbicyclewechat.configuration.pojo.Ticket;

public class TicketThread implements Runnable{
	
	 public static String appId = "wxf1172d4291aafa24";
	 public static String appSecret= "e945f6f799c771f2133054bf677f97e2";
	 public static Ticket ticket = null;
	  
	 public void run(){
		 while (true){
			  try{
				  ticket = BaseUtil.getTicket(TokenThread.accessToken.getAccessToken());
			  if(null!=ticket){
			   Thread.sleep(7000 * 1000); //获取到ticket 休眠7000秒
			  
			  }else{
			   Thread.sleep(1000*3); //获取的ticket为空 休眠3秒
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
