package org.electricbicyclewechat.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.Seller;
import org.electricbicyclewechat.pojo.request.seller.SellerParam;
import org.electricbicyclewechat.service.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 创建经销商基本信息
 * 
 * @author 魏坤
 *
 */
@Controller
@RequestMapping("/seller")
public class SellerController {
	
private static Logger logger = Logger.getLogger(SellerController.class);
	
	@Autowired
	private SellerService sellerService;
	
	@ResponseBody
	@RequestMapping("/insertSeller")
	public String test(HttpServletRequest request,Model model,SellerParam sellerParam)throws Exception{
		if(sellerParam!=null){
			Seller seller = new Seller();
			BeanUtils.copyProperties(sellerParam, seller);
			boolean flag = sellerService.checkSeller(seller);
			if(flag){
				int num = sellerService.insertSeller(seller);
				if(num>0){
					logger.info("创建经销商基本信息成功！");
					return "true";
				}else{
					logger.error("创建经销商基本信息失败！");
					return "false";
				}
			}else{
				logger.error("创建经销商基本信息失败！");
				return "false";
			}
		}
		logger.error("创建经销商基本信息失败！");
		return "false";
	}

	/**
	 * 根据代码、名称等查询经销商详细信息
	 * @param request
	 * @param model
	 * @param frameCode
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findSellerDetail")
	public Object findBicycle(HttpServletRequest request,Model model,SellerParam sellerParam)throws Exception{
		if(sellerParam!=null){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sellerCode", sellerParam.getSellerCode());
			map.put("sellerName", sellerParam.getSellerName());
			Seller seller = sellerService.findSellerDetail(map);
			if(seller==null){
				logger.info("不存在该经销商！");
				return "none";
			}else{
				logger.info("查询经销商成功！");
				return seller;
			}
		}
		logger.error("经销商信息失败！");
		return "false";
	}
}
