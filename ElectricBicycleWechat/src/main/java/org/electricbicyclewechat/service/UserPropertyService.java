package org.electricbicyclewechat.service;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.SoDetail;
import org.electricbicyclewechat.pojo.SoMaster;
import org.electricbicyclewechat.pojo.UserProperty;

public interface UserPropertyService {
	/**
	 * 登录时检查账户
	 * @return
	 */
	public Map<String,Object> checkifAccount(UserProperty userProperty)throws Exception;
	
	/**
	 * 登录
	 * @return
	 */
	public UserProperty accountLogin(UserProperty userProperty)throws Exception;
	/**
	 * 内勤查询未确认的订单
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<SoMaster> findUnConfirmOrder(Map<String, Object> map) throws Exception;
  
	public boolean deleteProduct(Map<String, Object> map1 , Map<String, Object> map2) throws Exception;
	public int getSn(String comp_id ,String bill_no) throws Exception;
	
	public Map<String, Object> getTotal(String comp_id,String bill_no) throws Exception;
	
	public boolean addNewProduct(Map<String, Object> map , SoDetail sodetail) throws Exception;
	/**
    * 内勤确认订单
    * @param comp_id
    * @param bill_no
    * @return
    * @throws Exception
    */
	public int confirmOrder(String comp_id ,String bill_no) throws Exception;
}
