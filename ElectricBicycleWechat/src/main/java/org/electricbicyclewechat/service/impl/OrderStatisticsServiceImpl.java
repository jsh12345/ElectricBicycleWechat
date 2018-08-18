package org.electricbicyclewechat.service.impl;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.dao.OrderStatisticsDao;
import org.electricbicyclewechat.pojo.So_master;
import org.electricbicyclewechat.pojo.So_detail;
import org.electricbicyclewechat.pojo.request.so_masterParam.So_masterParam;
import org.electricbicyclewechat.service.OrderStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OrderStatisticsService")
public class OrderStatisticsServiceImpl implements OrderStatisticsService{
	@Autowired
	private OrderStatisticsDao orderStatisticsDao;
	
	/**
	 * 01检查订单表中是否含有此订单信息
	 * @return
	 */
	@Override
	public boolean checkOrder(So_master so_master)throws Exception{
		int num = orderStatisticsDao.checkOrder(so_master);
		if(num>0){
		return false;	
		}else{
			return true;
		}
	}

	/**
	 * 02新增经销商库存信息
	 * @param sellerBalance
	 */
	@Override
	public int insertOrder(So_master so_master)throws Exception {
		return orderStatisticsDao.insertOrder(so_master);
	}
	
	/**
	 * 03删除订单信息
	 * @param sellerBalance
	 */
	@Override
	public int deleteBybill_no(Map<String, Object> map)throws Exception{
		return orderStatisticsDao.deleteBybill_no(map);
	}
	
	/**
	 * 04页面加载查询近期订单信息
	 * @return
	 */
	@Override
	public List<So_master> searchOrderInfoOnload()throws Exception{
		return orderStatisticsDao.searchOrderInfoOnload();
	}
	
	/**
	 * 05查看订单详情
	 * @return
	 */
	@Override
	public So_masterParam OrderDetail(So_master sellerBalance)throws Exception{
		return orderStatisticsDao.OrderDetail(sellerBalance);
	}
	
	/**
	 * 06查询订单信息
	 * @return
	 */
	@Override
	public List<So_master> searchOrderInfo(So_master balanceQueryParam)throws Exception{
		return orderStatisticsDao.searchOrderInfo(balanceQueryParam);
	}
	
	/************************
	 * 07查询经销商订单数量汇总信息
	 * @return
	 */
	@Override
	public List<So_master> searchOrderInfoForNum(So_masterParam balanceQueryParam)throws Exception{
		return orderStatisticsDao.searchOrderInfoForNum(balanceQueryParam);
	}
	
	/**
	 * 08查看每个经销商订单数量小计信息
	 * @return
	 */
	@Override
	public List<So_masterParam> orderNumSum(So_masterParam balanceQueryParam)throws Exception{
		return orderStatisticsDao.orderNumSum(balanceQueryParam);
	}
	
	/**
	 * 09查看每个经销商订单数量总合计信息
	 * @return
	 ************************/
	@Override
	public int orderSumTotal(So_masterParam balanceQueryParam)throws Exception{
		return orderStatisticsDao.orderSumTotal(balanceQueryParam);
	}
	
	/************************
	 * 10查询订单车型汇总信息
	 * @return
	 **/
	@Override
	public List<So_detail> searchOrderInfoForType(So_masterParam balanceQueryParam)throws Exception{
		return orderStatisticsDao.searchOrderInfoForType(balanceQueryParam);
	}
	/**
	 * 11查看经销商订单车型小计信息
	 * @return
	 **/
	@Override
	public List<So_detail> orderTypeSum(So_masterParam balanceQueryParam)throws Exception{
		return orderStatisticsDao.orderTypeSum(balanceQueryParam);
	}
	/**
	 * 09查看每个经销商订单数量总合计信息
	 * @return
	 ************************/
	@Override
	public int carTypeTotal(So_masterParam so_detail)throws Exception{
		return orderStatisticsDao.carTypeTotal(so_detail);
	}
}
