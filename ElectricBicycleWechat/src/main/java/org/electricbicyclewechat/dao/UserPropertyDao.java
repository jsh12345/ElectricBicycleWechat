package org.electricbicyclewechat.dao;

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
	

}
