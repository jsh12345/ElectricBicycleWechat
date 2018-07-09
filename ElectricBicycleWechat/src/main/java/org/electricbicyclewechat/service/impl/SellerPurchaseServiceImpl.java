package org.electricbicyclewechat.service.impl;

import java.util.Map;

import org.electricbicyclewechat.dao.SellerPurchaseDao;
import org.electricbicyclewechat.pojo.SellerPurchase;
import org.electricbicyclewechat.service.SellerPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SellerPurchaseService")
public class SellerPurchaseServiceImpl implements SellerPurchaseService{
	@Autowired
	private SellerPurchaseDao sellerPurchaseDao;
	
	/**
	 * 根据条形码查找电瓶车信息
	 * @param 
	 */
	@Override
	public SellerPurchase findBicycleFromPurchase(Map<String, Object> map)throws Exception{
		return sellerPurchaseDao.findBicycleFromPurchase(map);
	}
	

}
