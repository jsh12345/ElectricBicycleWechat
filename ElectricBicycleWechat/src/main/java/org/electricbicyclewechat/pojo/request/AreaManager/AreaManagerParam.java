package org.electricbicyclewechat.pojo.request.AreaManager;
/**
 * 创建区域经理信息
 * @author 
 *
 */
public class AreaManagerParam {
	//区域经理代码,主关键字
	private String managerCode;
	//区域经理姓名
	private String managerName;
	//区域经理手机号码
	private String managerMobile;
	//区域经理微信号
	private String mangaerWX;
	//区域经理登录账号
	private String managerAccount;
	//区域经理登录密码
	private String managerPassword;
	//负责的区域代码,area的主关键字，外码
	private String areaCode;
	
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerMobile() {
		return managerMobile;
	}
	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}
	public String getMangaerWX() {
		return mangaerWX;
	}
	public void setMangaerWX(String mangaerWX) {
		this.mangaerWX = mangaerWX;
	}
	public String getManagerAccount() {
		return managerAccount;
	}
	public void setManagerAccount(String managerAccount) {
		this.managerAccount = managerAccount;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
}
