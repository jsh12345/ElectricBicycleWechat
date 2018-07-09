package org.electricbicyclewechat.service;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.SellerBalance;
import org.electricbicyclewechat.pojo.request.balanceQueryParam.BalanceQueryParam;
import org.electricbicyclewechat.pojo.request.invoicingReportParam.InvoicingReportParam;

/**
 * 
 * @author 
 *
 */
public interface SellerBalanceService {
	
	
	/**
	 * 新增经销商库存信息
	 * @param sellerBalance
	 */
	public int insertSellerBalance(SellerBalance sellerBalance)throws Exception;
	
	/**
	 * 检查库存表中是否含有此库存信息
	 * @return
	 */
	public boolean checkSellerBalance(SellerBalance sellerBalance)throws Exception;
	
	/**
	 * 根据车架号查找电瓶车信息
	 * @param sellerBalance
	 */
	public SellerBalance findBicycleByFrameCode(Map<String, Object> map)throws Exception;
	
	/**
	 * 根据条形码查找电瓶车信息
	 * @param sellerBalance
	 */
	public SellerBalance findBicycleByBarCode(Map<String, Object> map)throws Exception;
	
	/**
	 * 删除库存信息
	 * @param sellerBalance
	 */
	public int deleteBybarCode(Map<String, Object> map)throws Exception;
	
	/**
	 * 页面加载查询近期库存信息
	 * @return
	 */
	public List<SellerBalance> searchBalanceInfoOnload(Map<String, Object> map)throws Exception;
	
	/**
	 * 查看库存详情
	 * @return
	 */
	public BalanceQueryParam balanceQueryDetail(SellerBalance sellerBalance)throws Exception;
	
	/**
	 * 查询库存信息
	 * @return
	 */
	public List<SellerBalance> searchBalanceInfo(BalanceQueryParam balanceQueryParam)throws Exception;
	
	/**
	 * 查询库存信息
	 * @return
	 */
	public List<SellerBalance> searchBalanceInfoForDetail(BalanceQueryParam balanceQueryParam)throws Exception;
	
	/**
	 * 查询库存汇总信息
	 * @return
	 */
	public List<BalanceQueryParam> searchBalanceInfoForSum(BalanceQueryParam balanceQueryParam)throws Exception;
	
	/**
	 * 查看库存汇总每种车型小计信息
	 * @return
	 */
	public List<BalanceQueryParam> balanceSumSubtotal(BalanceQueryParam balanceQueryParam)throws Exception;
	
	/**
	 * 查看库存汇总每种车型总合计信息
	 * @return
	 */
	public int balanceSumTotal(BalanceQueryParam balanceQueryParam)throws Exception;
	
	/**
	 * 进销存报表查询
	 * @param sellerBalance
	 */
	public List<InvoicingReportParam> searchForInvoicing(InvoicingReportParam invoicingReportParam)throws Exception;

}
