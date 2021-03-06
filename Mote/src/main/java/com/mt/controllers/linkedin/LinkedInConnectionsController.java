package com.mt.controllers.linkedin;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.NetworkStatistics;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The <code>LinkedInConnectionsController</code> is created by injecting a LinkedIn object into its constructor.
 * The LinkedIn object is a reference to Spring Social’s LinkedIn API binding.
 * 
 * @Controller marks the LinkedInConnectionsController.java POJO class as MVC controller
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class LinkedInConnectionsController {
	private final LinkedIn linkedIn;
	private ConnectionRepository connectionRepository;
	
	@Inject
	public LinkedInConnectionsController(LinkedIn linkedIn, ConnectionRepository connectionRepository) {
		this.linkedIn = linkedIn;
		this.connectionRepository = connectionRepository;
	}
	
	/**
	 * Do LinkedIn's aggregation
	 * 
	 * 1. HTTP request http://54.149.27.205:8080/linkedin/aggregation arrives at DispatcherServlet (traffic cop), which handles
	 *    routing request to appropriated controller class using HandlerMapping to determine appropriate controller class.
	 * 2. DispatcherServlet consults HandlerMapping (in this cases annotations "users/profile", GET, JSON) to determine
	 *    appropriate Controller to process the HTTP request http://54.149.27.205:8080/linkedin/aggregation
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
	@RequestMapping(value="/linkedin/aggregation", method = RequestMethod.GET)
	@ResponseBody
	public String linkedinAggregation(ModelMap model) {
		/*
		 * Check whether the user has authorized the application to access the user’s LinkedIn data.
		 * If not, the user is redirected to ConnectController with the option to kick off the authorization process.
		 * If the user has authorized the application to access LinkedIn data, the application fetches the user’s profile
		 * as well as several of the most recent entries in the user’s home feed. The data is placed into the model to be
		 * displayed by the view identified as "friend_feed".
		 */
		if(connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }
		
		model.addAttribute(linkedIn.profileOperations().getProfileId());
        model.addAttribute("friendFeed", "<friendFeedPost>");
		
		return "friend_feed";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/linkedin/connections", method=RequestMethod.GET)
	public String connections(ModelMap model) {
		if(connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }
		
		NetworkStatistics statistics = linkedIn.connectionOperations().getNetworkStatistics();
		
		model.addAttribute("firstDegreeCount", statistics.getFirstDegreeCount());
		model.addAttribute("secondDegreeCount", statistics.getSecondDegreeCount());
		model.addAttribute("connections", linkedIn.connectionOperations().getConnections());
		
		return "linkedin/connections";
	}
}