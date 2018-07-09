package org.electricbicyclewechat.service.impl;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.dao.SellerBalanceDao;
import org.electricbicyclewechat.pojo.SellerBalance;
import org.electricbicyclewechat.pojo.request.balanceQueryParam.BalanceQueryParam;
import org.electricbicyclewechat.pojo.request.invoicingReportParam.InvoicingReportParam;
import org.electricbicyclewechat.service.SellerBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("SellerBalanceService")
public class SellerBalanceServiceImpl implements SellerBalanceService{
	@Autowired
	private SellerBalanceDao sellerBalanceDao;
	
	/**
	 * 检查库存表中是否含有此库存信息
	 * @return
	 */
	@Override
	public boolean checkSellerBalance(SellerBalance sellerBalance)throws Exception{
		int num = sellerBalanceDao.checkSellerBalance(sellerBalance);
		if(num>0){
		return false;	
		}else{
			return true;
		}
	}

	/**
	 * 新增经销商库存信息
	 * @param sellerBalance
	 */
	@Override
	public int insertSellerBalance(SellerBalance sellerBalance)throws Exception {
		return sellerBalanceDao.insertSellerBalance(sellerBalance);
	}
	
	/**
	 * 根据车架号查找电瓶车信息
	 * @param sellerBalance
	 */
	@Override
	public SellerBalance findBicycleByFrameCode(Map<String, Object> map)throws Exception{
		return sellerBalanceDao.findBicycleByFrameCode(map);
	}
	
	/**
	 * 根据条形码查找电瓶车信息
	 * @param sellerBalance
	 */
	@Override
	public SellerBalance findBicycleByBarCode(Map<String, Object> map)throws Exception{
		return sellerBalanceDao.findBicycleByBarCode(map);
	}
	
	/**
	 * 删除库存信息
	 * @param sellerBalance
	 */
	@Override
	public int deleteBybarCode(Map<String, Object> map)throws Exception{
		return sellerBalanceDao.deleteBybarCode(map);
	}
	
	/**
	 * 页面加载查询近期库存信息
	 * @return
	 */
	@Override
	public List<SellerBalance> searchBalanceInfoOnload(Map<String, Object> map)throws Exception{
		return sellerBalanceDao.searchBalanceInfoOnload(map);
	}
	
	/**
	 * 查看库存详情
	 * @return
	 */
	@Override
	public BalanceQueryParam balanceQueryDetail(SellerBalance sellerBalance)throws Exception{
		return sellerBalanceDao.balanceQueryDetail(sellerBalance);
	}
	
	/**
	 * 查询库存信息
	 * @return
	 */
	@Override
	public List<SellerBalance> searchBalanceInfo(BalanceQueryParam balanceQueryParam)throws Exception{
		return sellerBalanceDao.searchBalanceInfo(balanceQueryParam);
	}
	
	/**
	 * 查询库存信息
	 * @return
	 */
	@Override
	public List<SellerBalance> searchBalanceInfoForDetail(BalanceQueryParam balanceQueryParam)throws Exception{
		return sellerBalanceDao.searchBalanceInfoForDetail(balanceQueryParam);
	}
	
	/**
	 * 查询库存汇总信息
	 * @return
	 */
	@Override
	public List<BalanceQueryParam> searchBalanceInfoForSum(BalanceQueryParam balanceQueryParam)throws Exception{
		return sellerBalanceDao.searchBalanceInfoForSum(balanceQueryParam);
	}
	
	/**
	 * 查看库存汇总每种车型小计信息
	 * @return
	 */
	@Override
	public List<BalanceQueryParam> balanceSumSubtotal(BalanceQueryParam balanceQueryParam)throws Exception{
		return sellerBalanceDao.balanceSumSubtotal(balanceQueryParam);
	}
	
	/**
	 * 查看库存汇总每种车型总合计信息
	 * @return
	 */
	@Override
	public int balanceSumTotal(BalanceQueryParam balanceQueryParam)throws Exception{
		return sellerBalanceDao.balanceSumTotal(balanceQueryParam);
	}
	
	/**
	 * 进销存报表查询
	 * @param sellerBalance
	 */
	@Override
	public List<InvoicingReportParam> searchForInvoicing(InvoicingReportParam invoicingReportParam)throws Exception{
		return sellerBalanceDao.searchForInvoicing(invoicingReportParam);
	}
	

}
