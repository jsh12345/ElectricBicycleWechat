package org.electricbicyclewechat.service;

import org.electricbicyclewechat.pojo.AreaManager;


public interface AreaManagerService {
	
	/**
	 * 检查表中是否已存在该区域经理信息
	 * @return
	 */
	public boolean checkAreaManager(AreaManager areaManager)throws Exception;
	
	/**
	 * 检查是否存在该区域经理账号
	 * @return
	 */
	public AreaManager checkAreaManagerAcount(AreaManager areaManager)throws Exception;
	
	/**
	 * 区域经理账号登录
	 * @return
	 */
	public int areaManagerLogin(AreaManager areaManager)throws Exception;
	
	/**
	 * 新增区域经理信息
	 * @param areaManager
	 */
	public int insertAreaManager(AreaManager areaManager)throws Exception;
	
}
