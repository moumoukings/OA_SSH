package com.jboa.dao;

import java.util.List;

import com.jboa.entity.Right;
import com.jboa.entity.Role;
import com.jboa.exception.DAOException;
import com.jboa.exception.HibernateDaoSupportException;

public interface RoleRightDao{
	public List<Right> getRightsByRoleId(int roleId) throws DAOException;
	public List <Role> getRoles (int empRoleId) throws HibernateDaoSupportException;
}