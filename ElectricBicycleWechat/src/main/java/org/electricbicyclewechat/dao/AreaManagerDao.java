package org.electricbicyclewechat.dao;

import java.util.List;
import org.electricbicyclewechat.pojo.AreaManager;

/**
 * 区域经理信息
 * @author 
 *
 */
public interface AreaManagerDao {
	/**
	 * 获取所有的区域经理信息
	 * @return
	 */
	public List<AreaManager> findAllAreaManager();
	
	/**
	 * 检查表中是否已存在该区域经理信息
	 * @return
	 */
	public int checkAreaManager(AreaManager areaManager);
	
	/**
	 * 检查是否存在该区域经理账号
	 * @return
	 */
	public AreaManager checkAreaManagerAcount(AreaManager areaManager);
	
	/**
	 * 区域经理账号登录
	 * @return
	 */
	public int areaManagerLogin(AreaManager areaManager);
	
	/**
	 * 新增区域经理信息
	 * @param  areaManager
	 */
	public int insertAreaManager(AreaManager areaManager);

}
