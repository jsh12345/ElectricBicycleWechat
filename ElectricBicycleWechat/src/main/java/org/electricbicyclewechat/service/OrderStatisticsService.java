package org.electricbicyclewechat.service;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.So_detail;
import org.electricbicyclewechat.pojo.So_master;
import org.electricbicyclewechat.pojo.request.so_masterParam.So_masterParam;

/**
 * 
 * @author 
 *
 */
public interface OrderStatisticsService {

	/**
	 * 01检查订单表中是否含有此订单信息
	 * @return
	 */
	public boolean checkOrder(So_master so_master)throws Exception;
	
	/**
	 * 02新增订单信息
	 * @param so_maste
	 */
	
	public int insertOrder(So_master so_master)throws Exception;
	/**
	 * 03删除订单信息
	 * @param so_maste
	 */
	public int deleteBybill_no(Map<String, Object> map)throws Exception;
	
	/**
	 * 04页面加载查询近期订单信息
	 * @return
	 */
	public List<So_master> searchOrderInfoOnload()throws Exception;
	
	/**
	 * 05查看订单详情
	 * @return
	 */
	public So_masterParam OrderDetail(So_master so_master)throws Exception;
	
	/**
	 * 06查询订单信息
	 * @return
	 */
	public List<So_master> searchOrderInfo(So_master so_maste)throws Exception;
	
	/************************
	 * 07查询经销商订单数量汇总信息
	 * @return
	 */
	public List<So_master> searchOrderInfoForNum(So_masterParam so_maste)throws Exception;
	
	/**
	 * 08查看每个经销商订单数量小计信息
	 * @return
	 */
	public List<So_masterParam> orderNumSum(So_masterParam so_maste)throws Exception;
	
	/**
	 *  09查看每个经销商订单数量总合计信息
	 * @return
	 ************************/
	public int orderSumTotal(So_masterParam so_maste)throws Exception;
	
	/************************
	 * 10查询订单车型汇总信息
	 * @return
	 **/
	public List<So_detail> searchOrderInfoForType(So_masterParam so_detail)throws Exception;
	/**
	 * 11查看经销商订单车型小计信息
	 * @return
	 **/
	public List<So_detail> orderTypeSum(So_masterParam so_detail)throws Exception;
	/**
	 * 12查看经销商订单车型总计信息
	 * @return
	 ***********************/
	public int carTypeTotal(So_masterParam so_detail)throws Exception;
	
}
