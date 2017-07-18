package com.jboa.service;

import java.util.List;

import com.jboa.entity.Right;
import com.jboa.entity.Role;

public interface RoleRightService {
	public List<Right> getRightsByRoleId(int roleId) throws Exception;
	public List<Role> getRoles (int empRoleId) throws Exception;
}
