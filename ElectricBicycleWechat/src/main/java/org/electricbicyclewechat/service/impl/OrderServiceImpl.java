package org.electricbicyclewechat.service.impl;


import java.util.List;

import org.electricbicyclewechat.dao.ProductDao;
import org.electricbicyclewechat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public String getMaterialPhoto(String name, String spec, String color)
			throws Exception {
		// TODO Auto-generated method stub
		return productDao.searchPhoto(name, spec, color);
	}

}
