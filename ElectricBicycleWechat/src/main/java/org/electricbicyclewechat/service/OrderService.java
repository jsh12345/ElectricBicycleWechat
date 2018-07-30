package org.electricbicyclewechat.service;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.Product;
import org.electricbicyclewechat.pojo.ShoppingCart;
import org.electricbicyclewechat.pojo.SoDetail;
import org.electricbicyclewechat.pojo.SoMaster;

public interface OrderService {
	/**
	 * 获得大类
	 * @return
	 * @throws Exception
	 */
	public List<String> getSort() throws Exception;
	/**
	 * 根据大类获取车名
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<String> getMaterialName(String sort) throws Exception;
	/**
	 * 根据车名获取规格列表
	 * @param material_name
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> searchByName(String material_name) throws Exception;
	
	/**
	 * 根据车名查询对应的规格
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<String> getMaterialSpec(String name) throws Exception;
	/**
	 * 根据车名和规格获取颜色
	 * @param name
	 * @param spec
	 * @return
	 * @throws Exception
	 */
	public List<String> getMaterialColor(String name,String spec) throws Exception;
    //价格
	public String getPrice(String name,String spec) throws Exception;
	
	/**
     * 图片和库存数量
     * @param name
     * @param spec
     * @param photo
     * @return
     * @throws Exception
     */
	public Product getMaterialPhoto(String name,String spec,String color) throws Exception;
	public int insertToCart(ShoppingCart cart) throws Exception;
	public String getColorCode(String desc) throws Exception;
	public String getMaterialCode(String name,String spec) throws Exception;
	public String getMaterialType(String code) throws Exception;
	
	/**
	 * 获取购物车列表
	 * @return
	 * @throws Exception
	 */
	public List<ShoppingCart> getShoppingList(String code) throws Exception;
	/**
	 * 删除商品
	 * @param code
	 * @param name
	 * @param spec
	 * @param color
	 * @return
	 * @throws Exception
	 */
	public int deleteOrder(String code,String material_codeString , String color_code) throws Exception;
    
	public String getCustAddress(String code) throws Exception;
	
	public String getBillNo(Map<String, Object> map) throws Exception;
	
	public boolean submitOrder(String cust_code,Map<String, Object> masterMap, List<Map<String, Object>> detailMapList) throws Exception;

    public List<SoMaster> getOrderList(String cust_code) throws Exception;
    
    public List<SoDetail> getOrderDetail(String comp_id,String bill_no) throws Exception;
     
    public int signOrder(String comp_id,String bill_no) throws Exception;
}
