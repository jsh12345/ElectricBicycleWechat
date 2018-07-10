package org.electricbicyclewechat.dao;

import java.util.List;

import org.electricbicyclewechat.pojo.Product;



public interface ProductDao {
	/**
	 * 获取所有大类
	 * @return
	 */
	public List<String> searchSort();
	
	/**
	 * 根据大类获取车型名称
	 * @return
	 */
    public List<String> searchMaterialName(String sort);
    /**
     * 根据车名获取规格
     * @param name
     * @return
     */
    public List<String> searchMaterialSpec(String name);
    /**
     * 根据车型名称，规格得到整车的颜色
     * @param code
     * @return
     */
    public List<String> searchColor(String name,String spec);
    /**
     * 根据车名、规格和颜色选择电动车的图片
     * @param color
     * @return
     */
    public String searchPhoto(String name,String spec,String color); 
}
