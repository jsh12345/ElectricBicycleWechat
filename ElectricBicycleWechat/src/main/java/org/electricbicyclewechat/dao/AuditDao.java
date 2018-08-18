package org.electricbicyclewechat.dao;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.ShipmentDetail;
import org.electricbicyclewechat.pojo.ShipmentMaster;
import org.electricbicyclewechat.pojo.SoMaster;

public interface AuditDao {
	
	public List<SoMaster> findUnConfirmOrder(Map<String, Object> map);
	
	public int updateAuditSign(Map<String, Object> map);
	
	public List<ShipmentMaster> findUnAuditOrder(Map<String, Object> map);
	
	public List<ShipmentDetail> getOrderDetail(String comp_id ,String bill_no);
	
	public int updateShipmentAuditSign(Map<String, Object> map) throws Exception;
    
}
