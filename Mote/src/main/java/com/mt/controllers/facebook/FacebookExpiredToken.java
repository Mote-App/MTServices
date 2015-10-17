package com.mt.controllers.facebook;

import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The <code>FacebookExpiredToken</code> is ...
 * 
 * @Controller marks the FacebookExpiredToken.java POJO class as MVC controller
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class FacebookExpiredToken {
	
	/**
	 * 
	 */
	@RequestMapping("/facebook/expired")
	public void simulateExpiredToken() {
		throw new ExpiredAuthorizationException("facebook");
	}
}