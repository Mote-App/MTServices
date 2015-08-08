package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mt.models.Post;

/**
 * The <code>PostDao</code> ...
 * 
 * @author gibranecastillo
 *
 */
@Repository
@Transactional
public class PostDao {
	//An EntityManager will be automatically injected from entityManagerFactory setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Get the posts for list of user's friends sorted by post date in descending order.
	 * This will arrange the user list in the order of recent posting and is helpful to 
	 * retrieve current post (the first record for a user) and most recent post for a user
	 * (the second record for a user) and based on likes you can find the most popular from the same list
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getUserPosts(List<Long> profileIds) {
		log.info("Get the posts for list of user's [" + profileIds + "] friends sorted by post date in descending order.");
		
	    return _entityManager.createQuery("SELECT DISTINCT P.profile.profileId FROM Post as P WHERE P.profile.profileId IN :profileIds ORDER BY P.postDate DESC")
	    		.setParameter("profileIds", profileIds)
	    		.getResultList();
	}
	
	/**
	 * 
	 * @param profileId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getMostRecentPost(long profileId) {
		log.info("Get most recent post for user [profileId]: " + profileId);
		
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE P.profile.profileId = :profileId ORDER BY P.postDate DESC")
				.setParameter("profileId", profileId)
				.setMaxResults(2)
				.getResultList();
	}
	
	/**
	 * 
	 * @param profileId
	 * @return
	 */
	public Post getMostPopularPost(long profileId) {
		//log.info("Get most popular post for user [profileId]: " + profileId);
		
		Object [] objects = (Object []) _entityManager.createQuery("SELECT PL.postId , COUNT(*) as likeCnt FROM PostProfileLike PL WHERE PL.profileId = :profileId GROUP BY PL.postId ORDER BY likeCnt DESC")
				.setParameter("profileId", profileId)
				.setMaxResults(1)
				.getSingleResult();
		
		Long postId = (Long)objects[0];
		
		return getPost(postId);
	}
	
	/**
	 * Get the list of school post for user and its friends sorted by post date in descending order.
	 * Here Post entity is used to filter school post based on field postSchoolPromote.
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getUserSchoolPosts(List<Long> profileIds, Long collegeId) {
		log.info("Get the list of school post for user [" + profileIds + "] from college [" + collegeId + "] and its friends sorted by post date in descending order");
		
	    return _entityManager.createQuery("SELECT P FROM Post P WHERE P.profile.profileId IN :profileIds and P.profile.profileCollege.collegeId = :collegeId and P.postSchoolPromote = 1 ORDER BY P.postDate DESC")
	    		.setParameter("profileIds", profileIds)
	    		.setParameter("collegeId", collegeId)
	    		.getResultList();
	}
	
	/**
	 * Get the list of national post for user and its friends sorted by post date in descending order.
	 * Here Post entity is used to filter school post based on field postSchoolPromote.
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getUserNationalPosts(List<Long> profileIds, Long collegeId) {
		log.info("Get the list of national post for user [" + profileIds + "] from college [" + collegeId + "] and its friends sorted by post date in descending order");
		
	    return _entityManager.createQuery("SELECT P FROM Post P WHERE P.profile.profileId IN :profileIds and P.profile.profileCollege.collegeId = :collegeId and P.postNationalPromote=1 ORDER BY P.postDate DESC")
	    		.setParameter("profileIds", profileIds)
	    		.setParameter("collegeId", collegeId)
	    		.getResultList();
	}
	
	/**
	 * 
	 * @param postId
	 * @return
	 */
	public Post getPost(long postId) {
		log.info("Get post for postId: " + postId);
		
		return (Post)_entityManager.createQuery("SELECT P FROM Post as P WHERE P.postId = :postId")
				.setParameter("postId", postId)
				.setMaxResults(1)
				.getSingleResult();
	}
	
	/*
	 * Instead of using a List<Post> what other existing Java collections data structure I could use that would do
	 * this process more efficiently for time complexity [performance] and space complexity [memory footprint].
	 */
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getFriendFeedPosts() {
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE P.postSchoolPromote = 0 AND P.postNationalPromote = 0 ORDER BY P.postDate DESC")
				.getResultList();
	}
	
	/**
	 * Returns the number of 'posts' in School Feed.
	 * 
	 * @return
	 */
	public Long getNs() {
		//Q. Which logic updates the school_promote? So that we can calculate Ns.
		//Ans. See the method promotePostToSchoolFeed, which is called after algorithm calculation
		//The Ns will be always zero unless some post is promoted to school
		log.info("Get Ns - the number of 'post' in School Feed.");
		
		return (Long)_entityManager.createQuery("SELECT COUNT(P.postSchoolPromote) AS Ns FROM Post as P WHERE P.postSchoolPromote = 1")
				.getSingleResult();
	}
	
	/**
	 * 
	 * @param postId
	 * @return
	 */
	public void promotePostToSchoolFeed(Post friendPost) {
		byte b = 1;
		friendPost.setPostSchoolPromote(b);
		_entityManager.persist(friendPost);
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getSchoolFeedPosts() {
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE P.postSchoolPromote = 1 AND P.postNationalPromote = 0 ORDER BY P.postDate DESC")
				.getResultList();
	}
	
	/**
	 * Cl - the number of 'likes' per post from that school.
	 * 
	 * @param collegeId
	 * @return
	 */
	public Long getCl(long collegeId) {
		log.info("Get Cl - the number of 'likes' per post from that school [collegeId]: " + collegeId);
		
		List<Long> profileIds = (List<Long>)_entityManager.createQuery("SELECT U.profileId from User U where U.profileCollege.collegeId = :collegeId")
				.setParameter("collegeId", collegeId)
				.getResultList();
		
		
		return (Long)_entityManager.createQuery("SELECT count(*) as Cl FROM PostProfileLike PL WHERE PL.profileId in :profileIds")
				.setParameter("profileIds", profileIds)
				.getSingleResult();
	}
	
	/**
	 * ClIdealAvg - the average number of 'likes' per post from all school.
	 * 
	 * @param collegeId
	 * @return
	 */
	public Long getClIdealAvg(long collegeId) {
		log.info("Get ClIdealAvg - the average number of 'likes' per post from this collegeId: " + collegeId);
		
		return (Long)_entityManager.createQuery("SELECT SUM(P.likes) AS Cl FROM Post as P")
				.setParameter("collegeId", collegeId)
				.getSingleResult();
	}
	
	/**
	 * Cpn - the number of 'post' from that school.
	 * 
	 * @param collegeId
	 * @return
	 */
	public Long getCpn(long collegeId) {
		log.info("Get Cpn - the number of 'post' from that school.");
		
		return (Long)_entityManager.createQuery("SELECT COUNT(P.postId) AS Cpn FROM Post as P WHERE P.profile.profileCollege.collegeId = :collegeId")
				.setParameter("collegeId", collegeId)
				.getSingleResult();
	}
	
	/**
	 * 
	 * @param postId
	 * @return
	 */
	public void promotePostToNationalFeed(Post schoolPost) {
		byte b = 1;
		schoolPost.setPostNationalPromote(b);
		_entityManager.persist(schoolPost);
	}
}