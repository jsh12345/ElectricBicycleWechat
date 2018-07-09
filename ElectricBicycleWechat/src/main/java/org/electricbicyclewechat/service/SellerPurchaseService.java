package org.electricbicyclewechat.service;

import java.util.Map;

import org.electricbicyclewechat.pojo.SellerPurchase;


public interface SellerPurchaseService {
	
	/**
	 * 根据条形码查找电瓶车信息
	 * @param 
	 */
	public SellerPurchase findBicycleFromPurchase(Map<String, Object> map)throws Exception;
	
	
}
