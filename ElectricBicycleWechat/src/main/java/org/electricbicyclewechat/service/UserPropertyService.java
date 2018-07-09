package org.electricbicyclewechat.service;

import java.util.Map;

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
}
