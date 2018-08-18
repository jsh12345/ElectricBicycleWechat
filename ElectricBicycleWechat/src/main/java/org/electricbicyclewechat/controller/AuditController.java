package org.electricbicyclewechat.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.ShipmentMaster;
import org.electricbicyclewechat.pojo.SoMaster;
import org.electricbicyclewechat.pojo.UserProperty;
import org.electricbicyclewechat.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 财务审核（两次）
 * @author jsh
 *
 */

@RestController
@RequestMapping("/audit")
public class AuditController {
  
	private static Logger logger = Logger.getLogger(UserPropertyController.class);
	
  @Autowired
  private AuditService auditService;
  
  @ResponseBody
  @RequestMapping("/findUnConfirmOrder")
  public Object findUnConfirmOrder(HttpServletRequest request , Model model,String cust_code) throws Exception{
	  Map<String, Object> map = new HashMap<String, Object>();	    
	  map.put("audit_sign","0");
	  map.put("check_sign","1");
	  map.put("cust_code", cust_code);
	  List<SoMaster> result = auditService.findUnConfirmOrder(map);
	  logger.info("查询未审核订单成功！");
  	  return result;		
  }
  
  @ResponseBody
  @RequestMapping("/findConfirmOrder")
  public Object findConfirmOrder(HttpServletRequest request , Model model,String cust_code) throws Exception{
	  Map<String, Object> map = new HashMap<String, Object>();	    
	  map.put("audit_sign","1");
	  map.put("cust_code", cust_code);
	  List<SoMaster> result = auditService.findUnConfirmOrder(map);
	  logger.info("查询未审核订单成功！");
  	  return result;		
  }
  
  @ResponseBody
  @RequestMapping("/firstAuditOrder")
  public Object firstAuditOrder(HttpServletRequest request ,Model model,String comp_id,String bill_no,String bill_date)throws Exception{
	  
	  HttpSession session = request.getSession();
  	  UserProperty userProperty = (UserProperty)session.getAttribute("CurrentAccount");	 		
      String login_id = userProperty.getLoginId();	
	  
      Map<String, Object> map = new HashMap<String, Object>();
	  
	  map.put("comp_id", comp_id);
	  map.put("bill_no", bill_no );
	  map.put("bill_date", bill_date);
	  map.put("login_id", login_id);
	  map.put("audit_sign", "1");
	  int num = auditService.updateAuditSign(map);
	  if(num > 0){
		  logger.info("财务第一次审核订单成功！");
		  return true;
	  }else{
		  logger.info("财务第一次审核订单失败！");
		  return false;
	  }  
  }
  
  @ResponseBody
  @RequestMapping("/cancelFirstAuditOrder")
  public Object cancelFirstAuditOrder(HttpServletRequest request ,Model model,String comp_id,String bill_no,String bill_date) throws Exception{
	  HttpSession session = request.getSession();
  	  UserProperty userProperty = (UserProperty)session.getAttribute("CurrentAccount");	 		
      String login_id = userProperty.getLoginId();	
	  
      Map<String, Object> map = new HashMap<String, Object>();
	  
	  map.put("comp_id", comp_id);
	  map.put("bill_no", bill_no );
	  map.put("bill_date", bill_date);
	  map.put("login_id", login_id);
	  map.put("audit_sign", "0");
	  int num = auditService.updateAuditSign(map);
	  if(num > 0){
		  logger.info("财务第一次取消审核订单成功！");
		  return true;
	  }else{
		  logger.info("财务第一次取消审核订单失败！");
		  return false;
	  }  
  }
  
  /**
   * 查询未审核的发货单
   * @param request
   * @param model
   * @param cust_code
   * @return
   * @throws Exception
   */
  @ResponseBody
  @RequestMapping("/findUnAuditOrder")
  public Object findUnAuditOrder(HttpServletRequest request , Model model,String cust_code) throws Exception{
	  Map<String, Object> map = new HashMap<String, Object>();
	  map.put("audit_sign","0");
	  map.put("check_sign","1");
	  map.put("cust_code", cust_code);
	  List<ShipmentMaster> result = auditService.findUnAuditOrder(map);
	  logger.info("查询未审核的发货单成功！");
  	  return result;		
  }
  
  @ResponseBody
  @RequestMapping("/getOrderDetail")
  public Object getOrderDetail(HttpServletRequest request , Model model,String comp_id ,String bill_no) throws Exception{ 
	  return auditService.getOrderDetail(comp_id , bill_no);
  }
  
  @ResponseBody
  @RequestMapping(value="/secondAuditOrder",produces="text/html;charset=UTF-8")
  public Object secondAuditOrder(HttpServletRequest request , Model model,String comp_id,String bill_no,String bill_date ){
	  try { 
	  HttpSession session = request.getSession();
  	  UserProperty userProperty = (UserProperty)session.getAttribute("CurrentAccount");	 		
      String login_id = userProperty.getLoginId();	
	  
      Map<String, Object> map = new HashMap<String, Object>();
	 
	  map.put("comp_id", comp_id);
	  map.put("bill_no", bill_no );
	  map.put("bill_date", bill_date);
	  map.put("login_id", login_id);
	  map.put("audit_sign", "1");
	  int num = auditService.secondAuditOrder(map);
	  if(num > 0){
		  logger.info("财务审核发货单成功！");
		  return "true";
	  }else{
		  logger.info("财务审核发货单失败！");
		  return "false";
	  }  
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		 return e.getMessage();
	}
	  
  }
  /**
   * 已进行财务审核的发货单
   * @param request
   * @param model
   * @param cust_code
   * @return
   * @throws Exception
   */
  @ResponseBody
  @RequestMapping("/findAuditOrder")
  public Object findAuditOrder(HttpServletRequest request , Model model,String cust_code) throws Exception{
	  Map<String, Object> map = new HashMap<String, Object>();
	  map.put("audit_sign","1");
	  map.put("check_sign","1");
	  map.put("cust_code", cust_code);
	  List<ShipmentMaster> result = auditService.findUnAuditOrder(map);
	  logger.info("查询已审核的发货单成功！");
  	  return result;	
  }
  
  @ResponseBody
  @RequestMapping(value="/cancelAuditOrder",produces="text/html;charset=UTF-8")
  public Object cancelAuditOrder(HttpServletRequest request , Model model,String comp_id,String bill_no,String bill_date){
	  try {
	  HttpSession session = request.getSession();
  	  UserProperty userProperty = (UserProperty)session.getAttribute("CurrentAccount");	 		
      String login_id = userProperty.getLoginId();	  
	  
      Map<String, Object> map = new HashMap<String, Object>();		 
	  map.put("comp_id", comp_id);
	  map.put("bill_no", bill_no );
	  map.put("bill_date", bill_date);
	  map.put("login_id", login_id);
	  map.put("audit_sign", "0");
      int num = auditService.secondAuditOrder(map);
	 if(num > 0){
		  logger.info("财务取消审核发货单成功！");
		  return "true";
	  }else{
		  logger.info("财务取消审核发货单失败！");
		  return "false";
	  }  
	} catch (Exception e) { 
	      return e.getMessage();
	}
  }
 
  
}
