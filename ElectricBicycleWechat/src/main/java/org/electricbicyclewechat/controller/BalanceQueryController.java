package org.electricbicyclewechat.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.Area;
import org.electricbicyclewechat.pojo.AreaManager;
import org.electricbicyclewechat.pojo.LoginAccount;
import org.electricbicyclewechat.pojo.Seller;
import org.electricbicyclewechat.pojo.SellerAccount;
import org.electricbicyclewechat.pojo.SellerBalance;
import org.electricbicyclewechat.pojo.request.balanceQueryParam.BalanceQueryParam;
import org.electricbicyclewechat.service.AreaService;
import org.electricbicyclewechat.service.SellerBalanceService;
import org.electricbicyclewechat.service.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 库存信息查询
 * 
 * @author 宋晓婉
 *
 */
@Controller
@RequestMapping("/balanceQuery")
public class BalanceQueryController {
	
private static Logger logger = Logger.getLogger(BalanceQueryController.class);
	
	@Autowired
	private SellerBalanceService sellerBalanceService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private AreaService areaService;
	
	/**
	 *  查询近期库存信息
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/searchBalanceInfoOnload")
	public Object searchBalanceInfoOnload(HttpServletRequest request,Model model)throws Exception{
		try {
			HttpSession session = request.getSession();
			List<SellerBalance> balanceList = new ArrayList<SellerBalance>();
			Map<String, Object> map = new HashMap<String, Object>();
			String accountType = (String) session.getAttribute("AccountType");
			map.put("accountType", accountType);
			if("sellerAccount".equals(accountType)){//经销商登录
				SellerAccount sellerAccount = (SellerAccount) session.getAttribute("CurrentAccount");
				map.put("sellerCode", sellerAccount.getSellerCode());
			}else if("areaManager".equals(accountType)){//区域经理登录
				AreaManager areaManager = (AreaManager) session.getAttribute("CurrentAccount");
				map.put("managerCode", areaManager.getManagerCode());
				map.put("areaCode", areaManager.getAreaCode());
			}
			balanceList = sellerBalanceService.searchBalanceInfoOnload(map);
			logger.info("查询近期库存信息成功！");
			return balanceList;
		} catch (Exception e) {
			logger.info("查询近期库存信息失败！");
			return "false";
		}
			
	}
	
	/**
	 * 查看库存详情
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/balanceQueryDetail")
	public Object balanceQueryDetail(HttpServletRequest request,ModelAndView modelAndView,BalanceQueryParam balanceQueryParam)throws Exception{
		if(balanceQueryParam!=null){
			try {
				SellerBalance sellerBalance = new SellerBalance();
				BeanUtils.copyProperties(balanceQueryParam, sellerBalance);
				BalanceQueryParam balanceDtail = sellerBalanceService.balanceQueryDetail(sellerBalance);
				String makeTime = balanceDtail.getMakeDate();
				makeTime = makeTime.substring(0, makeTime.length()-11);
				balanceDtail.setMakeDate(makeTime);
				logger.info("查询库存信息详情成功！");
				return balanceDtail;
			} catch (Exception e) {
				logger.error("查询库存信息详情失败！");
				return "false";
			}
		}
		logger.error("查询库存信息详情失败！");
		return "false";
	}
	
	/**
	 *  查询库存明细信息
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/searchBalanceInfoForDetail")
	public Object searchBalanceInfo(HttpServletRequest request,Model model,BalanceQueryParam balanceQueryParam)throws Exception{
		if(balanceQueryParam!=null){
			try {
				HttpSession session = request.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				List<SellerBalance> balanceList = new ArrayList<SellerBalance>();
				List<BalanceQueryParam> sumSubtotalList = new ArrayList<BalanceQueryParam>();
				LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");
				balanceQueryParam.setAccountType(loginAccount.getType());
				if("1".equals(balanceQueryParam.getAccountType())){//经销商登录
					balanceQueryParam.setSellerCode(loginAccount.getAccountCode());
				}else if("2".equals(balanceQueryParam.getAccountType())){//区域经理登录
					balanceQueryParam.setAreaManagerCode(loginAccount.getAccountCode());
				}
				String areaCode = balanceQueryParam.getAreaCode();
				if("".equals(areaCode)){//没有选择区域情况
					
				}else {
					//判断是否为父区域代码
					Area area = areaService.checkIfParentArea(areaCode);
					if(area==null){//子区域代码
						balanceQueryParam.setIfParentArea("1");
					}else{
						balanceQueryParam.setIfParentArea("0");
					}
				}
				balanceList = sellerBalanceService.searchBalanceInfoForDetail(balanceQueryParam);
				sumSubtotalList = sellerBalanceService.balanceSumSubtotal(balanceQueryParam);
				int num = sellerBalanceService.balanceSumTotal(balanceQueryParam);
				map.put("saleList", balanceList);
				map.put("Subtotal", sumSubtotalList);
				map.put("num", num);
				map.put("accountType", loginAccount.getType());
				logger.info("查询库存明细信息成功！");
				return map;
			} catch (Exception e) {
				logger.error("查询销售明细信息失败！");
				return "false";
			}
		}
		logger.error("查询库存明细信息失败！");
		return "false";
	}
	
	/**
	 *  查询库存汇总信息
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/searchBalanceInfoForSum")
	public Object searchBalanceInfoForSum(HttpServletRequest request,Model model,BalanceQueryParam balanceQueryParam)throws Exception{
		if(balanceQueryParam!=null){
			try {
				HttpSession session = request.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				List<BalanceQueryParam> balanceList = new ArrayList<BalanceQueryParam>();
				List<BalanceQueryParam> sumSubtotalList = new ArrayList<BalanceQueryParam>();
				LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");
				balanceQueryParam.setAccountType(loginAccount.getType());
				if("1".equals(balanceQueryParam.getAccountType())){//经销商登录
					balanceQueryParam.setSellerCode(loginAccount.getAccountCode());
				}else if("2".equals(balanceQueryParam.getAccountType())){//区域经理登录
					balanceQueryParam.setAreaManagerCode(loginAccount.getAccountCode());
				}
				String areaCode = balanceQueryParam.getAreaCode();
				if("".equals(areaCode)){//没有选择区域情况
					
				}else {
					//判断是否为父区域代码
					Area area = areaService.checkIfParentArea(areaCode);
					if(area==null){//子区域代码
						balanceQueryParam.setIfParentArea("1");
					}else{
						balanceQueryParam.setIfParentArea("0");
					}
				}
				balanceList = sellerBalanceService.searchBalanceInfoForSum(balanceQueryParam);
				sumSubtotalList = sellerBalanceService.balanceSumSubtotal(balanceQueryParam);
				int num = sellerBalanceService.balanceSumTotal(balanceQueryParam);
				map.put("saleList", balanceList);
				map.put("Subtotal", sumSubtotalList);
				map.put("num", num);
				map.put("accountType", loginAccount.getType());
				logger.info("查询库存汇总信息成功！");
				return map;
			} catch (Exception e) {
				logger.error("查询销售汇总信息失败！");
				return "false";
			}
		}
		logger.error("查询库存汇总信息失败！");
		return "false";
	}
	
	/**
	 * 获取经销商账号
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getSellerInfo")
	public Object getSellerInfo(HttpServletRequest request,ModelAndView modelAndView)throws Exception{
		try {
			HttpSession session=request.getSession();
			Map<String, Object> map = new HashMap<String, Object>();
			LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");
			List<Seller> list = new ArrayList<Seller>();
			String type = loginAccount.getType();
			if("2".equals(type)){//大区经理
				map.put("areaManagerCode", loginAccount.getAccountCode());
				list = sellerService.getSellerInfo(map);
			}else{
				map.put("areaManagerCode", "");
				list = sellerService.getSellerInfo(map);
			}
			map.put("seller", list);
			logger.info("查询经销商账号信息成功！");
			return map;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error("查询经销商账号信息失败！");
			return "false";
		}
	}
	
	/**
	 * 获取区域经理区域信息
	 * @param request
	 * @param model
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getAreaInfo")
	public Object searchAreaCode(HttpServletRequest request,ModelAndView modelAndView)throws Exception{
		try {
			HttpSession session=request.getSession();
			Map<String, Object> map = new HashMap<String, Object>();
			LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");
			List<Area> list = new ArrayList<Area>();
			String type = loginAccount.getType();
			if("2".equals(type)){//大区经理
				map.put("areaManagerCode", loginAccount.getAccountCode());
				list = areaService.searchAreaCode(map);
			}else{
				map.put("areaManagerCode", "");
				list = areaService.searchAreaCode(map);
			}
			map.put("area", list);
			map.put("accountType", type);
			logger.info("查询区域信息成功！");
			return map;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error("查询区域信息失败！");
			return "false";
		}
	}
	
}
