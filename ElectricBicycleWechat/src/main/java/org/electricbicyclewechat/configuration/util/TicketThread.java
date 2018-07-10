package org.electricbicyclewechat.configuration.util;

import org.electricbicyclewechat.configuration.pojo.Ticket;

public class TicketThread implements Runnable{
	
//	 public static String appId = "wx6c5e2b145ca4653b";
//	 public static String appSecret= "5bb27830661395610d14614d658b0faf";
	
	public static String appId = "wxa3c839c1ca76bcc0";
	public static String appSecret= "418a0710c843e346eee9201950373051";
	
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
