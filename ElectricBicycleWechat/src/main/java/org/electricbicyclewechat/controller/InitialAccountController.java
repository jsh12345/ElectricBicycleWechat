package org.electricbicyclewechat.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.LoginAccount;
import org.electricbicyclewechat.pojo.SellerBalance;
import org.electricbicyclewechat.pojo.SellerPurchase;
import org.electricbicyclewechat.pojo.request.initialAccount.SellerBalanceParam;
import org.electricbicyclewechat.service.SellerBalanceService;
import org.electricbicyclewechat.service.SellerPurchaseService;
import org.electricbicyclewechat.service.SellerSaleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 期初入账
 * 
 * @author 宋晓婉
 *
 */
@Controller
@RequestMapping("/initialAccount")
public class InitialAccountController {
	
private static Logger logger = Logger.getLogger(InitialAccountController.class);
	
	@Autowired
	private SellerBalanceService sellerBalanceService;
	@Autowired
	private SellerPurchaseService sellerPurchaseService;
	@Autowired
	private SellerSaleService sellerSaleService;
	
	@ResponseBody
	@RequestMapping("/insertAccount")
	public String test(HttpServletRequest request,Model model,SellerBalanceParam sellerBalanceParam)throws Exception{
		if(sellerBalanceParam!=null){
			SellerBalance sellerBalance = new SellerBalance();
			BeanUtils.copyProperties(sellerBalanceParam, sellerBalance);
			boolean flag = sellerBalanceService.checkSellerBalance(sellerBalance);
			if(flag){
				int num = sellerBalanceService.insertSellerBalance(sellerBalance);
				if(num>0){
					logger.info("期初入账成功！");
					return "true";
				}else{
					logger.error("期初入账失败！");
					return "false";
				}
			}else{
				logger.error("期初入账失败！");
				return "false";
			}
		}
		logger.error("期初入账失败！");
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
	@RequestMapping("/findBicycleFromPurchase")
	public Object findBicycleFromPurchase(HttpServletRequest request,Model model,String barCode)throws Exception{
		if(barCode!=null){
			HttpSession session=request.getSession();
			LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("barCode", barCode);
			map.put("sellerCode", loginAccount.getAccountCode());
			//判断该电瓶车是否已经入库
			String sellerCode = sellerSaleService.checkIfSale(map);
			if(sellerCode!=null){//已经入库
				logger.info("该电瓶车已经入库，不能进行期初入库！");
				return "exist";
			}else{
				//从采购信息表中查询数据
				SellerPurchase sellerPurchase = sellerPurchaseService.findBicycleFromPurchase(map);
				if(sellerPurchase==null){
					logger.info("不存在该电瓶车信息！");
					return "non-existent";
				}else{
					String makeTime = sellerPurchase.getMakeDate();
					makeTime = makeTime.substring(0, makeTime.length()-11);
					sellerPurchase.setMakeDate(makeTime);
					logger.info("查询电瓶车信息成功！");
					return sellerPurchase;
				}
			}
		}
		logger.error("查询电瓶车信息失败！");
		return "false";
	}

}
