package com.mt.controllers.facebook;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Photo;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.Video;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mt.models.Aggregation;
import com.mt.models.AggregationSourceObject;
import com.mt.models.Post;
import com.mt.models.dao.UserFriendsDao;
import com.mt.models.repository.AggregationRepository;
import com.mt.models.repository.AggregationSourceObjectRepository;
import com.mt.models.repository.PostRepository;
import com.mt.models.repository.UserRepository;

/**
 * The <code>FacebookLoginController</code> is ...
 * 
 * @Controller marks the FacebookExpiredToken.java POJO class as MVC controller
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class FacebookLoginController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String FACEBOOK = "Facebook";
	private static final String YES = "Y";
	//private static final String NO = "N";
	
	@Autowired 
	AggregationRepository _aggregationRepo;
	
	@Autowired
	AggregationSourceObjectRepository _aggregationSourceObjectRepository;
	
	@Autowired
	UserRepository _userRepository;
	
	@Autowired
	UserFriendsDao _userFriendsDao;
	
	@Autowired
	PostRepository _postRepository;
	
	/*
	 * This REST api will redirect the user to a Facebook authorization page.
	 */
	@RequestMapping("/fb/login")
	public void login (HttpServletRequest request, HttpServletResponse response) throws IOException{
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("956170854392949", "5724c20e501b3d770370f04fecffbb2c");
		
		OAuth2Parameters params = new OAuth2Parameters();
		
		// AWS EC2 URL http://54.149.27.205     Account was closed 28th December 2015
		params.setRedirectUri("http://127.0.0.1:8080/fb/callback");
		params.setScope("public_profile, email, user_friends, user_posts, user_photos, user_videos");
		//Store user Id and client address and port, to be made available in callback, otherwise it gets lost redirection.
		//params.setState(request.getParameter("userId") + "," + request.getHeader("Referer"));
		params.setState(request.getParameter("userId") + "," + "http://localhost:8100/");
		//params.setState(request.getParameter("userId") + "," + request.getHeader(HttpHeaders.REFERER));
		
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(params);
		
		log.info(" fb/login User ID " + request.getParameter("userId"));
		log.info(" fb/login Authorize URL " + authorizeUrl);
		log.info("Login Referer : " + request.getHeader("Referer"));
		log.info("Login HttpHeaders.REFERER : " + request.getHeader(HttpHeaders.REFERER));
		
		response.sendRedirect(authorizeUrl);
	}
	
	/**
	 * This REST api is the method for the callback URL, where the user will be redirected after logging into Facebook.
	 * Using the authorization code parameter received from Facebook, get an access token and save it in the session.
	 * 
	 * @RequestParam(name) maps HTTP request parameter with name to the method parameter.
	 * 
	 * @param authorizationCode
	 * @param callbackParam
	 * @param request
	 * @return
	 */
	@RequestMapping("/fb/callback")
	//@ResponseStatus(value=HttpStatus.OK)
	public String callback(@RequestParam("code") String authorizationCode, @RequestParam("state") String callbackParam, HttpServletRequest request) {
		log.info("authorizationCode: " + authorizationCode);
		log.info("callbackParam: " + callbackParam);
		
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("956170854392949", "5724c20e501b3d770370f04fecffbb2c");
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(authorizationCode, "http://127.0.0.1:8080/fb/callback", null);
		String token = accessGrant.getAccessToken();
		
		//request.getSession().setAttribute("facebookToken", token);
		
		String arr[] = callbackParam.split(",");
		long moteUserId = Long.parseLong(arr[0]);
		log.info("redirect User ID " + moteUserId);
		
		Facebook facebook = new FacebookTemplate(token);
		
		persistFacebookUserProfile(facebook, moteUserId, token);
		autoFriendFacebookUserProfileFriends(facebook, moteUserId);
		postFacebookUserProfilePhotos(facebook, moteUserId);
		postFacebookUserProfileVideos(facebook, moteUserId);
		
		//return "redirect:/fb_login_success?facebookToken=" + token;
		log.info("Client URL required for redirect : " + arr[1]);
		log.info("return to 'redirect:" + arr[1]+ "/#/app/aggregation'");
		
		return "redirect:" + arr[1]+ "/#/app/aggregation";
		
	}
	
	/**
	 * Persist Mote user's basic Facebook user profile aggregation content into motedb.
	 * 
	 * @param facebook an interface specifying a basic set of operations for interacting with Facebook.
	 * @param moteUserId a long value that represents the Mote User Id.
	 * @param accessToken a String value that represents the Facebook user's OAuth 2.0 access token.
	 */
	private void persistFacebookUserProfile(Facebook facebook, long moteUserId, String accessToken) {
		try {
			User facebookUser = facebook.userOperations().getUserProfile();
			com.mt.models.User moteUser = new com.mt.models.User();
			
			try {
				moteUser.setProfileId(moteUserId);
			} catch(Exception e) {
				log.error("Invalid User ID, exiting FB persistence: ", e);
			}
			
			Aggregation aggregation = new Aggregation();
			
			aggregation.setAggregationId(Long.parseLong(facebookUser.getId()));
			aggregation.setAggregationIsFriend(YES);
			aggregation.setAggregationName(FACEBOOK);
			aggregation.setAggregationSourceId(2);
			aggregation.setProfileId(moteUser);
			aggregation.setAccessToken(accessToken);
			
			log.info(moteUser.toString());
			
			_aggregationRepo.save(aggregation);
			
			log.info("The following basic Facebook Aggregation Content Persistance was successfull: " + aggregation.toString());
		} catch(Exception e) {
			log.error("The basic Facebook Aggregation Content Persistance was a failure" , e);
		}
	}
	
	/**
	 * Auto-friend Mote user's Facebook friends.  In other words, if Mote user's Facebook friends are also
	 * register with Mote, make them friend in Mote as well, if they are not register ignore for now.
	 * 
	 * @param facebook an interface specifying a basic set of operations for interacting with Facebook.
	 * @param moteUserId a long value that represents the Mote User Id.
	 */
	private void autoFriendFacebookUserProfileFriends(Facebook facebook, long moteUserId) {
		try {
			User facebookUser = facebook.userOperations().getUserProfile();
			PagedList<String> friendIds = facebook.friendOperations().getFriendIds(facebookUser.getId());
			
			for(String friendId : friendIds) {
				/*
				 * If friendId (Facebook User's Friend fb id) is found in motedb.aggregation table (aggregation_id),
				 * then this Facebook user has a Mote profile; therefore, auto friend if they are not friends already with Mote user.
				 */
				com.mt.models.User fbFriendMoteUser = _aggregationRepo.findUserAggregationFriend(Long.parseLong(friendId), FACEBOOK);
				
				if(fbFriendMoteUser != null) {
					log.info("friendId '" + friendId + "' (Facebook User's Friend fb id) is register with Mote, found in motedb.aggregation table (aggregation_id) " + fbFriendMoteUser.toString());
					_userFriendsDao.addFriend(moteUserId, fbFriendMoteUser.getProfileId());
				} else {
					log.info("friendId '" + friendId + "' (Facebook User's Friend fb id) has not register with Mote, not found in motedb.aggregation table (aggregation_id)");
				}
			}
			
			log.info("Facebook Friend List Auto friending with Mote user completed successfully.");
		} catch(Exception e) {
			log.error("Facebook Friend List Auto friending with Mote user failed" , e);
		}
	}
	
	/**
	 * Persist and Post Mote user's Facebook user profile photos aggregation content into motedb.
	 * 
	 * @param facebook an interface specifying a basic set of operations for interacting with Facebook.
	 * @param moteUserId a long value that represents the Mote User Id.
	 */
	private void postFacebookUserProfilePhotos(Facebook facebook, long moteUserId) {
		try {
			User facebookUser = facebook.userOperations().getUserProfile();
			PagedList<Photo> photos = facebook.mediaOperations().getPhotos(facebookUser.getId());
			
			for(Photo photo : photos) {
				AggregationSourceObject sourceObject = new AggregationSourceObject();
				
				sourceObject.setAggregationId(Long.parseLong(facebookUser.getId()));
				sourceObject.setSourceObjectUrl(photo.getPicture());
				sourceObject.setSourceObjectCaption(photo.getName());
				
				log.info(sourceObject.toString());
				
				_aggregationSourceObjectRepository.save(sourceObject);
				
				log.info("photo.getHeight(): " + photo.getHeight());
				log.info("photo.getIcon(): " + photo.getIcon());
				log.info("photo.toString(): " + photo.toString());
				//log.info(": " + );
				
				//For each source object create new Post entry with reference to source_id
				Post post = new Post();
				
				post.setPostTypeCode("public");
				post.setPostObjectPath(FACEBOOK);
				post.getProfile().setProfileId(moteUserId);
				post.setPostDate(Calendar.getInstance());
				post.setPostObjectPath(sourceObject.getSourceObjectUrl());
				post.setAggregationSourceObject(sourceObject);
				
				log.info(post.toString());
				
				_postRepository.save(post);
			}
			
			log.info("The Facebook user profile photos Persistance was successfull.");
		} catch(Exception e) {
			log.error("The Facebook user profile photos Persistance was a failure" , e);
		}
	}
	
	/**
	 * Persist and Post Mote user's Facebook user profile videos aggregation content into motedb.
	 * 
	 * @param facebook an interface specifying a basic set of operations for interacting with Facebook.
	 * @param moteUserId a long value that represents the Mote User Id.
	 */
	private void postFacebookUserProfileVideos(Facebook facebook, long moteUserId) {
		try {
			User facebookUser = facebook.userOperations().getUserProfile();
			PagedList<Video> videos = facebook.mediaOperations().getVideos(facebookUser.getId());
			
			for(Video video : videos) {
				AggregationSourceObject sourceObject = new AggregationSourceObject();
				
				sourceObject.setAggregationId(Long.parseLong(facebookUser.getId()));
				sourceObject.setSourceObjectUrl(video.getSource());
				sourceObject.setSourceObjectCaption(video.getName());
				
				log.info(sourceObject.toString());
				
				_aggregationSourceObjectRepository.save(sourceObject);

				log.info("video.getDescription(): " + video.getDescription());
				log.info("video.getEmbedHtml(): " + video.getEmbedHtml());
				log.info("video.toString(): " + video.toString());
				//log.info(": " + );
				
				//For each source object create new Post entry with reference to source_id
				Post post = new Post();
				
				post.setPostTypeCode("public");
				post.setPostObjectPath(FACEBOOK);
				post.getProfile().setProfileId(moteUserId);
				post.setPostDate(Calendar.getInstance());
				post.setPostObjectPath(sourceObject.getSourceObjectUrl());
				post.setAggregationSourceObject(sourceObject);
				
				log.info(post.toString());
				
				_postRepository.save(post);
			}
			
			log.info("The Facebook user profile videos Persistance was successfull.");
		} catch(Exception e) {
			log.error("The Facebook user profile videos Persistance was a failure" , e);
		}
	}
}