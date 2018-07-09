package org.electricbicyclewechat.configuration.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.configuration.util.TicketThread;
import org.electricbicyclewechat.configuration.util.TokenThread;


public class AccessTokenServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AccessTokenServlet.class);

	public void init() throws ServletException {
		 
		 TokenThread.appId = getInitParameter("appid"); //获取servlet初始参数appid和appsecret
		 TokenThread.appSecret = getInitParameter("appsecret");
		 if ("".equals(TokenThread.appId) || "".equals(TokenThread.appSecret)) {
				logger.error("appid and appsecret configuration error, please check carefully.");
			} else {
				new Thread(new TokenThread()).start(); //启动进程
		}
		 
		 
	 }
	  
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException {
	  
	 }
	  
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException {
	  
	 }

}
