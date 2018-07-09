package org.electricbicyclewechat.pojo;
/**
 * 经销商账号表
 * @author 
 *
 */
public class SellerAccount {
	
	//标识经销商的代码
	private String sellerCode;
	//登录账号名称
	private String accountName;
	//登录密码
	private String password;
	
	public String getSellerCode() {
		return sellerCode;
	}
	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
