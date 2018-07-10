package org.electricbicyclewechat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.electricbicyclewechat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		logger.info("查询大类信息成功！");
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
	@RequestMapping("/getSpec")
	public Object getSpec(HttpServletRequest request,Model model,String name) throws Exception{	
		List<String> result = orderService.getMaterialSpec(name);		
		logger.info("查询规格成功！");
		return result;
	}
	@ResponseBody
	@RequestMapping("/getProductColor")
	public Object getColor(HttpServletRequest request,Model model,String name,String spec) throws Exception{
		
		List<String> result = orderService.getMaterialColor(name, spec);
		
		logger.info("查询颜色成功！");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/getProductPhoto")
	public Object getPhoto(HttpServletRequest request,Model model,String name,String spec,String color) throws Exception{
		
		String result = orderService.getMaterialPhoto(name, spec, color);
//		System.out.println("123"+result);
		logger.info("查询图片成功！");
		return result;
	}
	
}
