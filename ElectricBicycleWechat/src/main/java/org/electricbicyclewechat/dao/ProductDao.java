package org.electricbicyclewechat.dao;

import java.util.List;
import java.util.Map;
import org.electricbicyclewechat.pojo.Product;
import org.electricbicyclewechat.pojo.ShoppingCart;
import org.electricbicyclewechat.pojo.SoDetail;
import org.electricbicyclewechat.pojo.SoMaster;

public interface ProductDao {
	/**
	 * 获取所有大类
	 * @return
	 */
	public List<String> searchSort();
	
	/**
	 * 根据大类获取车型名称
	 * @param sort
	 * @return
	 */
    public List<String> searchMaterialName(String sort);
    /**
     * 根据车名获取不同规格的列表
     * @param material_name
     * @return
     */
    public List<Map<String, Object>> searchByName(String material_name);
    
    /**
     * 根据车名获取规格
     * @param name
     * @return
     */
    public List<String> searchMaterialSpec(String name);
    /**
     * 根据车型名称，规格得到整车的颜色
     * @param name
     * @param spec
     * @return
     */
    public List<String> searchColor(String name,String spec);
    
    public String getPrice(String name,String spec);
    /**
     * 根据车名、规格和颜色显示电动车的图片和库存数量
     * @param name
     * @param spec
     * @param color
     * @return
     */
    public Product searchPhoto(String name,String spec,String color); 
    /**
     * 加入购物车
     * @param cart
     * @return
     */
    public int insertToCart(ShoppingCart cart);     
    public String getMaterialCode(String name,String spec);
    public String getColorCode(String desc);
    public String getMaterialType(String code);
    
    /**
     * 根据经销商账号获取购物车列表
     * @return
     */
    public List<ShoppingCart> getShoppingList(String code);
    /**
     * 删除购物车里所选择的电动车
     * @param code
     * @param name
     * @param spec
     * @param color
     * @return
     */
    public int deleteOrder(String code,String material_code,String color_code);
    /**
     * 清空购物车
     * @param code
     * @return
     */
    public int dropOrder(String code);
    /**
     * 获取经销商的送货地址
     * @param code
     * @return
     */
    public String getCustAddress(String code);
    
    /**
     * 根据某些参数获取订单号
     * @param map
     * @return
     */
    public String getBillNo(Map<String, Object> map);
    
    //在订单主表中插入记录
    public int insertToMaster(Map<String, Object> masterMap);
    
    //在订单明细表中插入记录
    public int insertToDetail(List<Map<String, Object>> detailMapList);
    //获取订单列表
    public List<SoMaster> getOrderList(String cust_code);
    //获取对应订单的详细信息
    public List<SoDetail> getOrderDetail(String comp_id,String bill_no);
    
    //签收订单
    public int signOrder(String comp_id , String bill_no);
}
