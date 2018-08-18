package org.electricbicyclewechat.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.dao.AuditDao;
import org.electricbicyclewechat.pojo.ShipmentDetail;
import org.electricbicyclewechat.pojo.ShipmentMaster;
import org.electricbicyclewechat.pojo.SoMaster;
import org.electricbicyclewechat.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("AuditService")
public class AuditServiceImpl implements AuditService{
	
	@Autowired
	private AuditDao auditDao;

	@Override
	public List<SoMaster> findUnConfirmOrder(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return auditDao.findUnConfirmOrder(map);
	}

	@Override
	public int updateAuditSign(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return auditDao.updateAuditSign(map);
	}

	@Override
	public List<ShipmentMaster> findUnAuditOrder(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return auditDao.findUnAuditOrder(map);
	}

	@Override
	public List<ShipmentDetail> getOrderDetail(String comp_id, String bill_no)
			throws Exception {
		// TODO Auto-generated method stub
		return auditDao.getOrderDetail(comp_id , bill_no);
	}

	@Override
	public int secondAuditOrder(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
	
			
				return auditDao.updateShipmentAuditSign(map);
			
		
	}

}
