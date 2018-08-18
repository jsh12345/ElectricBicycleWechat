package org.electricbicyclewechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.electricbicyclewechat.dao.EmployeeDao;
import org.electricbicyclewechat.dao.LoginAccountDao;
import org.electricbicyclewechat.dao.SellerDao;
import org.electricbicyclewechat.pojo.Employee;
import org.electricbicyclewechat.pojo.LoginAccount;
import org.electricbicyclewechat.pojo.Seller;
import org.electricbicyclewechat.service.LoginAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LoginAccountService")
public class LoginAccountServiceImpl implements LoginAccountService{
	@Autowired
	private LoginAccountDao loginAccountDao;
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private EmployeeDao employeeDao;
	
	/**
	 * 登录时检查账户
	 * @return
	 */
	@Override
	public Map<String,Object> checkifAccount(LoginAccount loginAccount)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		if(loginAccount!=null){
			//检查是否存在该账户
			LoginAccount account = loginAccountDao.checkifAccount(loginAccount);
			if(account!=null){//存在该账户
				//查询该账户对应的名称
				String type = account.getType();
				String accountCode = account.getAccountCode();
				if("1".equals(type)){//经销商
					Seller seller = sellerDao.searchSellerNameByAccount(accountCode);
					String name = seller.getSellerName();
					map.put("name", name);
				}else{
					Employee employee = employeeDao.searchEPNameByAccount(accountCode);
					String name = employee.getEmployeeName();
					map.put("name", name);
				}
				map.put("code", "0");
				map.put("msg", "查询成功！");
				return map;
			}else{//不存在该账户
				map.put("code", "2");
				map.put("msg", "不存在该账户！");
				return map;
			}
		}
		map.put("code", "1");
		map.put("msg", "查询错误！");
		return map;
	}
	
	/**
	 * 登录
	 * @return
	 */
	@Override
	public LoginAccount accountLogin(LoginAccount loginAccount)throws Exception{
		if(loginAccount!=null){
			return loginAccountDao.accountLogin(loginAccount);
		}
		return null;
	}

	/**
	 * 更改密码
	 * @return
	 */
	@Override
	public boolean updatePassword(LoginAccount loginAccount) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (loginAccount!=null) {
			int n = loginAccountDao.updatePassword(loginAccount);
			if (n>0) {
				flag = true;
			}else {
				flag = false;
			}
		}
		return flag;
	}
/**
 * 判断是否具有内勤和财务的权限
 */
	@Override
	public String ifAuditOrCheck(String login_id) throws Exception {
		// TODO Auto-generated method stub
		int auditflag = loginAccountDao.ifAudit(login_id);//审核发货通知单
		int auditOrderFlag = loginAccountDao.ifAuditOrder(login_id);//审核订单
		int checkflag = loginAccountDao.ifCheckOrder(login_id);//确认订单
	    if(auditflag > 0 && auditOrderFlag > 0 && checkflag > 0){//财务+内勤
	    	return "everybody";
	    }else if(auditflag > 0 && auditOrderFlag > 0){//财务(一审+二审)
	    	return "everyaudit";
	    }else if(auditOrderFlag > 0 && checkflag > 0){//内勤+一审财务
	    	return "order"; 
	    }else if(auditflag > 0 && checkflag > 0){//内勤+二审财务
	    	return "checkaudit";
	    }else if(auditflag > 0){
	    	return "audit";
	    }else if(auditOrderFlag > 0){
	    	return "auditOrder";
	    }else if(checkflag > 0){
	    	return "check";
	    }else{
	    	return "common";
	    }
	}
}
