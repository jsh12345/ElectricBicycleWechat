package org.electricbicyclewechat.pojo.request.login;

public class UserPropertyParam {
	//内勤登录账号
	private String loginId;
	//新密码
	private String newpwd;
	//内勤姓名
	private String empName;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwed(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
}
