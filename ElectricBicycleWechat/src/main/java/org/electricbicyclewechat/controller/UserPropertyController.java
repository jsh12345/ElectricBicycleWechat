package org.electricbicyclewechat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.electricbicyclewechat.pojo.SoDetail;
import org.electricbicyclewechat.pojo.SoMaster;
import org.electricbicyclewechat.service.OrderService;
import org.electricbicyclewechat.service.UserPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 内勤确认订单
 * @author jsh
 *
 */
@Controller
@RequestMapping("/userProperty")
public class UserPropertyController {
	
	private static Logger logger = Logger.getLogger(UserPropertyController.class);
	
	@Autowired
	private UserPropertyService userPropertyService;
	@Autowired
	private OrderService orderService;
	
	
	/**
	 * 查询未确认的订单
	 * @param request
	 * @param model
	 * @param cust_code
	 * @return
	 * @throws Exception
	 */	
    @ResponseBody
    @RequestMapping("/findUnConfirmOrder")
	public Object findUnConfirmOrder(HttpServletRequest request ,Model model,String cust_code) throws Exception{
	    Map<String, Object> map = new HashMap<String, Object>();	    
	    map.put("check_sign","0");
	    map.put("cust_code", cust_code);
	    List<SoMaster> result = userPropertyService.findUnConfirmOrder(map);
	    logger.info("查询订单成功！");
    	return result;		
	}
    /**
     * 删除订单中的某类电动车
     * @param request
     * @param model
     * @param comp_id
     * @param bill_no
     * @param s_n
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/deleteProduct")
    public Object deleteProduct(HttpServletRequest request , Model model , String comp_id, String bill_no,String s_n , String so_qty ,String price) throws Exception{
    	 
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	resultMap = userPropertyService.getTotal(comp_id, bill_no);
        double oldtotal_qty =  Double.parseDouble(resultMap.get("total_qty").toString()) ;
        double oldtotal_amt =  Double.parseDouble(resultMap.get("total_amt").toString());
        double total_qty = oldtotal_qty - Double.parseDouble(so_qty);
        double sintotal_amt = Double.parseDouble(price)*Double.parseDouble(so_qty);//此类车下单的总金额
        double total_amt = oldtotal_amt - sintotal_amt;
        Map<String, Object> map1 = new HashMap<String, Object>();
    	//封装总数量和总金额
    	map1.put("comp_id", comp_id);
    	map1.put("bill_no", bill_no);
    	map1.put("total_qty", total_qty);
    	map1.put("total_amt",total_amt);
    	
    	Map<String, Object> map2 = new HashMap<String, Object>();
    	map2.put("comp_id", comp_id);
    	map2.put("bill_no", bill_no);
    	map2.put("s_n", s_n);
    	boolean result = userPropertyService.deleteProduct(map1,map2);
    	if(result){
    		logger.info("成功删除该类电动车");
    		return true;
    	}else{
    		logger.info("删除该类电动车失败");
    		return false;
    	}
    }
    @ResponseBody
    @RequestMapping("/addNewProduct")
    public Object addProduct(HttpServletRequest request,Model model,String comp_id , String bill_no ,
    		String material_name,String material_spec , String color_desc ,String so_qty) throws Exception{    	
    	SoDetail soDetail = new SoDetail();
    	String color_code = orderService.getColorCode(color_desc);
    	String material_code = orderService.getMaterialCode(material_name,material_spec);
    	String material_type = orderService.getMaterialType(material_code);
//   	double price = Double.parseDouble(orderService.getPrice(material_name, material_spec));//单价按理是从数据库表中正常获取，由于是0，所以先写死
     	double price = 3200;
    	soDetail.setComp_id(comp_id);
    	soDetail.setBill_no(bill_no);
    	//获取s_n
    	int num = userPropertyService.getSn(comp_id, bill_no);   	
    	soDetail.setS_n(String.valueOf(num));
    	soDetail.setMaterial_code(material_code);
    	soDetail.setMaterial_name(material_name);
    	soDetail.setMaterial_type(material_type);
    	soDetail.setMaterial_spec(material_spec);
    	soDetail.setColor_code(color_code);
    	soDetail.setColor_desc(color_desc);
    	soDetail.setSo_qty(Double.parseDouble(so_qty));
    	soDetail.setUnit_code("元");//单位
    	soDetail.setShipment_qty(0);
    	soDetail.setRetu_qty(0);
        soDetail.setStand_price(price);//标准售价
        soDetail.setDiscount_rate(0);
        soDetail.setDiscount_amt(0);
        soDetail.setUnit_price(price);//单价
        soDetail.setTax_rate(0);
        soDetail.setNotax_price(0);
        soDetail.setNotax_amt(0);
        soDetail.setTax_amt(0);
        double sintotal_amt = price*Double.parseDouble(so_qty);//此类车下单的总金额
        soDetail.setTotal_amt(sintotal_amt);//某一类电动车的总金额
        soDetail.setSales_type("正价车");        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //访问订单主表，获取总金额和总数量
        resultMap = userPropertyService.getTotal(comp_id, bill_no);
        double oldtotal_qty =  Double.parseDouble(resultMap.get("total_qty").toString()) ;
        double oldtotal_amt =  Double.parseDouble(resultMap.get("total_amt").toString());
        double total_qty = oldtotal_qty + Double.parseDouble(so_qty);
    	//总数量 = so_qty + 原来相应的订单主表里的total_qty
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("total_qty",total_qty);
        System.out.println("总数量"+total_qty);
        double total_amt = oldtotal_amt + sintotal_amt;
        System.out.println("总金额"+total_amt);
        //总金额 = total_amt + 原来相应的订单主表里的total_amt;
        map.put("total_amt", total_amt);
        map.put("comp_id", comp_id);
        map.put("bill_no", bill_no);
    	boolean result = userPropertyService.addNewProduct(map, soDetail);
    	return result;
    }
    
    @ResponseBody
    @RequestMapping("/confirmOrder")
    public Object confirmOrder(HttpServletRequest request , Model model,String comp_id ,String bill_no) throws Exception{
		int num = userPropertyService.confirmOrder(comp_id, bill_no);
		if(num > 0){
			logger.info("成功确认订单！");
			return true;
		}else{
			logger.info("确认订单失败！");
			return false;
		}
    	
    }
}
