package com.datawings.app.auth;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.datawings.app.model.SysUser;
import com.datawings.app.service.impl.SysUserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final Log log = LogFactory.getLog(CustomAuthenticationProvider.class);
	
	private SysUserService sysUserService;
	
	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		String username = arg0.getName();
		String password = (String) arg0.getCredentials();
		log.info("authentication : " + username + " / ******");
		
		SysUser user = sysUserService.findByUsernameLogin(username);
		if (user == null)
			throw new BadCredentialsException("Username not found.");
		if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
        }

		Collection<GrantedAuthority> gas = SecurityUtil.toGrantedAuthorities(user.getRole());
		if (gas == null || gas.size() == 0)
			throw new BadCredentialsException("User's not permission");

		return new UsernamePasswordAuthenticationToken(user, password, gas);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	
}
