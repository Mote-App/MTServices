package com.mt.controllers.facebook;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The <code>FacebookProfileController</code> is created by injecting a Facebook object into its constructor.
 * The Facebook object is a reference to Spring Social’s Facebook API binding.
 * 
 * @Controller marks the FacebookProfileController.java POJO class as MVC controller
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class FacebookProfileController {
	
	@Inject
	private ConnectionRepository connectionRepository;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/facebook", method=RequestMethod.GET)
	public String home(ModelMap model) {
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		
		if(connection == null) {
			return "redirect:/connect/facebook";
		}
		
		model.addAttribute("profile", connection.getApi().userOperations().getUserProfile());
		
		return "facebook/profile";
	}
}