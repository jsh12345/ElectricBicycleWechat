package org.electricbicyclewechat.service;

import java.util.Map;

import org.electricbicyclewechat.pojo.LoginAccount;



public interface LoginAccountService {
	
	/**
	 * 登录时检查账户
	 * @return
	 */
	public Map<String,Object> checkifAccount(LoginAccount loginAccount)throws Exception;
	
	/**
	 * 登录
	 * @return
	 */
	public LoginAccount accountLogin(LoginAccount loginAccount)throws Exception;
	
	/**
	 * 更改密码
	 * @return
	 */
	public boolean updatePassword(LoginAccount loginAccount)throws Exception;
	
	
}
