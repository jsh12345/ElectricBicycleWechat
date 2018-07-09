package org.electricbicyclewechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.electricbicyclewechat.dao.UserPropertyDao;
import org.electricbicyclewechat.pojo.UserProperty;
import org.electricbicyclewechat.service.UserPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
