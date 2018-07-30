package org.electricbicyclewechat.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.LoginAccount;
import org.electricbicyclewechat.pojo.Product;
import org.electricbicyclewechat.pojo.ShoppingCart;
import org.electricbicyclewechat.service.OrderService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 订单
 * @author jsh
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	private static Logger logger = Logger.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@RequestMapping("/getSort")
	public Object getSort(HttpServletRequest request,Model model) throws Exception{
		List<String> result = orderService.getSort();
		logger.info("查询大类成功！");
		return result;
			
	}
	@ResponseBody
	@RequestMapping("/getMaterialName")
	public Object getMaterialName(HttpServletRequest request,Model model,String sort) throws Exception{	
		List<String> result = orderService.getMaterialName(sort);		
		logger.info("查询名称成功！");
		return result;
	}
	@ResponseBody
	@RequestMapping("/getListByName")
	public Object getListByName(HttpServletRequest request ,Model model,String material_name) throws Exception{
		List<Map<String, Object>> result = orderService.searchByName(material_name);
//		System.out.println(result);
		logger.info("根据名称查询对应规格的车信息成功！");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/getSpec")
	public Object getSpec(HttpServletRequest request,Model model,String name) throws Exception{	
		List<String> result = orderService.getMaterialSpec(name);		
		logger.info("查询规格成功！");
		return result;
	}
	@ResponseBody
	@RequestMapping("/getProductColor")
	public Object getColor(HttpServletRequest request,Model model,String name,String spec) throws Exception{		
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> colorList = orderService.getMaterialColor(name, spec);		
		String price = orderService.getPrice(name, spec);
		map.put("colorList", colorList);
		map.put("price", price);
		logger.info("查询颜色成功！");
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getProductPhoto")
	public Object getPhoto(HttpServletRequest request,Model model,String name,String spec,String color) throws Exception{				
		Product result = orderService.getMaterialPhoto(name, spec, color);
		logger.info("查询图片和库存数量成功！");
		return result;
	}
	@ResponseBody
	@RequestMapping("/insertToCart")
	public String insertToCart(HttpServletRequest request,Model model,String so_qty,String material_name,String material_spec,String color_desc,String stand_price) throws Exception{
		ShoppingCart cart = new ShoppingCart();
		cart.setComp_id("0001");
		
		HttpSession session = request.getSession();
		LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");	 		
		String code = loginAccount.getAccountCode();	
		String name = loginAccount.getAccountName(); 
		
		cart.setCust_code(code);
		cart.setCust_name(name);
		
		cart.setColor_desc(color_desc);
		String color_code = orderService.getColorCode(color_desc);
	    cart.setColor_code(color_code);
	    
		cart.setMaterial_name(material_name);
		cart.setMaterial_spec(material_spec);
		String material_code = orderService.getMaterialCode(material_name,material_spec);
		cart.setMaterial_code(material_code);
		String material_type = orderService.getMaterialType(material_code);
		cart.setMaterial_type(material_type);		
		cart.setSo_qty(so_qty);
		cart.setUnit_code("元");
		cart.setStand_price(Double.parseDouble(stand_price));
		double total_amt = (Integer.parseInt(so_qty))*(Double.parseDouble(stand_price));
		cart.setTotal_amt(total_amt);
		
		int num = orderService.insertToCart(cart);
		if (num > 0) {
			logger.info("购物车插入成功！");
			return "true";
		} else {
			logger.error("购物车插入失败！");
			return "false";
	    }
	}
	/**
	 * 得到购物车列表
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/getShoppingList")
	public Object getShoppingList(HttpServletRequest request,Model model) throws Exception{
//		String code = "AP";
		HttpSession session = request.getSession();
		LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");	 		
		String code = loginAccount.getAccountCode();	
		return orderService.getShoppingList(code);
		
	}
	
	@ResponseBody
	@RequestMapping("/deleteOrder")
	public String deleteOrder(HttpServletRequest request , Model model ,String material_code , String color_code) throws Exception{
//		String code= "AP";
		HttpSession session = request.getSession();
		LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");	 		
		String code = loginAccount.getAccountCode();	
		int num = orderService.deleteOrder(code, material_code, color_code);
		if (num > 0) {
			logger.info("商品删除成功！");
			return "true";
		} else {
			logger.error("商品删除失败！");
			return "false";
	    }
	}
	
	@ResponseBody
	@RequestMapping("/submitOrder")
	public String submitOrder(HttpServletRequest request,Model model,String total_amt,String total_qty,String receive_date, String detail) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("compid", "0001");
		map.put("billsort", "0500");
		map.put("billhead", null);
		HttpSession session = request.getSession();
		LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");	 		
		String code = loginAccount.getAccountCode();	
		String loginId = loginAccount.getLoginId();
		String name = loginAccount.getAccountName();
		map.put("loginid", loginId);
		map.put("saveno", "1");
		String bill_no = null;
		map.put("outbillno", bill_no);
	    bill_no = orderService.getBillNo(map) ;//获取订单号
	
//		String cust_code = "AP";
		String cust_delivery_address = orderService.getCustAddress(code);//获取经销商送货地址
		Map<String, Object> masterMap = new HashMap<String, Object>();
		
		masterMap.put("comp_id", "0001");
		masterMap.put("bill_no", bill_no);		
		masterMap.put("I_E_F", "I");
		Timestamp systime = new Timestamp(System.currentTimeMillis());
		masterMap.put("bill_date",systime);
		masterMap.put("bill_type", "整车订单");
		
		masterMap.put("cust_code", code);
		
		masterMap.put("cust_name", name);
		masterMap.put("exchange_rate", 6.25);
		masterMap.put("forecast_shipmentdate", receive_date);
		masterMap.put("cust_delivery_address", cust_delivery_address);
		masterMap.put("other_amt", 0);
		masterMap.put("total_qty", total_qty);
		masterMap.put("total_amt", total_amt);		
		masterMap.put("login_empid", loginId);
		masterMap.put("login_name", name);		
		masterMap.put("login_date",systime);		
		masterMap.put("check_sign", 0);
		masterMap.put("audit_sign", 0);
		masterMap.put("indepot_sign", 0);
		masterMap.put("shipment_sign", 0);
		masterMap.put("outdepot_sign", 0);
		masterMap.put("end_sign", 0);		
		//涉及到明细表的时候还是要传入一个list对象
		List<Map<String, Object>> detailMapList = new ArrayList<Map<String,Object>>();				
//		System.out.println("从前台传入的订单详细信息为 "+detail);
		
		JSONArray jsonArray = new JSONArray(detail);		
		for(int i=0 ; i < jsonArray.length(); i++){
//			System.out.println(jsonArray.getJSONObject(i).get("color"));
			Map<String, Object> detailMap = new HashMap<String, Object>();			
			detailMap.put("comp_id", "0001");
			detailMap.put("bill_no", bill_no);
			detailMap.put("s_n", i);
			detailMap.put("material_code", jsonArray.getJSONObject(i).get("material_code"));
			detailMap.put("material_name", jsonArray.getJSONObject(i).get("name"));
			detailMap.put("material_type", jsonArray.getJSONObject(i).get("material_type"));
			detailMap.put("material_spec", jsonArray.getJSONObject(i).get("spec"));
			detailMap.put("color_code", jsonArray.getJSONObject(i).get("color_code"));
			detailMap.put("color_desc", jsonArray.getJSONObject(i).get("color"));
			detailMap.put("so_qty", jsonArray.getJSONObject(i).get("qty"));		
			detailMap.put("unit_code", jsonArray.getJSONObject(i).get("unit_code"));
			detailMap.put("shipment_qty", 0);
			detailMap.put("retu_qty", 0);
			detailMap.put("stand_price", jsonArray.getJSONObject(i).get("price"));//标准售价
			detailMap.put("discount_rate", 0);
			detailMap.put("discount_amt", 0);			
			detailMap.put("unit_price", jsonArray.getJSONObject(i).get("price"));//单价
			
			detailMap.put("tax_rate", 0);//税率
			
			detailMap.put("notax_price", 0);//不含税单价=单价/(税率+1)待定
			detailMap.put("notax_amt", 0);//不含税金额
			detailMap.put("tax_amt", 0);//税额
			detailMap.put("total_amt",Double.parseDouble((String) jsonArray.getJSONObject(i).get("price"))*Double.parseDouble((String) jsonArray.getJSONObject(i).get("qty")) );//总金额
			detailMap.put("sales_type", "正价车");			
		    
			detailMapList.add(detailMap);			
		}		
		
		boolean result = orderService.submitOrder(code , masterMap , detailMapList);
		if (result) {
			logger.info("订单提交成功！");
			return "true";
		} else {
			logger.error("订单提交失败！");
			return "false";
	    }		
	}
	
	@ResponseBody
	@RequestMapping("/getOrderList")
	public Object getOrderList(HttpServletRequest request,Model model) throws Exception{
		HttpSession session = request.getSession();
		
		LoginAccount loginAccount = (LoginAccount)session.getAttribute("CurrentAccount");	 		
		String code = loginAccount.getAccountCode();	
//	    System.out.println(code);
		return orderService.getOrderList(code);		
	}
	
	@ResponseBody
	@RequestMapping("/getOrderDetail")
	public Object getOrderDetail(HttpServletRequest request , Model model,String comp_id,String bill_no) throws Exception{
		
		return orderService.getOrderDetail(comp_id, bill_no);
	}
	@ResponseBody
	@RequestMapping("/signOrder")
	public String signOrder(HttpServletRequest request,Model model,String comp_id,String bill_no) throws Exception{
		int num = orderService.signOrder(comp_id, bill_no);
		if(num > 0){
			logger.info("订单签收成功！");
			return "true";
		}else{
			logger.info("订单签收失败！");
			return "false";
		}
	}
	
}
