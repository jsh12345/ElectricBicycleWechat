package org.electricbicyclewechat.dao;

import java.util.List;

import org.electricbicyclewechat.pojo.Employee;

/**
 * 员工信息
 * @author 
 *
 */
public interface EmployeeDao {
	
	/**
	 * 登录时获取名称
	 * @return
	 */
	public Employee searchEPNameByAccount(String employeeCode);
	
	/**
	 * 获取所有的员工信息
	 * @return
	 */
	public List<Employee> findAllEmployee();
		

}
