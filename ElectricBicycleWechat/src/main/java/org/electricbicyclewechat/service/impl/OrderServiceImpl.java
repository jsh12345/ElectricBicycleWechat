package org.electricbicyclewechat.service.impl;


import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.dao.ProductDao;
import org.electricbicyclewechat.pojo.Product;
import org.electricbicyclewechat.pojo.ShoppingCart;
import org.electricbicyclewechat.pojo.SoDetail;
import org.electricbicyclewechat.pojo.SoMaster;
import org.electricbicyclewechat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("OrderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private ProductDao productDao;
	/**
	 * 获得大类信息
	 * @return
	 */
	public List<String> getSort(){
		
		return productDao.searchSort();
	}
	@Override
	public List<String> getMaterialName(String sort) throws Exception {
		// TODO Auto-generated method stub
		return productDao.searchMaterialName(sort);
	}
	@Override
	public List<String> getMaterialSpec(String name) throws Exception {
		// TODO Auto-generated method stub
		return productDao.searchMaterialSpec(name);
	}
	@Override
	public List<String> getMaterialColor(String name, String spec)
			throws Exception {
		// TODO Auto-generated method stub
		return productDao.searchColor(name, spec);
	}
	@Override
	public Product getMaterialPhoto(String name, String spec, String color)
			throws Exception {
		// TODO Auto-generated method stub
		return productDao.searchPhoto(name, spec, color);
	}
	@Override
	public int insertToCart(ShoppingCart cart) throws Exception {
		// TODO Auto-generated method stub
		return productDao.insertToCart(cart);
	}
	@Override
	public String getColorCode(String desc) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getColorCode(desc);
	}
	@Override
	public String getMaterialCode(String name, String spec) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getMaterialCode(name, spec);
	}
	@Override
	public String getMaterialType(String code) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getMaterialType(code);
	}
	@Override
	public List<ShoppingCart> getShoppingList(String code) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getShoppingList(code);
	}
	@Override
	public int deleteOrder(String code, String material_code,String color_code)
			throws Exception {
		// TODO Auto-generated method stub
		return productDao.deleteOrder(code,material_code, color_code);
	}
	
	@Override
	public String getCustAddress(String code) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getCustAddress(code);
	}
	@Override
	public String getBillNo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub		
		productDao.getBillNo(map);
		return (String) map.get("outbillno");
	}
	
	@Transactional
	@Override	
	public boolean submitOrder(String cust_code,Map<String, Object> masterMap, List<Map<String, Object>> detailMapList) throws Exception {
		// TODO Auto-generated method stub
		//一个事务，三个操作		
		//清空购物车
		int flag1 = productDao.dropOrder(cust_code);
		//在订单主表中插入一条记录
		int flag2 = productDao.insertToMaster(masterMap);
		//在订单明细表中插入一条记录
		
		int flag3 = productDao.insertToDetail(detailMapList);
		
		if(flag1 > 0 && flag2 > 0 && flag3 > 0){
			return true;
		}else{
			return false;
		}		
	}
	@Override
	public List<SoMaster> getOrderList(String cust_code)
			throws Exception {
		// TODO Auto-generated method stub
		return productDao.getOrderList(cust_code);
	}
	@Override
	public List<SoDetail> getOrderDetail(String comp_id,
			String bill_no) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getOrderDetail(comp_id, bill_no);
	}
	@Override
	public int signOrder(String comp_id, String bill_no) throws Exception {
		// TODO Auto-generated method stub
		return productDao.signOrder(comp_id, bill_no);
		
	}
	@Override
	public String getPrice(String name, String spec) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getPrice(name, spec);
	}
	@Override
	public List<Map<String, Object>> searchByName(String material_name)
			throws Exception {
		// TODO Auto-generated method stub
		return productDao.searchByName(material_name);
	}

}
