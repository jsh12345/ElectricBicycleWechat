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
import org.electricbicyclewechat.pojo.SellerAccount;
import org.electricbicyclewechat.pojo.SellerSale;
import org.electricbicyclewechat.pojo.request.saleQueryParam.SaleQueryParam;
import org.electricbicyclewechat.service.AreaService;
import org.electricbicyclewechat.service.SellerSaleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 销售信息查询
 * 
 * @author 宋晓婉
 *
 */
@Controller
@RequestMapping("/saleQuery")
public class SaleQueryController {
	
private static Logger logger = Logger.getLogger(SaleQueryController.class);
	
	@Autowired
	private SellerSaleService sellerSaleService;
	@Autowired
	private AreaService areaService;
	
	/**
	 *  查询近期销售信息
	 * @param request
	 * @param model
	 * @param saleQueryParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/searchPCInfoOnload")
	public Object searchPCInfoOnload(HttpServletRequest request,Model model)throws Exception{
		try {
			HttpSession session = request.getSession();
			List<SellerSale> saleList = new ArrayList<SellerSale>();
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
			saleList = sellerSaleService.searchPCInfoOnload(map);
			logger.info("查询近期销售信息成功！");
			return saleList;
		} catch (Exception e) {
			logger.error("查询近期销售信息失败！");
			return "false";
		}
	}
	
	/**
	 * 查看销售详情
	 * @param request
	 * @param model
	 * @param saleQueryParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/saleQueryDetail")
	public Object saleQueryDetail(HttpServletRequest request,ModelAndView modelAndView,SaleQueryParam saleQueryParam)throws Exception{
		if(saleQueryParam!=null){
			SellerSale sellerSale = new SellerSale();
			BeanUtils.copyProperties(saleQueryParam, sellerSale);
			SaleQueryParam saleDtail = sellerSaleService.saleQueryDetail(sellerSale);
			String makeTime = saleDtail.getMakeDate();
			makeTime = makeTime.substring(0, makeTime.length()-11);
			saleDtail.setMakeDate(makeTime);
			logger.info("查询销售信息详情成功！");
			return saleDtail;
		}
		logger.error("查询销售信息详情失败！");
		return "false";
	}
	
	/**
	 *  查询销售信息
	 * @param request
	 * @param model
	 * @param saleQueryParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/searchPCInfo")
	public Object searchPCInfo(HttpServletRequest request,Model model,SaleQueryParam saleQueryParam)throws Exception{
		if(saleQueryParam!=null){
			try {
				HttpSession session = request.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				List<SellerSale> saleList = new ArrayList<SellerSale>();
				LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");
				saleQueryParam.setAccountType(loginAccount.getType());
				if("1".equals(saleQueryParam.getAccountType())){//经销商登录
					saleQueryParam.setSellerCode(loginAccount.getAccountCode());
				}else if("2".equals(saleQueryParam.getAccountType())){//区域经理登录
					saleQueryParam.setAreaManagerCode(loginAccount.getAccountCode());
				}
				String areaCode = saleQueryParam.getAreaCode();
				if("".equals(areaCode)){//没有选择区域情况
					
				}else {
					//判断是否为父区域代码
					Area area = areaService.checkIfParentArea(areaCode);
					if(area==null){//子区域代码
						saleQueryParam.setIfParentArea("1");
					}else{
						saleQueryParam.setIfParentArea("0");
					}
				}
				saleList = sellerSaleService.searchPCInfo(saleQueryParam);
				map.put("saleList", saleList);
				map.put("accountType", loginAccount.getType());
				logger.info("查询销售信息成功！");
				return map;
			} catch (Exception e) {
				logger.error("查询销售信息失败！");
				return "false";
			}
		}
		logger.error("查询销售信息失败！");
		return "false";
	}
	
	/**
	 *  查询车型排行榜信息
	 * @param request
	 * @param model
	 * @param saleQueryParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/salesRankings")
	public Object salesRankings(HttpServletRequest request,Model model,SaleQueryParam saleQueryParam)throws Exception{
		if(saleQueryParam!=null){
			try {
				HttpSession session = request.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				List<SaleQueryParam> saleList = new ArrayList<SaleQueryParam>();
				LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");
				saleQueryParam.setAccountType(loginAccount.getType());
				if("1".equals(saleQueryParam.getAccountType())){//经销商登录
					saleQueryParam.setSellerCode(loginAccount.getAccountCode());
				}else if("2".equals(saleQueryParam.getAccountType())){//区域经理登录
					saleQueryParam.setAreaManagerCode(loginAccount.getAccountCode());
				}
				String areaCode = saleQueryParam.getAreaCode();
				if("".equals(areaCode)){//没有选择区域情况
					
				}else {
					//判断是否为父区域代码
					Area area = areaService.checkIfParentArea(areaCode);
					if(area==null){//子区域代码
						saleQueryParam.setIfParentArea("1");
					}else{
						saleQueryParam.setIfParentArea("0");
					}
				}
				saleList = sellerSaleService.salesRankings(saleQueryParam);
				map.put("saleList", saleList);
				map.put("accountType", loginAccount.getType());
				logger.info("查询车型排行榜信息成功！");
				return map;
			} catch (Exception e) {
				logger.error("查询车型排行榜信息失败！");
				return "false";
			}
		}
		logger.error("查询车型排行榜信息失败！");
		return "false";
	}
	
	
}
