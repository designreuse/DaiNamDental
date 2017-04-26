package com.datawings.app.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
public class SecurityUtil {
	private static SecureRandom random = new SecureRandom();

	public static String nextToken() {
		return new BigInteger(130, random).toString(32);
	}

	public static String nextToken(int length) {
		return new BigInteger(130, random).toString(length);
	}

	public static String getMD5(String input) {
		if (StringUtils.isEmpty(input))
			return "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/*public static Collection<GrantedAuthority> toGrantedAuthorities(Collection<SysRole> roles){
		Collection<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		for (SysRole r : roles){
			gas.add(new SimpleGrantedAuthority(r.getAuthority()));
		}
		return gas;
	}*/
	
	public static Collection<GrantedAuthority> toGrantedAuthorities(String role){
		Collection<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		gas.add(new SimpleGrantedAuthority(role));
		return gas;
	}

	public static String invalidateSession(HttpSession httpSession){
		if (httpSession != null)
			httpSession.invalidate();
		return "";
	}

	public static String invalidateSession(HttpServletRequest request){
		HttpSession httpSession = request.getSession(false);
		if (httpSession != null){
			Authentication au = SecurityContextHolder.getContext().getAuthentication();
			if (!(au instanceof UsernamePasswordAuthenticationToken && au.isAuthenticated())){
				httpSession.invalidate();
				SecurityContextHolder.clearContext();
			}
		}
		return "OK";
	}
}
