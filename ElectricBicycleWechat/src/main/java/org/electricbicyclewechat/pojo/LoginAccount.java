package org.electricbicyclewechat.pojo;
/**
 * 登录账户表
 * @author 
 *
 */
public class LoginAccount {
	
	//登录账号
	private String loginId;  
	//账户名称
	private String accountName;  
	//密码
	private String password;
	//类型
	private String type;
	//账户代码
	private String accountCode;
	//原先密码
	private String oldPassword;
	//新密码
	private String newpwed;
	//内勤姓名
	private String empName;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewpwed() {
		return newpwed;
	}
	public void setNewpwed(String newpwed) {
		this.newpwed = newpwed;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
	
	

	

}
