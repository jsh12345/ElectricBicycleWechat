package org.electricbicyclewechat.service;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.Seller;


public interface SellerService {
	/**
	 * 获取所有的经销商信息
	 * @return
	 */
	public List<Seller> findAllSeller()throws Exception;
	
	/**
	 * 创建经销商信息
	 * @return
	 */
	public int insertSeller(Seller seller)throws Exception;
	
	/**
	 * 检查是否含有此经销商信息
	 * @return
	 */
	public boolean checkSeller(Seller seller)throws Exception;
	
	/**
	 * 获取经销商账号
	 * @return
	 */
	public List<Seller> getSellerInfo(Map<String, Object> map)throws Exception;
	
	/**
	 * 根据代码、名称等查询经销商详细信息
	 * @param seller
	 */
	public Seller findSellerDetail(Map<String, Object> map)throws Exception;
	
}
