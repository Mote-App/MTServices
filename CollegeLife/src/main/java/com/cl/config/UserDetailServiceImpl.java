package com.cl.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cl.models.User;
import com.cl.models.dao.UserDao;

public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	UserDao _userDao;
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
	
		User user = _userDao.getUserbyName(userName);
		
		if(user == null){
			throw new UsernameNotFoundException("Username " + userName + " not found");
		}
		
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
	            user.getUserName(), user.getPassword(), true, true, true, true, getGrantedAuthorities(userName));
		
		return userDetails;
	}	
	
	private List<GrantedAuthority> getGrantedAuthorities(String userName){
	
		
		List<GrantedAuthority> GA = new ArrayList<GrantedAuthority>();
		
		if(userName.equals("cladmin")){

			GA.add(new RoleAdmin());
			GA.add(new RoleBasic());
		}else{
			GA.add(new RoleBasic());
		}
		
		return GA;
	}
	
	class RoleAdmin implements GrantedAuthority{

		@Override
		public String getAuthority() {
			return "ROLE_ADMIN";
		}
		
		
	}

	class RoleBasic implements GrantedAuthority{

		@Override
		public String getAuthority() {
			return "ROLE_BASIC";
		}
		
		
	}

}
