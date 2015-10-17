package com.mt.controllers.facebook;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The <code>FacebookFriendsController</code> is created by injecting a Facebook object into its constructor.
 * The Facebook object is a reference to Spring Social’s Facebook API binding.
 * 
 * @Controller marks the FacebookFriendsController.java POJO class as MVC controller
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class FacebookFriendsController {
	private final Facebook facebook;
	
	@Inject
	public FacebookFriendsController(Facebook facebook) {
		this.facebook = facebook;
	}
	
	/**
	 * Do Facebook's aggregation
	 * 
	 * 1. HTTP request http://54.149.27.205:8080/facebook/aggregation arrives at DispatcherServlet (traffic cop), which handles
	 *    routing request to appropriated controller class using HandlerMapping to determine appropriate controller class.
	 * 2. DispatcherServlet consults HandlerMapping (in this cases annotations "users/profile", GET, JSON) to determine
	 *    appropriate Controller to process the HTTP request http://54.149.27.205:8080/facebook/aggregation
	 * 3. Controller class executes and delegates business calls to objects in the model (in this case ??)
	 * 4. Controller method returns ModelAndView object
	 *    <> ModelMap.java is an object in the Session that is made available by the Spring Framework in view.  It only needs
	 *       to appear in the controller method's parameter list if controller method prepares data for view.  You set the
	 *       data to be available for view by using the ModelMap.java method addAttribute("<key>", <valueObject>);
	 *    <> The String value return by the controller method is mapped to a view page; in other words, return String value
	 *       representing the view to use.
	 *    In conclusion, the ModelAndView constructed by Spring internally contains data for view (model) and information
	 *    on which view to display.
	 * 5. DispatcherServlet uses ViewResolver and information in ModelAndView to select appropriate view
	 * 6. DispatcherServlet dispatches request processing to selected view to then send an HTTP response to the client?
	 * 
	 * @RequestMapping allows URL template to be defined
	 * <> value="users/profile" defines how URLs are parameterized and marks class as controller for URL pattern.
	 * <> method = RequestMethod.GET defines which HTTP verb(s) is accepted  HTTP verb describes action requested on resource GET: fetch
	 * <> produces="application/json") defines which type of data to return to AJAX clients, in this case return JSON.
	 *    It means that this controller method only processes HTTP request with JSON header set.  HTTP request header has a
	 *    field indicating type of data request requires, in this case JSON. Spring then generates appropriate data format for
	 *    HTTP response based on configured method handler.
	 * 
	 * @ResponseBody defines HTTP response will contain JSON-formatted object.  Annotation allows controller method return
	 * value forms content of response; object(s) returned from controller will be converted to JSON or XML, in this case JSON
	 * 
	 * Spring uses open-source Jackson JSON mapper, converter to convert from Java object to JSON and vice versa.  It maps
	 * Java object properties to JSON string.
	 * 
	 * Method can have any name.
	 * 
	 * @param profileId
	 * @return
	 */
	@RequestMapping(value="/facebook/aggregation", method=RequestMethod.GET)
	@ResponseBody
	public String facebookAggregation(ModelMap model) {
		/*
		 * Check whether the user has authorized the application to access the user’s Facebook data.
		 * If not, the user is redirected to ConnectController with the option to kick off the authorization process.
		 * If the user has authorized the application to access Facebook data, the application fetches the user’s profile
		 * as well as several of the most recent entries in the user’s home feed. The data is placed into the model to be
		 * displayed by the view identified as "friend_feed".
		 */
		if(!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}
		
		model.addAttribute(facebook.userOperations().getUserProfile());
        model.addAttribute("friendFeed", "<friendFeedPost>");
		
		return "friend_feed";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/facebook/friends", method=RequestMethod.GET)
	public String showFeed(ModelMap model) {
		if(!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}
		
		model.addAttribute("friends", facebook.friendOperations().getFriendProfiles());
		
		return "facebook/friends";
	}
}