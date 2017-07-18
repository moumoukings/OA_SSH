package com.jboa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jboa.entity.Right;
import com.jboa.entity.Role;
import com.jboa.entity.RoleRight;
import com.jboa.exception.DAOException;
import com.jboa.exception.HibernateDaoSupportException;

@Repository("roleRightDao")
public class RoleRightDaoImpl extends BaseDaoImpl<RoleRight, Integer> implements com.jboa.dao.RoleRightDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Right> getRightsByRoleId(int roleId) throws DAOException {
		String hql="select rr.right from RoleRight rr where rr.role.id=:roleid or rr.role.id=-1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleid", roleId);
		return super.queryListByHql(hql, params);
	}

	@SuppressWarnings("unchecked")
	public List <Role> getRoles (int empRoleId) throws HibernateDaoSupportException{
		String hql = "from Role where id>-1 and id!=3 " ;
		return super.queryListByHql(hql, null);
	}
}
