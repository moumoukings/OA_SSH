package com.jboa.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jboa.dao.EmployeeDao;
import com.jboa.dao.LeaveDao;
import com.jboa.dao.MailDao;
import com.jboa.dao.RoleRightDao;

public class BaseService {
	
	@Autowired
	protected EmployeeDao employeeDao;
	@Autowired
	protected RoleRightDao roleRightDao;
	@Autowired
	protected LeaveDao leaveDao;
	@Autowired
	protected MailDao mailDao;
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());

}
