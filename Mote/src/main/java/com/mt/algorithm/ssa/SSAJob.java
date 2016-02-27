package com.mt.algorithm.ssa;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mt.models.Post;
import com.mt.models.SSAParams;
import com.mt.models.User;
import com.mt.models.dao.PostDao;
import com.mt.models.dao.SSAParamsDao;
import com.mt.models.dao.UserDao;
import com.mt.models.repository.PostUserLikeRepository;
import com.mt.models.repository.PostUserViewRepository;


/**
 * 
 * 
 * The Social Stairway Algorithm needs the following work:
 * 1.  Kevin/Ryan want the coefficient and threshold values to be dynamic.
 *     We need to create an SQL table that will hold the values, which can be change adhoc by a MySQL administrator
 *     [perhaps in the future we can create a web client to do that].
 *     We may want to skip this until after the Demo and focus on completing the algorithm and just use the ones that
 *     are hard coded right now or modify them if necessary but go with hardcoded values for now
 * 
 * 2. Implement Timer to execute the Social Stairway Algorithm.
 * 
 * 3. Need logic to calculate certain likes and post values.
 *    Sanjay and Gibran sort of talked about that and decided to all a new field to one of the existing tables so that
 *    if the post was already promoted to say National feeds, then we don't need to run the algorithm...
 * 
 * 4. Integrate Social Stairway Algorithm with Front-End.  The Front-End and the Back-End is already integrated and working,
 *    but the Social Stairway Algorithm is not integrated, we would have to come up with some URIs that Front-End can call to
 *    get what it needs ....
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class SSAJob {
	@Autowired
	private PostDao _postDao;
	
	@Autowired
	private UserDao _userDao;
	
	@Autowired
	private SSAParamsDao _ssaParamsDao;
	
	@Autowired
	private PostUserViewRepository _postUserViewRepository;
	
	@Autowired
	private PostUserLikeRepository _postUserLikeRepository;
	
	//@Autowired
	private SSA ssa;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void initiate() {
		SSAParams ssaParams = _ssaParamsDao.getSSAParams(1L);
		
		ssa = new SSA(ssaParams);
		
		long Ns = _postDao.getNs();
		
		log.info("Ns (the number of 'posts' in School Feed): " + Ns);
		
		List<Post> friendPosts = _postDao.getFriendFeedPosts();
		
		log.info("Total Friend Feed Posts: " + friendPosts.size());
		
		/*
		 * Instead of using a List<Post> what other existing Java collections data structure I could use that would do
		 * this process more efficiently for time complexity [performance] and space complexity [memory footprint].
		 * 
		 * May be we can use MoM (Message oriented Technology) to send the required data from the Mote App while end user 
		 * is like any post to Algorithm Processing Engine (APE) to prepare normalized table for processing algorithm. 
		 * 
		 * To get the count of school and 
		 *  
		 * First I'll get the algorithm working and then I'll improve performance/memory footprint.
		 */
		
		for(int i = 0; i < friendPosts.size(); i++) {
			Post friendPost = friendPosts.get(i);
			
			int views = _postUserViewRepository.countPostViewForLevel(friendPost.getPostId(), "F");
			
			int likes = _postUserLikeRepository.countPostLikeForLevel(friendPost.getPostId(), "F");
			
			examineFriendFeedPost(friendPost, views, likes, Ns, friendPost.getPostId());
		}
		
		List<Post> schoolPosts = _postDao.getSchoolFeedPosts();
		
		log.info("Total School Feed Posts: " + schoolPosts.size());
		
		for(int i = 0; i < schoolPosts.size(); i++) {
			Post schoolPost = schoolPosts.get(i);
			examineSchoolFeedPost(Ns, schoolPost);
		}
		
		log.info("Completed Social Stairway Algorithm : " + new Date());
		log.info("##############################################################################");
	}
	
	/**
	 * 
	 * @param V a long value that represents the number of 'views' on a post.
	 * @param L a long value that represents the number of 'likes' on a post.
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * @param postId
	 */
	
	public void examineFriendFeedPost(Post friendPost, long V, long L, long Ns, long postId) {
		SSAPostRatio rfPostRatio = ssa.calculateRf(V, L, Ns);
		
		log.info("Processing Post Id: " + postId);
		log.info("Total Views : " + V);
		log.info("Total Likes : " + L);
		
		log.info("Is Rf > Kf : " + rfPostRatio.isGreaterThan());
		
		if(rfPostRatio.isGreaterThan()) {
			friendPost.setPostSchoolPromote(1);
			_postDao.updatePost(friendPost);
			log.info("Promoting Post Id to School feed !!!");
		}
	}
	
	/**
	 * 
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * @param schoolPost
	 */
	public void examineSchoolFeedPost(long Ns, Post schoolPost) {
		//TODO: Implement logic to calculate number of views on client (Javascript)
		
		long V = _postUserViewRepository.countPostViewForLevel(schoolPost.getPostId(), "S");
		long L = _postUserLikeRepository.countPostLikeForLevel(schoolPost.getPostId(), "S");
		
		log.info("Processing Post Id: " + schoolPost.getPostId());
		log.info("Total Views : " + V);
		log.info("Total Likes : " + L);
		
		User user = _userDao.getById(schoolPost.getProfile().getProfileId());		
		long collegeId = user.getProfileCollege().getCollegeId();
		
		log.info("Processing for profile Id: " + user.getProfileId() + " for college Id : " + collegeId);
		
		long Cr = _userDao.getCr(collegeId);
		long Cl = _postDao.getCl(collegeId);
		long Cpn = _postDao.getCpn(collegeId);
		double CrIdealAvg = _userDao.getCrIdealAvg(); //pending!
		double ClIdealAvg = 0.0; //pending!
		double CpnAvg = 0.0; //pending!
		
		SSAPostRatio rsPostRatio = ssa.calculateRs(V, L, Cr, CrIdealAvg, Cl, ClIdealAvg, Cpn, CpnAvg, Ns);
		
		log.info("Is Rs > Ks : " + rsPostRatio.isGreaterThan());
		
		if(rsPostRatio.isGreaterThan()) {
			schoolPost.setPostNationalPromote(1);
			_postDao.updatePost(schoolPost);
			log.info("Promoting Post Id to National feed !!! ");
		}
	}
}