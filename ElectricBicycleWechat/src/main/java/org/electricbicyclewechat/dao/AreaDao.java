package org.electricbicyclewechat.dao;

import java.util.List;
import org.electricbicyclewechat.pojo.Area;

/**
 * 区域信息
 * @author 
 *
 */
public interface AreaDao {
	/**
	 * 获取所有的区域信息
	 * @return
	 */
	public List<Area> findAllArea();
	/**
	 * 检查区域表中是否含有此区域信息
	 * @return
	 */
	public int checkArea(Area area);
	/**
	 * 新增区域信息
	 * @param area
	 */
	public int insertArea(Area area);	
	
	/**
	 * 判断是否为父区域代码
	 * @return
	 */
	public Area checkIfParentArea(String areaCode);
	
	/**
	 * 获取父区域代码
	 * @return
	 */
	public Area getParentArea(String areaCode);
	
	/**
	 * 获取子集区域代码
	 * @return
	 */
	public Area getArea(String areaCode);
	
	/**
	 * 获取子集区域代码
	 * @return
	 */
	public List<Area> getAreaInfo(String areaCode);

}
