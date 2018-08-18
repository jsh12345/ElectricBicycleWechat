package org.electricbicyclewechat.pojo;

public class ShipmentMaster {
	
	private String comp_id;
	private String bill_no;
	private String bill_date;
	private String cust_code;
    private String orig_totalamt;
    private String local_totalamt;
    private String total_qty;
    private String audit_sign;
    private String check_sign;
    private String shipment_sign;
    private String cust_sign;
    private String end_sign;
    
    
	public String getComp_id() {
		return comp_id;
	}
	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}
	public String getBill_no() {
		return bill_no;
	}
	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	public String getCust_code() {
		return cust_code;
	}
	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}
	public String getOrig_totalamt() {
		return orig_totalamt;
	}
	public void setOrig_totalamt(String orig_totalamt) {
		this.orig_totalamt = orig_totalamt;
	}
	public String getLocal_totalamt() {
		return local_totalamt;
	}
	public void setLocal_totalamt(String local_totalamt) {
		this.local_totalamt = local_totalamt;
	}
	public String getAudit_sign() {
		return audit_sign;
	}
	public void setAudit_sign(String audit_sign) {
		this.audit_sign = audit_sign;
	}
	public String getCheck_sign() {
		return check_sign;
	}
	public void setCheck_sign(String check_sign) {
		this.check_sign = check_sign;
	}
	public String getTotal_qty() {
		return total_qty;
	}
	public void setTotal_qty(String total_qty) {
		this.total_qty = total_qty;
	}
	public String getShipment_sign() {
		return shipment_sign;
	}
	public void setShipment_sign(String shipment_sign) {
		this.shipment_sign = shipment_sign;
	}
	public String getCust_sign() {
		return cust_sign;
	}
	public void setCust_sign(String cust_sign) {
		this.cust_sign = cust_sign;
	}
	public String getEnd_sign() {
		return end_sign;
	}
	public void setEnd_sign(String end_sign) {
		this.end_sign = end_sign;
	}
    
}
