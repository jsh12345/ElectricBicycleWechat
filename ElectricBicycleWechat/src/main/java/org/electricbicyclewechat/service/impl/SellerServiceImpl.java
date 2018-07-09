package org.electricbicyclewechat.service.impl;

import java.util.List;
import java.util.Map;

import org.electricbicyclewechat.dao.SellerDao;
import org.electricbicyclewechat.pojo.Seller;
import org.electricbicyclewechat.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SellerService")
public class SellerServiceImpl implements SellerService{
	@Autowired
	private SellerDao sellerDao;

	@Override
	public List<Seller> findAllSeller() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertSeller(Seller seller) throws Exception {
		// TODO Auto-generated method stub
		return sellerDao.insertSeller(seller);
	}

	@Override
	public boolean checkSeller(Seller seller) throws Exception {
		// TODO Auto-generated method stub
		int num = sellerDao.checkSeller(seller);
		if (num > 0) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 获取经销商账号
	 * @return
	 */
	@Override
	public List<Seller> getSellerInfo(Map<String, Object> map)throws Exception{
		return sellerDao.getSellerInfo(map);
	}

	/**
	 * 根据代码、名称等查询经销商详细信息
	 * @param seller
	 */
	@Override
	public Seller findSellerDetail(Map<String, Object> map) throws Exception {
		return sellerDao.findSellerDetail(map);
	}
	

}
