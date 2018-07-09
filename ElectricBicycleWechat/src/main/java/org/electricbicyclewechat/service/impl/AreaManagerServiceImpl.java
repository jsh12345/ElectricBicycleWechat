package org.electricbicyclewechat.service.impl;

import org.electricbicyclewechat.dao.AreaManagerDao;
import org.electricbicyclewechat.pojo.AreaManager;
import org.electricbicyclewechat.service.AreaManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AreaManagerService")
public class AreaManagerServiceImpl implements AreaManagerService{
	@Autowired
	private AreaManagerDao areaManagerDao;
	
	/**
	 * 检查是否存在该区域经理账号
	 * @return
	 */
	@Override
	public AreaManager checkAreaManagerAcount(AreaManager areaManager)throws Exception{
		return areaManagerDao.checkAreaManagerAcount(areaManager);
	}
	
	/**
	 * 区域经理账号登录
	 * @return
	 */
	@Override
	public int areaManagerLogin(AreaManager areaManager)throws Exception{
		return areaManagerDao.areaManagerLogin(areaManager);
	}
	/**
	 * 插入区域经理信息
	 * @return
	 */
	@Override
	public int insertAreaManager(AreaManager areaManager) throws Exception {
		return areaManagerDao.insertAreaManager(areaManager);
	}
	/**
	 * 检查表中是否已存在该区域经理信息
	 * @return
	 */
	@Override
	public boolean checkAreaManager(AreaManager areaManager)throws Exception {
		int num = areaManagerDao.checkAreaManager(areaManager);
		if(num>0){
			return false;	
		}else{
			return true;
		}
	}
	

}
