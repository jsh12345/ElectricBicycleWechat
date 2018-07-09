package org.electricbicyclewechat.service;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.pojo.Area;


public interface AreaService {
	/**
	 * 检查区域表中是否含有此区域信息
	 * @return
	 */
	public boolean checkArea(Area area) throws Exception;
	/**
	 * 新增区域信息
	 * @param area
	 */
	public int insertArea(Area area)throws Exception;
	
	/**
	 * 查询区域经理区域信息
	 * @param area
	 */
	public List<Area> searchAreaCode(Map<String, Object> map)throws Exception;
	
	/**
	 * 判断是否为父区域代码
	 * @param area
	 */
	public Area checkIfParentArea(String areaCode)throws Exception;
	
}
