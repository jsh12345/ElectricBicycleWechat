package org.electricbicyclewechat.service.impl;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.dao.SellerSaleDao;
import org.electricbicyclewechat.pojo.SellerSale;
import org.electricbicyclewechat.pojo.request.saleQueryParam.SaleQueryParam;
import org.electricbicyclewechat.service.SellerSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SellerSaleService")
public class SellerSaleServiceImpl implements SellerSaleService{
	@Autowired
	private SellerSaleDao sellerSaleDao;
	
	/**
	 * 系统保存销售信息
	 * @return
	 */
	@Override
	public int saveSellInfo(SellerSale sellerSale)throws Exception{
		return sellerSaleDao.saveSellInfo(sellerSale);
	}
	
	/**
	 * 页面加载查询近期销售信息
	 * @return
	 */
	@Override
	public List<SellerSale> searchPCInfoOnload(Map<String, Object> map)throws Exception{
		return sellerSaleDao.searchPCInfoOnload(map);
	}
	
	/**
	 * 查看销售详情
	 * @return
	 */
	@Override
	public SaleQueryParam saleQueryDetail(SellerSale sellerSale)throws Exception{
		return sellerSaleDao.saleQueryDetail(sellerSale);
	}
	
	/**
	 * 查询销售信息
	 * @return
	 */
	@Override
	public List<SellerSale> searchPCInfo(SaleQueryParam saleQueryParam)throws Exception{
		return sellerSaleDao.searchPCInfo(saleQueryParam);
	}
	
	/**
	 * 查询车型排行榜信息
	 * @return
	 */
	@Override
	public List<SaleQueryParam> salesRankings(SaleQueryParam saleQueryParam)throws Exception{
		return sellerSaleDao.salesRankings(saleQueryParam);
	}
	
	/**
	 * 用于判断该电瓶车是否已经入库
	 * @return
	 */
	@Override
	public String checkIfSale(Map<String, Object> map)throws Exception{
		return sellerSaleDao.checkIfSale(map);
	}
	

}
