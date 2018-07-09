package org.electricbicyclewechat.dao;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.SellerSale;
import org.electricbicyclewechat.pojo.request.saleQueryParam.SaleQueryParam;

/**
 * 经销商销售信息表
 * @author 
 *
 */
public interface SellerSaleDao {
	/**
	 * 获取所有的经销商销售信息
	 * @return
	 */
	public List<SellerSale> findAllSellerSale();
	
	/**
	 * 系统保存销售信息
	 * @return
	 */
	public int saveSellInfo(SellerSale sellerSale);
	
	/**
	 * 页面加载查询近期销售信息
	 * @return
	 */
	public List<SellerSale> searchPCInfoOnload(Map<String, Object> map);
	
	/**
	 * 查看销售详情
	 * @return
	 */
	public SaleQueryParam saleQueryDetail(SellerSale sellerSale);
	
	/**
	 * 查询销售信息
	 * @return
	 */
	public List<SellerSale> searchPCInfo(SaleQueryParam saleQueryParam);
	
	/**
	 * 查询车型排行榜信息
	 * @return
	 */
	public List<SaleQueryParam> salesRankings(SaleQueryParam saleQueryParam);
	
	/**
	 * 用于判断该电瓶车是否已经入库
	 * @return
	 */
	public String checkIfSale(Map<String, Object> map);
	

}
