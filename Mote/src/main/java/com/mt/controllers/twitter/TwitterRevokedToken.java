package com.mt.controllers.twitter;

import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The <code>TwitterRevokedToken</code> is ...
 * 
 * @Controller marks the TwitterRevokedToken.java POJO class as MVC controller
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class TwitterRevokedToken {
	
	/**
	 * 
	 */
	@RequestMapping("/twitter/revoked")
	public void simulateExpiredToken() {
		throw new ExpiredAuthorizationException("twitter");
	}
}