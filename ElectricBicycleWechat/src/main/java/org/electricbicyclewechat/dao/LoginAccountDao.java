package org.electricbicyclewechat.dao;


import org.electricbicyclewechat.pojo.LoginAccount;

/**
 * 登录账户信息
 * @author 
 *
 */
public interface LoginAccountDao {
	/**
	 * 登录时检查账户
	 * @return
	 */
	public LoginAccount checkifAccount(LoginAccount loginAccount);
	
	/**
	 * 登录
	 * @return
	 */
	public LoginAccount accountLogin(LoginAccount loginAccount);
	
	/**
	 * 更改密码
	 * @return
	 */
	public int updatePassword(LoginAccount loginAccount);
	
}
