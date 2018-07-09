package org.electricbicyclewechat.dao;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.SellerPurchase;

/**
 * 经销商采购信息表
 * @author 
 *
 */
public interface SellerPurchaseDao {
	
	/**
	 * 根据条形码查找电瓶车信息
	 * @param 
	 */
	public SellerPurchase findBicycleFromPurchase(Map<String, Object> map);
	
	/**
	 * 获取所有的经销商采购信息
	 * @return
	 */
	public List<SellerPurchase> findAllSellerPurchase();
	

}
