package org.electricbicyclewechat.dao;


import java.util.Map;

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
	 * 判断账户是否拥有审核发货通知单的权限
	 * @return
	 */
	public int ifAudit(String login_id);
	/**
	 * 判断账户是否能进行复核订单
	 * @param login_id
	 * @return
	 */
	public int ifCheckOrder(String login_id);
	/**
	 * 判断账户是否能进行审核销售订单的权限
	 * @param login_id
	 * @return
	 */
	public int ifAuditOrder(String login_id);
	
	/**
	 * 更改密码
	 * @return
	 */
	public int updatePassword(LoginAccount loginAccount);
	
}
