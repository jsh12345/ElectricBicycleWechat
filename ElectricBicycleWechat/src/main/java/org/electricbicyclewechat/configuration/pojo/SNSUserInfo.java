package org.electricbicyclewechat.configuration.pojo;

import java.util.List;

public class SNSUserInfo {
	
	// 用户的唯一标识
    private String openId;
    // 用户昵称
    private String nickname;
    // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private int sex;
    // 国家，如中国为CN
    private String country;
    // 用户个人资料填写的省份
    private String province;
    // 普通用户个人资料填写的城市
    private String city;
    // 用户头像
    private String headImgUrl;
    // 用户特权信息，json 数组
    private List<String> privilegeList;
    //
    private String unionid;
    private int accountKeyid;
    
	public int getAccount_keyid() {
		return accountKeyid;
	}
	public void setAccount_keyid(int accountKeyid) {
		this.accountKeyid = accountKeyid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public List<String> getPrivilegeList() {
		return privilegeList;
	}
	public void setPrivilegeList(List<String> privilegeList) {
		this.privilegeList = privilegeList;
	}
    

}
