package com.jboa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jboa.entity.Right;
import com.jboa.entity.Role;
import com.jboa.service.RoleRightService;

@Service("roleRightService")
public class RoleRightServiceImpl extends BaseService implements RoleRightService{

	@Override
	public List<Right> getRightsByRoleId(int roleId) throws Exception {
		return roleRightDao.getRightsByRoleId(roleId);
	}

	@Override
	public List<Role> getRoles(int empRoleId) throws Exception {
		return roleRightDao.getRoles(empRoleId);
	}

}
