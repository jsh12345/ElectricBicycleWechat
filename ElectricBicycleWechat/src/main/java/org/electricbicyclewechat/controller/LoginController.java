package org.electricbicyclewechat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.LoginAccount;
import org.electricbicyclewechat.pojo.UserProperty;
import org.electricbicyclewechat.pojo.request.login.AccountLoginParam;
import org.electricbicyclewechat.service.AreaManagerService;
import org.electricbicyclewechat.service.LoginAccountService;
import org.electricbicyclewechat.service.UserPropertyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 账号登录
 * 
 * @author smile
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private AreaManagerService areaManagerService;
	@Autowired
	private LoginAccountService loginAccountService;
	@Autowired
	private UserPropertyService userPropertyService;

	/**
	 * 检查账号
	 * 
	 * @param request
	 * @param model
	 * @param sellerLoginParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/checkAccount")
	public Object checkAccount(HttpServletRequest request, Model model,
			AccountLoginParam accountLoginParam) throws Exception {
		if (accountLoginParam != null) {
			LoginAccount loginAccount = new LoginAccount();
			BeanUtils.copyProperties(accountLoginParam, loginAccount);
			Map<String, Object> map1 = loginAccountService
					.checkifAccount(loginAccount);
			if (!"0".equals(map1.get("code"))) {
				UserProperty userProperty = new UserProperty();
				BeanUtils.copyProperties(accountLoginParam, userProperty);
				Map<String, Object> map = userPropertyService
						.checkifAccount(userProperty);
				return map;
			}
			return map1;
		}
		logger.error("参数为null！");
		return "false";
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param model
	 * @param sellerLoginParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/AccountLogin")
	public String SellerLogin(HttpServletRequest request, Model model,
			AccountLoginParam accountLoginParam) throws Exception {
		if (accountLoginParam != null) {
			HttpSession session = request.getSession();
			// 登录
			LoginAccount loginAccount = new LoginAccount();
			BeanUtils.copyProperties(accountLoginParam, loginAccount);
			loginAccount = loginAccountService.accountLogin(loginAccount);
			if (loginAccount == null) {// 登录失败
				UserProperty userProperty = new UserProperty();
				accountLoginParam.setNewpwd(accountLoginParam.getPassword());
				BeanUtils.copyProperties(accountLoginParam, userProperty);
				userProperty = userPropertyService.accountLogin(userProperty);
				if (userProperty == null) {// 内勤登陆失败
					logger.error("登录失败！");
					return "false";
				}
				// 内勤登陆成功，保存session中
				session.setAttribute("CurrentAccount", userProperty);
				session.setAttribute("name", userProperty.getEmpName());
				// 保存用户的loginId
				session.setAttribute("loginId", userProperty.getLoginId());
				// 保存用户登录的密码password
				session.setAttribute("password", userProperty.getNewpwd());
				session.setAttribute("type", "销售内勤");
				logger.info("登录成功！");
				return "true1";
			} else {
				// 保存session中
				session.setAttribute("CurrentAccount", loginAccount);
				session.setAttribute("name", accountLoginParam.getName());
				// 保存用户的loginId
				session.setAttribute("loginId", loginAccount.getLoginId());
				// 保存用户登录的密码password
				session.setAttribute("password", loginAccount.getPassword());
				session.setAttribute("type", loginAccount.getType());
				logger.info("登录成功！");
				return "true";
			}
		}
		logger.error("登录失败！");
		return "false";
	}

	/**
	 * 获取当前登录用户信息
	 * 
	 * @param request
	 * @param model
	 * @param sellerLoginParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getCurrentUser")
	public Object getCurrentUser(HttpServletRequest request, Model model)
			throws Exception {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		String loginId = (String) session.getAttribute("loginId");
		String password = (String) session.getAttribute("password");
		String type = (String) session.getAttribute("type");
		System.out.println("类型为 "+type);
		Map<String, Object> map = new HashMap<String, Object>();
		if(type == "销售内勤"){
			UserProperty userProperty = (UserProperty) session.getAttribute("CurrentAccount");
			
			map.put("name", name);
			map.put("loginId", loginId);
			map.put("password", password);
			map.put("currentAccount", userProperty);	
			map.put("type","销售内勤");
		}else{
			LoginAccount loginAccount = (LoginAccount) session.getAttribute("CurrentAccount");
			map.put("name", name);
			map.put("loginId", loginId);
			map.put("password", password);
			map.put("currentAccount", loginAccount);
			map.put("type", "经销商");
		}
	
		logger.info("获取当前登录用户信息成功！");
		return map;
	}

	/**
	 * 更改密码
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updatePassword")
	public String updatePassword(HttpServletRequest request, Model model,
			AccountLoginParam accountLoginParam) throws Exception {
		if (accountLoginParam != null) {
			LoginAccount account = new LoginAccount();
			BeanUtils.copyProperties(accountLoginParam, account);
			boolean b = loginAccountService.updatePassword(account);
			if (b == true) {
				return "true";
			}
		}
		logger.error("修改失败！");
		return "false";
	}
	
	/**
	 * 验证用户是否具有车辆销售的权限
	 * 
	 * @param request
	 * @param model
	 * @param sellerLoginParam
	 * @return
	 * @throws Exception
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/checkIfPurchase") public void
	 * checkIfPurchase(HttpServletRequest request,Model
	 * model,HttpServletResponse response)throws Exception{ HttpSession
	 * session=request.getSession(); String name = (String)
	 * session.getAttribute("name"); LoginAccount loginAccount = (LoginAccount)
	 * session.getAttribute("CurrentAccount"); if(loginAccount==null ||
	 * name==null){ //ModelAndView mv = new ModelAndView(); //封装要显示到视图的数据
	 * //mv.addObject("msg","hello myfirst mvc"); //视图名
	 * //mv.setViewName("login/login"); //return mv; response.sendRedirect(
	 * "http://supplierwechat01.free.ngrok.cc/ElectricBicycleWechat/views/login/login.html"
	 * ); }else{ if(!"1".equals(loginAccount.getType())){//非经销商登录 String info =
	 * "1"; response.sendRedirect(
	 * "http://supplierwechat01.free.ngrok.cc/ElectricBicycleWechat/views/login/login.html?info="
	 * +info); }else{ response.sendRedirect(
	 * "http://supplierwechat01.free.ngrok.cc/ElectricBicycleWechat/views/purchaseManagement/purchaseInformation.html"
	 * ); } } }
	 */
	
	@ResponseBody
	@RequestMapping("/unBundle")
    public Object unBundle(HttpServletRequest request , Model model) throws Exception{
		HttpSession session = request.getSession();
		session.invalidate();
		return true;
    }
	
}
