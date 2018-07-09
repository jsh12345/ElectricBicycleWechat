package org.electricbicyclewechat.pojo.request.invoicingReportParam;
/**
 * 进销存信息
 * @author 
 *
 */
public class InvoicingReportParam {
	//标识经销商的代码
	private String sellerCode;
	//车型物料编码
	private String material_code;
	//车型名称
	private String material_name;
	//车型规格
	private String material_spec;
	//车辆型号
	private String material_type;
	//颜色描述
	private String color_desc;
	//颜色描述
	private String color_code;
	//开始日期
	private String startDate;
	//结束日期
	private String endDate;
	//期初数据
	private int init_qty;
	//入库数据
	private int purchase_qty;
	//出库数据
	private int sale_qty;
	//结存数据
	private int end_qty;
	//当前库存量
	private int stock_qty;
	//账号类型
	private String accountType;
	
	public String getSellerCode() {
		return sellerCode;
	}
	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}
	public String getMaterial_code() {
		return material_code;
	}
	public void setMaterial_code(String material_code) {
		this.material_code = material_code;
	}
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	public String getMaterial_type() {
		return material_type;
	}
	public void setMaterial_type(String material_type) {
		this.material_type = material_type;
	}
	public String getColor_desc() {
		return color_desc;
	}
	public void setColor_desc(String color_desc) {
		this.color_desc = color_desc;
	}
	public String getColor_code() {
		return color_code;
	}
	public void setColor_code(String color_code) {
		this.color_code = color_code;
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
	
	public int getInit_qty() {
		return init_qty;
	}
	public void setInit_qty(int init_qty) {
		this.init_qty = init_qty;
	}
	public int getPurchase_qty() {
		return purchase_qty;
	}
	public void setPurchase_qty(int purchase_qty) {
		this.purchase_qty = purchase_qty;
	}
	public int getSale_qty() {
		return sale_qty;
	}
	public void setSale_qty(int sale_qty) {
		this.sale_qty = sale_qty;
	}
	public int getEnd_qty() {
		return end_qty;
	}
	public void setEnd_qty(int end_qty) {
		this.end_qty = end_qty;
	}
	public int getStock_qty() {
		return stock_qty;
	}
	public void setStock_qty(int stock_qty) {
		this.stock_qty = stock_qty;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getMaterial_spec() {
		return material_spec;
	}
	public void setMaterial_spec(String material_spec) {
		this.material_spec = material_spec;
	}
	
	
	
	
	
	
	
	

}
