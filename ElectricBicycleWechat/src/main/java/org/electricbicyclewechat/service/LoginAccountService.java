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
	 * 判断是否是财务或是内勤
	 * @param login_id
	 * @return
	 * @throws Exception
	 */
    public String ifAuditOrCheck(String login_id) throws Exception;

	
	/**
	 * 更改密码
	 * @return
	 */
	public boolean updatePassword(LoginAccount loginAccount)throws Exception;
	
	
}
