package org.electricbicyclewechat.pojo.request.balanceQueryParam;
/**
 * 经销商库存信息表
 * @author 
 *
 */
public class BalanceQueryParam {
	
	//是否为父区域代码  0为父区域代码 1为子区域代码
	private String ifParentArea;
	//区域代码
	private String areaCode;
	//账号类型
	private String accountType;
	//经销商名称
	private String sellerName;
	
	//标识经销商的代码
	private String sellerCode;
	//区域经理代码
	private String areaManagerCode;
	//车辆条码号
	private String barCode;
	//车架号
	private String frameCode;
	//电机号
	private String motorCode;
	//车型物料编码
	private String materialCode;
	//车型名称
	private String materialName;
	//车型规格
	private String maiterialSpec;
	//车辆型号
	private String materialType;
	//颜色描述
	private String colorDesc;
	//颜色描述
	private String colorCode;
	//生产日期
	private String makeDate;
	//入库日期
	private String purchaseDate;
	//开始日期
	private String startDate;
	//结束日期
	private String endDate;
	//数量
	private int num;
	
	public String getSellerCode() {
		return sellerCode;
	}
	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getFrameCode() {
		return frameCode;
	}
	public void setFrameCode(String frameCode) {
		this.frameCode = frameCode;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaiterialSpec() {
		return maiterialSpec;
	}
	public void setMaiterialSpec(String maiterialSpec) {
		this.maiterialSpec = maiterialSpec;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getColorDesc() {
		return colorDesc;
	}
	public void setColorDesc(String colorDesc) {
		this.colorDesc = colorDesc;
	}
	public String getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(String makeDate) {
		this.makeDate = makeDate;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getMotorCode() {
		return motorCode;
	}
	public void setMotorCode(String motorCode) {
		this.motorCode = motorCode;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAreaManagerCode() {
		return areaManagerCode;
	}
	public void setAreaManagerCode(String areaManagerCode) {
		this.areaManagerCode = areaManagerCode;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getIfParentArea() {
		return ifParentArea;
	}
	public void setIfParentArea(String ifParentArea) {
		this.ifParentArea = ifParentArea;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
}
