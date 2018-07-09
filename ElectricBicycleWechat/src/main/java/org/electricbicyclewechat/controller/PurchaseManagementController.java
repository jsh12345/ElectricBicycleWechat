package org.electricbicyclewechat.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.LoginAccount;
import org.electricbicyclewechat.pojo.SellerBalance;
import org.electricbicyclewechat.pojo.SellerSale;
import org.electricbicyclewechat.pojo.request.purchaseManagement.SellerSaleParam;
import org.electricbicyclewechat.service.SellerBalanceService;
import org.electricbicyclewechat.service.SellerSaleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 销售管理
 * 
 * @author 宋晓婉
 *
 */
@Controller
@RequestMapping("/purchaseManagement")
public class PurchaseManagementController {
	
private static Logger logger = Logger.getLogger(PurchaseManagementController.class);
	
	@Autowired
	private SellerBalanceService sellerBalanceService;
	@Autowired
	private SellerSaleService sellerSaleService;
	
	/**
	 * 根据车架号查询电瓶车信息
	 * @param request
	 * @param model
	 * @param frameCode
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findBicycle")
	public Object findBicycle(HttpServletRequest request,Model model,SellerSaleParam sellerSaleParam)throws Exception{
		if(sellerSaleParam!=null){
			HttpSession session=request.getSession();
			LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("frameCode", sellerSaleParam.getFrameCode());
			map.put("barCode", sellerSaleParam.getBarCode());
			map.put("sellerCode", loginAccount.getAccountCode());
			SellerBalance sellerBalance = sellerBalanceService.findBicycleByFrameCode(map);
			if(sellerBalance==null){//库存中没有信息，可能是盘盈情况，可以进行盘点功能
				logger.info("库存中没有信息，可能是盘盈情况！");
				return "inventory";
			}else{
				String makeTime = sellerBalance.getMakeDate();
				makeTime = makeTime.substring(0, makeTime.length()-11);
				sellerBalance.setMakeDate(makeTime);
				logger.info("查询电瓶车信息成功！");
				return sellerBalance;
			}
		}
		logger.error("电瓶车信息失败！");
		return "false";
	}
	
	/**
	 * 根据条形码查询电瓶车信息
	 * @param request
	 * @param model
	 * @param frameCode
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findBicycleByBarCode")
	public Object findBicycleByBarCode(HttpServletRequest request,Model model,String barCode)throws Exception{
		if(barCode!=null){
			HttpSession session=request.getSession();
			LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("barCode", barCode);
			map.put("sellerCode", loginAccount.getAccountCode());
			SellerBalance sellerBalance = sellerBalanceService.findBicycleByBarCode(map);
			if(sellerBalance==null){//库存中没有信息，可能是盘盈情况，可以进行盘点功能
				logger.info("库存中没有信息，可能是盘盈情况！");
				return "inventory";
			}else{
				String makeTime = sellerBalance.getMakeDate();
				makeTime = makeTime.substring(0, makeTime.length()-11);
				sellerBalance.setMakeDate(makeTime);
				logger.info("查询电瓶车信息成功！");
				return sellerBalance;
			}
		}
		logger.error("电瓶车信息失败！");
		return "false";
	}
	
	/**
	 * 系统保存销售信息
	 * @param request
	 * @param model
	 * @param frameCode
	 * @return
	 * @throws Exception
	 */
	@Transactional  
	@ResponseBody
	@RequestMapping("/saveSellInfo")
	public Object saveSellInfo(HttpServletRequest request,Model model,SellerSaleParam sellerSaleParam)throws Exception{
		if(sellerSaleParam!=null){
			HttpSession session=request.getSession();
			LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");
			SellerSale sellerSale = new SellerSale();
			String sex = sellerSaleParam.getSex();
			sellerSaleParam.setBuyerSex(sex);
			BeanUtils.copyProperties(sellerSaleParam, sellerSale);
			//根据车架号或者条形码查询车辆信息
			Map<String, Object> findBicyclemap = new HashMap<String, Object>();
			findBicyclemap.put("frameCode", sellerSaleParam.getFrameCode());
			findBicyclemap.put("sellerCode", loginAccount.getAccountCode());
			SellerBalance sellerBalance = sellerBalanceService.findBicycleByFrameCode(findBicyclemap);
			//保存销售信息
			String makeTime = sellerBalance.getMakeDate();
			makeTime = makeTime.substring(0, makeTime.length()-11);
			sellerBalance.setMakeDate(makeTime);
			BeanUtils.copyProperties(sellerBalance, sellerSale);
			try {
			int num = sellerSaleService.saveSellInfo(sellerSale);
			if(num<1){//保存销售信息失败
				logger.info("保存销售信息失败！");
				throw new RuntimeException("保存销售信息失败！");
			}else{//删除库存信息
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("barCode", sellerSale.getBarCode());
				map.put("sellerCode", sellerSale.getSellerCode());
				int count = sellerBalanceService.deleteBybarCode(map);
				if(count<1){//删除库存信息失败
					logger.info("删除库存信息失败！");
					throw new RuntimeException("删除库存信息失败！");
				}else{
					logger.info("删除库存信息成功！");
					return "true";
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.error("系统保存销售信息失败！");
		return "false";
	}

}
