package org.electricbicyclewechat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.So_master;
import org.electricbicyclewechat.pojo.So_detail;
import org.electricbicyclewechat.pojo.request.so_masterParam.So_masterParam;
import org.electricbicyclewechat.pojo.LoginAccount;
import org.electricbicyclewechat.pojo.Seller;
import org.electricbicyclewechat.pojo.SellerAccount;
import org.electricbicyclewechat.pojo.UserProperty;
import org.electricbicyclewechat.service.OrderStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 订单统计信息查询
 * 
 * @author JackGao
 *
 */
@Controller
@RequestMapping("/orderStatistics")
public class OrderStatisticsController {

	private static Logger logger = Logger
			.getLogger(OrderStatisticsController.class);

	@Autowired
	private OrderStatisticsService orderStatisticsService;

	/**
	 * 01查询近期订单信息
	 * 
	 * @param request
	 * @param model
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/searchOrderInfoOnload")
	public Object searchOrderInfoOnload(HttpServletRequest request, Model model)
			throws Exception {

		HttpSession session = request.getSession();
		List<So_master> balanceList = new ArrayList<So_master>();
		Map<String, Object> map = new HashMap<String, Object>();

		// 经销商登录
		UserProperty userProperty = (UserProperty) session
				.getAttribute("CurrentAccount");

//		map.put("sellerCode", userProperty.getLoginId());

		balanceList = orderStatisticsService.searchOrderInfoOnload();
		// System.out.println("123 " + balanceList);
		logger.info("查询订单信息成功！");
		return balanceList;

	}

	/**
	 * 02查看订单详情
	 * 
	 * @param request
	 * @param model
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("orderDetail")
	public Object orderDetail(HttpServletRequest request,
			ModelAndView modelAndView, So_masterParam so_master)
			throws Exception {
		
        System.out.println("gy " + so_master.getBill_no());
		
		So_master sellerBalance = new So_master();
		BeanUtils.copyProperties(so_master, sellerBalance);
		So_masterParam balanceDtail = orderStatisticsService
				.OrderDetail(sellerBalance);

//		System.out.println("=======" + balanceDtail.getTransport_billno());
		
		logger.info("查询订单信息详情成功！");
		return balanceDtail;
	}

	/**
	 * 03查询订单数量汇总
	 * 
	 * @param request
	 * @param model
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/searchOrderInfoForNum")
	public Object searchOrderInfoForNum(HttpServletRequest request, Model model,
			So_masterParam so_master) throws Exception {
		
//		System.out.println("=========="+so_master);
		
		if (so_master != null) {
			try {
				System.out.println("=========="+so_master);
				HttpSession session = request.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				
				List<So_master> balanceList = new ArrayList<So_master>();
				List<So_masterParam> sumSubtotalList = new ArrayList<So_masterParam>();
				
				

				balanceList = orderStatisticsService.searchOrderInfoForNum(so_master);
				sumSubtotalList = orderStatisticsService.orderNumSum(so_master);	
				int num = orderStatisticsService.orderSumTotal(so_master);
				
				
				map.put("saleList", balanceList);
				map.put("Subtotal", sumSubtotalList);
				map.put("num", num);
				
				
				//map.put("accountType", loginAccount.getType());
				logger.info("查询订单数量汇总信息成功！");
				return map;
			} catch (Exception e) {
				System.out.println("=========错误原因："+e);
				logger.error("查询订单数量汇总信息失败！");
				return "false";
			}
		}
		logger.error("查询订单数量汇总信息失败！");
		return "false";
	}

	/**
	 * 04查询订单车型汇总信息
	 * 
	 * @param request
	 * @param model
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/searchOrderInfoForType")
	public Object searchOrderInfoForType(HttpServletRequest request,
			Model model, So_masterParam so_detail) throws Exception {
		System.out.println(so_detail.getMaterial_code());
		if (so_detail != null) {
			try {
				HttpSession session = request.getSession();
				Map<String, Object> map = new HashMap<String, Object>();
				//System.out.println("==============1：");
				List<So_detail> balanceList = new ArrayList<So_detail>();
				List<So_detail> sumSubtotalList = new ArrayList<So_detail>();
				//System.out.println("==============2：");
				balanceList = orderStatisticsService.searchOrderInfoForType(so_detail);
				sumSubtotalList = orderStatisticsService.orderTypeSum(so_detail);
				int num = orderStatisticsService.carTypeTotal(so_detail);
				//System.out.println("==============3：");
				map.put("saleList", balanceList);
				map.put("Subtotal", sumSubtotalList);
				map.put("num", num);

				logger.info("查询订单车型汇总信息成功！");
				return map;
			} catch (Exception e) {
				System.out.println("==============错误原因："+e);
				logger.error("查询订单车型汇总信息失败！");
				return "false";
			}
		}
		logger.error("查询订单车型汇总信息失败！");
		return "false";
	}

	/**
	 * 05获取账号
	 * 
	 * @param request
	 * @param model
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getSellerInfo")
	public Object getSellerInfo(HttpServletRequest request,
			ModelAndView modelAndView) throws Exception {
		try {
			HttpSession session = request.getSession();
			Map<String, Object> map = new HashMap<String, Object>();
			UserProperty userProperty = (UserProperty) session
					.getAttribute("CurrentAccount");
			List<Seller> list = new ArrayList<Seller>();

			map.put("seller", list);
			logger.info("查询经销商账号信息成功！");
			return map;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error("查询经销商账号信息失败！");
			return "false";
		}
	}

}
