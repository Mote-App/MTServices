package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
	
	/**
	 * Get the posts for list of user's friends sorted by post date in descending order.
	 * This will arrange the user list in the order of recent posting and is helpful to 
	 * retrieve current post (the first record for a user) and most recent post for a user
	 * (the second record for a user) and based on likes you can find the most popular from the same list
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getUserPosts(List<Long> profileIds) {
	    return _entityManager.createQuery("SELECT DISTINCT P.profileId FROM Post as P WHERE P.profileId IN :profileIds ORDER BY postDate DESC")
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
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE P.profileId = :profileId ORDER BY postDate DESC")
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
		return (Post)_entityManager.createQuery("SELECT P FROM Post as P WHERE P.profileId = :profileId ORDER BY likes DESC")
				.setParameter("profileId", profileId)
				.setMaxResults(1)
				.getSingleResult();
	}
	
	/**
	 * Get the list of post for user and its friends sorted by post date in descending order.
	 * Here Post entity is used to filter school post based on field postSchoolPromote.
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getUserSchoolPosts(List<Long> profileIds, Long collegeId) {
	    return _entityManager.createQuery("SELECT P FROM Post as P JOIN Profile as U ON P.profileId = U.profileId WHERE P.profileId IN :profileIds and U.profileCollege.collegeId = :collegeId and P.postSchoolPromote=true ORDER BY postDate DESC")
	    		.setParameter("profileIds", profileIds)
	    		.setParameter("collegeId", collegeId)
	    		.getResultList();
	}
	
	/**
	 * Get the list of post for user and its friends sorted by post date in descending order.
	 * Here Post entity is used to filter school post based on field postSchoolPromote.
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getUserNationalPosts(List<Long> profileIds, Long collegeId) {
	    return _entityManager.createQuery("SELECT P FROM Post as P JOIN Profile as U ON P.profileId = U.profileId WHERE P.profileId IN :profileIds and U.profileCollege.collegeId = :collegeId and P.postNationalPromote=true ORDER BY postDate DESC")
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
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE P.postSchoolPromote = false AND P.postNationalPromote = false ORDER BY P.postDate DESC")
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
		return (Long)_entityManager.createQuery("SELECT COUNT(P.postSchoolPromote) AS Ns FROM Post as P WHERE P.postSchoolPromote = true")
				.getSingleResult();
	}
	
	/**
	 * 
	 * @param postId
	 * @return
	 */
	public int promotePostToSchoolFeed(long postId) {
		return _entityManager.createQuery("UPDATE Post SET P.postSchoolPromote = true WHERE P.postId = :postId")
				.setParameter("postId", postId)
				.executeUpdate();
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getSchoolFeedPosts() {
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE P.postSchoolPromote = true AND P.postNationalPromote = false ORDER BY P.postDate DESC")
				.getResultList();
	}
	
	/**
	 * Cl - the number of 'likes' per post from that school.
	 * 
	 * @param collegeId
	 * @return
	 */
	public Long getCl(long collegeId) {
		return (Long)_entityManager.createQuery("SELECT SUM(P.likes) AS Cl FROM Post as P JOIN Profile as U ON P.profileId = U.profileId WHERE U.profileCollege.collegeId = :collegeId")
				.setParameter("collegeId", collegeId)
				.getSingleResult();
	}
	
	/**
	 * ClIdealAvg - the average number of 'likes' per post from all school.
	 * 
	 * @param collegeId
	 * @return
	 */
	public Long getClIdealAvg(long collegeId) {
		return (Long)_entityManager.createQuery("SELECT SUM(P.likes) AS Cl FROM Post as P JOIN Profile as U ON P.profileId = U.profileId")
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
		return (Long)_entityManager.createQuery("SELECT COUNT(P.postId) AS Cpn FROM Post as P JOIN Profile as U ON P.profileId = U.profileId WHERE U.profileCollege = :collegeId")
				.setParameter("collegeId", collegeId)
				.getSingleResult();
	}
	
	/**
	 * 
	 * @param postId
	 * @return
	 */
	public int promotePostToNationalFeed(long postId) {
		return _entityManager.createQuery("UPDATE Post as P SET P.postNationalPromote = true WHERE P.postId = :postId")
				.setParameter("postId", postId)
				.executeUpdate();
	}
}