package org.electricbicyclewechat.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.LoginAccount;


public class CheckIfInitialServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CheckIfInitialServlet.class);

	@Override
	public void init() throws ServletException {
		 
	 }
	/**
	 * 验证用户是否具有期初入库的权限
	 * @param request
	 * @param model
	 * @param sellerLoginParam
	 * @return
	 * @throws Exception
	 */
	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	
		response.setCharacterEncoding("utf-8");		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		HttpSession session=request.getSession();
		String name = (String) session.getAttribute("name");
		LoginAccount loginAccount = (LoginAccount) session.getAttribute("CurrentAccount");
		if(loginAccount==null || name==null){
			response.sendRedirect("http://zjyw.tunnel.qydev.com/ElectricBicycleWechat/views/login/login.html");
		}else{
			if(!"1".equals(loginAccount.getType())){//非经销商登录
				String info = "1";
				response.sendRedirect("http://zjyw.tunnel.qydev.com/ElectricBicycleWechat/views/login/login.html?info="+info);
			}else{
				response.sendRedirect("http://zjyw.tunnel.qydev.com/ElectricBicycleWechat/views/initialAccount/initialAccount.html");
			}
		}
	 }
	  
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException {
		 doPost(request,response);
	  
	 }

}
