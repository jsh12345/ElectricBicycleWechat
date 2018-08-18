package org.electricbicyclewechat.dao;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.SoDetail;
import org.electricbicyclewechat.pojo.SoMaster;
import org.electricbicyclewechat.pojo.UserProperty;


/**
 * 内勤登录账户信息
 * @author 
 * @param <LoginAccount>
 *
 */
public interface UserPropertyDao {
	/**
	 * 登录时检查内勤账户
	 * @return
	 */
	public UserProperty checkifAccount(UserProperty userProperty);
	
	/**
	 * 内勤登录
	 * @return
	 */
	public UserProperty accountLogin(UserProperty userProperty);
	
	/**
	 * 内勤查看未确认的订单(无论 经销商)
	 * @param map
	 * @return
	 */
	public List<SoMaster> findUnConfirmOrder(Map<String, Object> map);
	
	
    /**
     * 内勤删除某一订单下的指定电动车种类
     * @param map
     * @return
     */
	public int deleteProduct(Map<String, Object> map);
	
	public Map<String, Object> getTotal(String comp_id ,String bill_no);
	public int getSn(String comp_id ,String bill_no);
	public int updateTotal(Map<String, Object> map);
	
	public int updateSingleQty(List<Map<String, Object>> map);
	
	public int changeSinglePrice(Map<String, Object> map);
	public int changeTotalPrice(Map<String, Object> map);
	
	public int insertOneDetail(SoDetail sodetail);
	
	/**
     * 内勤确认订单
     */
	public int confirmOrder(Map<String, Object> map);
	
	

}
