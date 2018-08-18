package org.electricbicyclewechat.service;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.ShipmentDetail;
import org.electricbicyclewechat.pojo.ShipmentMaster;
import org.electricbicyclewechat.pojo.SoMaster;

public interface AuditService {
	/**
	 * 财务查询出还未审核的订单
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<SoMaster> findUnConfirmOrder(Map<String, Object> map) throws Exception;
	
	public int updateAuditSign(Map<String, Object> map) throws Exception;
	
	public List<ShipmentMaster> findUnAuditOrder(Map<String, Object> map) throws Exception;
	
	public List<ShipmentDetail> getOrderDetail(String comp_id , String bill_no) throws Exception;
    
	public int secondAuditOrder(Map<String, Object> map) throws Exception;
	
}
