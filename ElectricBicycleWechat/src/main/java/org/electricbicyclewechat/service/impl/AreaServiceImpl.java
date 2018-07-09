package org.electricbicyclewechat.service.impl;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.dao.AreaDao;
import org.electricbicyclewechat.dao.SellerDao;
import org.electricbicyclewechat.pojo.Area;
import org.electricbicyclewechat.pojo.Seller;
import org.electricbicyclewechat.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AreaService")
public class AreaServiceImpl implements AreaService{
	@Autowired
	private AreaDao areaDao;
	@Autowired
	private SellerDao sellerDao;

	@Override
	public boolean checkArea(Area area) throws Exception {
		int num = areaDao.checkArea(area);
		if(num>0){
			return false;	
		}else{
			return true;
		}
	}

	@Override
	public int insertArea(Area area) throws Exception {
		return areaDao.insertArea(area);
	}
	
	/**
	 * 查询区域经理区域信息
	 * @param area
	 */
	@Override
	public List<Area> searchAreaCode(Map<String, Object> map)throws Exception{
		if(map!=null){
			//查询该区域经理的区域信息
			String areaManagerCode = (String) map.get("areaManagerCode");
			Seller seller = sellerDao.searchAreaCode(areaManagerCode);
			if(seller==null){
				return null;
			}else{
				//判断是否为父区域
				String areaCode = seller.getAreaCode();
				Area area = areaDao.checkIfParentArea(areaCode);
				String parentCode = null;
				if(area==null){//不是父区域
					//获取父区域
					Area parentArea = areaDao.getParentArea(areaCode);
					parentCode = parentArea.getParentAreaCode();
				}else{
					parentCode = area.getAreaCode();
				}
				//获取区域信息
				List<Area> list = areaDao.getAreaInfo(parentCode);
				return list;
			}
		}
		return null;
	}
	
	/**
	 * 判断是否为父区域代码
	 * @param area
	 */
	@Override
	public Area checkIfParentArea(String areaCode)throws Exception{
		return areaDao.checkIfParentArea(areaCode);
	}
	

}
