package org.electricbicyclewechat.dao;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.Seller;

/**
 * 经销商基本信息表
 * @author 
 *
 */
public interface SellerDao {
	/**
	 * 获取所有的经销商信息
	 * @return
	 */
	public List<Seller> findAllSeller();
	
	/**
	 * 创建经销商基本信息
	 * @return
	 */
	public int insertSeller(Seller seller);
	
	/**
	 * 检查是否含有此经销商信息
	 * @return
	 */
	public int checkSeller(Seller seller);
	
	/**
	 * 登录时获取名称
	 * @return
	 */
	public Seller searchSellerNameByAccount(String sellerCode);
	
	/**
	 * 获取经销商账号
	 * @return
	 */
	public List<Seller> getSellerInfo(Map<String, Object> map);
	
	/**
	 * 查询区域经理区域信息
	 * @return
	 */
	public Seller searchAreaCode(String areaManagerCode);
	
	/**
	 * 根据代码、名称等查询经销商详细信息
	 * @param seller
	 */
	public Seller findSellerDetail(Map<String, Object> map);
	
}
