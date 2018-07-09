package org.electricbicyclewechat.pojo;
/**
 * 内勤登录账户表
 * @author 
 *
 */
public class UserProperty {
	//内勤登录账号
	private String loginId;   
	//新密码
	private String newpwd;
	//旧密码
	private String oldpwed;
	//内勤代码
	private String empId;
	//内勤姓名
	private String empName;
	//属性
	private String attr;
	//停止登陆
	private String stopLogin;
	//新增公司代码
	private String appendCompid;
	//查询公司代码
	private String queryCompid;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getOldpwed() {
		return oldpwed;
	}
	public void setOldpwed(String oldpwed) {
		this.oldpwed = oldpwed;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getStopLogin() {
		return stopLogin;
	}
	public void setStopLogin(String stopLogin) {
		this.stopLogin = stopLogin;
	}
	public String getAppendCompid() {
		return appendCompid;
	}
	public void setAppendCompid(String appendCompid) {
		this.appendCompid = appendCompid;
	}
	public String getQueryCompid() {
		return queryCompid;
	}
	public void setQueryCompid(String queryCompid) {
		this.queryCompid = queryCompid;
	}

}
