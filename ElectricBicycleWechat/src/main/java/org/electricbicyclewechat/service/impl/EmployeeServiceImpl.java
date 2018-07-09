package org.electricbicyclewechat.service.impl;

import org.electricbicyclewechat.dao.EmployeeDao;
import org.electricbicyclewechat.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeDao employeeDao;
	

	
	

}
