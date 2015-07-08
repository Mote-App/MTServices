package com.mt.algorithm.ssa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mt.models.Post;
import com.mt.models.User;
import com.mt.models.dao.PostDao;
import com.mt.models.dao.UserDao;


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
public class SSAJob {
	@Autowired
	private PostDao _postDao;
	
	@Autowired
	private UserDao _userDao;
	
	public void initiate(){	
		long Ns = _postDao.getNs();
		List<Post> friendPosts = _postDao.getFriendFeedPosts();
		
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
			examineFriendFeedPost(friendPost.getViews(), friendPost.getLikes(), Ns, friendPost.getPostId());
		}
		
		List<Post> schoolPosts = _postDao.getSchoolFeedPosts();
		
		for(int i = 0; i < schoolPosts.size(); i++) {
			Post schoolPost = schoolPosts.get(i);
			examineSchoolFeedPost(Ns, schoolPost);
		}
	}
	
	/**
	 * 
	 * @param V a long value that represents the number of 'views' on a post.
	 * @param L a long value that represents the number of 'likes' on a post.
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * @param postId
	 */
	public void examineFriendFeedPost(long V, long L, long Ns, long postId) {
		SSA ssa = new SSA();
		SSAPostRatio rfPostRatio = ssa.calculateRf(V, L, Ns);
		
		if(rfPostRatio.isGreaterThan()) {
			_postDao.promotePostToSchoolFeed(postId);
		}
	}
	
	/**
	 * 
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * @param schoolPost
	 */
	public void examineSchoolFeedPost(long Ns, Post schoolPost) {
		//TODO: Implement logic to calculate number of views on client (Javascript)
		long V = schoolPost.getViews();
		long L = schoolPost.getLikes();
		
		User user = _userDao.getById(schoolPost.getProfileId());		
		long collegeId = user.getProfileCollege().getCollegeId();
		
		long postId = schoolPost.getPostId();
		long Cr = _userDao.getCr(collegeId);
		long Cl = _postDao.getCl(collegeId);
		long Cpn = _postDao.getCpn(collegeId);
		double CrIdealAvg = _userDao.getCrIdealAvg(); //pending!
		double ClIdealAvg = 0.0; //pending!
		double CpnAvg = 0.0; //pending!
		
		SSA ssa = new SSA();
		SSAPostRatio rsPostRatio = ssa.calculateRs(V, L, Cr, CrIdealAvg, Cl, ClIdealAvg, Cpn, CpnAvg, Ns);
		
		if(rsPostRatio.isGreaterThan()) {
			_postDao.promotePostToNationalFeed(postId);
		}
	}
}