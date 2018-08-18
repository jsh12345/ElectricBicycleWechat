package org.electricbicyclewechat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.dao.UserPropertyDao;
import org.electricbicyclewechat.pojo.SoDetail;
import org.electricbicyclewechat.pojo.SoMaster;
import org.electricbicyclewechat.pojo.UserProperty;
import org.electricbicyclewechat.service.UserPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserPropertyService")
public class UserPropertyServiceImpl implements UserPropertyService{
	@Autowired
	private UserPropertyDao userPropertyDao;
	
	@Override
	public Map<String, Object> checkifAccount(UserProperty userProperty)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(userProperty != null){
			//检查是否存在该账户
			UserProperty account = userPropertyDao.checkifAccount(userProperty);
			if(account != null){//存在该账户
				//查询该内勤账户对应的名称
				String name = account.getEmpName();
				map.put("name", name);
				map.put("code", "0");
				map.put("msg", "查询成功！");
				return map;
			}else{//不存在该账户
				map.put("code", "2");
				map.put("msg", "不存在该账户！");
				return map;
			}
		}
		map.put("code", "1");
		map.put("msg", "查询错误！");
		return map;		
	}

	/**
	 * 内勤登录
	 * @return
	 */
	@Override
	public UserProperty accountLogin(UserProperty userProperty)
			throws Exception {
		if(userProperty!=null){
			return userPropertyDao.accountLogin(userProperty);
		}
		return null;
	}

	@Override
	public List<SoMaster> findUnConfirmOrder(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return userPropertyDao.findUnConfirmOrder(map);
	}

	@Override
	public int confirmOrder(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return userPropertyDao.confirmOrder(map);
	}
	@Transactional
	@Override
	public boolean deleteProduct(Map<String, Object> map1 , Map<String, Object> map2) throws Exception {
		// TODO Auto-generated method stub		
		int flag1 = userPropertyDao.updateTotal(map1);
		int flag2 = userPropertyDao.deleteProduct(map2);
		if(flag1 > 0 && flag2 > 0){
			return true;
		}else{
			return false;
		}		
	}
	@Transactional
	@Override
	public boolean addNewProduct(Map<String, Object> map, SoDetail sodetail)
			throws Exception {
		// TODO Auto-generated method stub
		int flag1 = userPropertyDao.updateTotal(map);
		int flag2 = userPropertyDao.insertOneDetail(sodetail);
		if(flag1 > 0 && flag2 > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Map<String, Object> getTotal(String comp_id, String bill_no)
			throws Exception {
		// TODO Auto-generated method stub
		return userPropertyDao.getTotal(comp_id, bill_no);
	}

	@Override
	public int getSn(String comp_id, String bill_no) throws Exception {
		// TODO Auto-generated method stub
		return userPropertyDao.getSn(comp_id, bill_no);
	}
	@Transactional
	@Override
	public boolean saveOrder(Map<String, Object> masterMap,
			List<Map<String, Object>> detailMapList) throws Exception {
		// TODO Auto-generated method stub
		int flag1 = userPropertyDao.updateSingleQty(detailMapList);
		int flag2 = userPropertyDao.updateTotal(masterMap);
		if(flag1 >0 && flag2 > 0){
			return true;
		}else{
			return false;
		}		
	}
	
	@Transactional
	@Override
	public boolean changePrice(Map<String, Object> map1,
			Map<String, Object> map2) throws Exception {
		// TODO Auto-generated method stub
		int flag1 = userPropertyDao.changeSinglePrice(map1);
		int flag2 = userPropertyDao.changeTotalPrice(map2);
		if(flag1 > 0 && flag2 > 0){
			return true;
		}else{
			return false;
		}
	}

	


}
