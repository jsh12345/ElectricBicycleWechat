package org.electricbicyclewechat.pojo;
/**
 * 区域信息
 * @author 
 *
 */
public class Area {
	
	//区域代码
	private String areaCode;  
	//区域名称
	private String areaName;
	//上级区域代码
	private String parentAreaCode;
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getParentAreaCode() {
		return parentAreaCode;
	}
	public void setParentAreaCode(String parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
	}
	

	

}
