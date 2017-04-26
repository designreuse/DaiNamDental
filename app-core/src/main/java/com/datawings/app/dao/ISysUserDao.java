package com.datawings.app.dao;

import java.util.List;

import com.datawings.app.filter.UserFilter;
import com.datawings.app.model.SysUser;

public interface ISysUserDao extends IBaseDao<SysUser, Integer>{
	
	List<SysUser> getUser(UserFilter userFilter);

	SysUser findByUsernameLogin(String username);

}
