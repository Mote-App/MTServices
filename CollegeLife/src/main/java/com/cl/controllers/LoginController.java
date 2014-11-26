package com.cl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import token.TokenUtil;
import views.TokenDto;

import com.cl.config.UserDetailServiceImpl;
import com.cl.models.User;
import com.cl.models.dao.UserDao;

@Controller
public class LoginController {

	@Autowired
	private UserDao _userDao;
	
	 @RequestMapping(value="/login", method = RequestMethod.GET, produces="application/json")
	 @ResponseBody
	 public TokenDto login(String userName, String password){
		 
		 UserDetailServiceImpl service = new UserDetailServiceImpl();
		 
		 UserDetails userDetail = service.loadUserByUsername(userName);
		 
		 String token = TokenUtil.createToken(userDetail);
		 
		 User user = _userDao.getUserbyName(userName);
		 
		 TokenDto dto = new TokenDto(token, user.getId());
		 
		 return dto;
	 }
	
}
