package org.electricbicyclewechat.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.LoginAccount;
import org.electricbicyclewechat.pojo.request.invoicingReportParam.InvoicingReportParam;
import org.electricbicyclewechat.service.AreaService;
import org.electricbicyclewechat.service.SellerBalanceService;
import org.electricbicyclewechat.service.SellerSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 进销存信息查询
 * 
 * @author 宋晓婉
 *
 */
@Controller
@RequestMapping("/invoicingReport")
public class InvoicingReportController {
	
private static Logger logger = Logger.getLogger(InvoicingReportController.class);
	
	@Autowired
	private SellerSaleService sellerSaleService;
	@Autowired
	private SellerBalanceService sellerBalanceService;
	@Autowired
	private AreaService areaService;
	
	
	/**
	 *  查询信息
	 * @param request
	 * @param model
	 * @param saleQueryParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/search")
	public Object search(HttpServletRequest request,Model model,InvoicingReportParam invoicingReportParam)throws Exception{
		if(invoicingReportParam!=null){
			try {
				HttpSession session = request.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				List<InvoicingReportParam> dataList = new ArrayList<InvoicingReportParam>();
				LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");
				invoicingReportParam.setAccountType(loginAccount.getType());
				if("1".equals(invoicingReportParam.getAccountType())){//经销商登录
					invoicingReportParam.setSellerCode(loginAccount.getAccountCode());
				}
//				else if("2".equals(saleQueryParam.getAccountType())){//区域经理登录
//					saleQueryParam.setAreaManagerCode(loginAccount.getAccountCode());
//				}
//				String areaCode = saleQueryParam.getAreaCode();
//				if("".equals(areaCode)){//没有选择区域情况
//					
//				}else {
//					//判断是否为父区域代码
//					Area area = areaService.checkIfParentArea(areaCode);
//					if(area==null){//子区域代码
//						saleQueryParam.setIfParentArea("1");
//					}else{
//						saleQueryParam.setIfParentArea("0");
//					}
//				}
				dataList = sellerBalanceService.searchForInvoicing(invoicingReportParam);
				map.put("dataList", dataList);
				map.put("accountType", loginAccount.getType());
				logger.info("查询进销存信息成功！");
				return map;
			} catch (Exception e) {
				logger.error("查询进销存信息失败！");
				return "false";
			}
		}
		logger.error("查询进销存信息失败！");
		return "false";
	}
	
	
	
}
