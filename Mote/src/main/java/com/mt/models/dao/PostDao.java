package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mt.models.Post;

@Repository
@Transactional
public class PostDao {
	
	//An EntityManager will be automatically injected from entityManagerFactory setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;
	
	/**
	 * Get the posts for list of users sorted by post date in descending order.
	 * This will arrange the user list in the order of recent posting and is helpful to 
	 * retrieve most recent post (the first record for a user) and most recent post for a user
	 * (the second record for a user) and based on likes you can find the most popular from the same list
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getUserPosts(List<Long> profileIds) {
	    return _entityManager.createQuery("SELECT DISTINCT P.profileId FROM Post as P WHERE P.profileId IN :profileIds ORDER BY postDate DESC")
	    		.setParameter("profileIds", profileIds)
	    		.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getMostRecentPost(long profileId) {
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE P.profileId = :profileId ORDER BY postDate DESC")
				.setParameter("profileId", profileId)
				.setMaxResults(2)
				.getResultList();
	}
	
	public Post getMostPopularPost(long profileId){
		return (Post)_entityManager.createQuery("SELECT P FROM Post as P WHERE P.profileId = :profileId ORDER BY likes DESC")
				.setParameter("profileId", profileId)
				.setMaxResults(1)
				.getSingleResult();
	}
	
	public Post getPost(long postId){
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
	 * old:  SELECT P FROM Post as P WHERE post_school_promote = false AND post_national_promote = false ORDER BY postDate DESC
	 * new:  SELECT P FROM Post as P WHERE P.post_school_promote = false AND P.post_national_promote = false ORDER BY P.post_date DESC
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getFriendFeedPosts() {
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE P.postSchoolPromote = false AND P.postNationalPromote = false ORDER BY P.postDate DESC")
				.getResultList();
	}
	
	public Long getNs() {
		
		//Q. Which logic updates the school_promote? So that we can calculate Ns.
		//Ans. See the method promotePostToSchoolFeed, which is called after algorithm calculation
		return (Long)_entityManager.createQuery("SELECT COUNT(P.postSchoolPromote) AS Ns FROM Post as P WHERE P.postSchoolPromote = true")
				.getSingleResult();
	}
	
	public int promotePostToSchoolFeed(long postId) {
		return _entityManager.createQuery("UPDATE Post SET P.postSchoolPromote = true WHERE P.postId = :postId")
				.setParameter("postId", postId)
				.executeUpdate();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Post> getSchoolFeedPosts() {
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE P.postSchoolPromote = true AND P.postNationalPromote = false ORDER BY P.postDate DESC")
				.getResultList();
	}
	
	/**
	 * Cl - the number of 'likes' per post from that school.
	 * New Data Model
	 * SELECT SUM(A.likes) FROM profile_has_post A JOIN profile B ON A.idprofile = B.idprofile WHERE A.school_promote = true AND B.idcollege = :collegeId 
	 * 
	 * @param collegeId
	 * @return
	 */
	public Long getCl(long collegeId) {
		return (Long)_entityManager.createQuery("SELECT SUM(P.likes) AS Cl FROM Post as P JOIN Profile as U ON P.profileId = U.profileId WHERE U.profileCollege = :collegeId")
				.setParameter("collegeId", collegeId)
				.getSingleResult();
	}
	
	/**
	 * ClIdealAvg - the average number of 'likes' per post from all school.
	 * New Data Model
	 * SELECT SUM(A.likes) FROM profile_has_post A JOIN profile B ON A.idprofile = B.idprofile WHERE A.school_promote = true AND B.idcollege = :collegeId 
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
	 * New Data Model
	 * SELECT COUNT(A.idpost) FROM profile_has_post A JOIN profile B ON A.idprofile = B.idprofile WHERE A.school_promote = true AND B.idcollege = :collegeId 
	 * 
	 * @param collegeId
	 * @return
	 */
	public Long getCpn(long collegeId) {
		return (Long)_entityManager.createQuery("SELECT COUNT(P.postId) AS Cpn FROM Post as P JOIN Profile as U ON P.profileId = U.profileId WHERE U.profileCollege = :collegeId")
				.setParameter("collegeId", collegeId)
				.getSingleResult();
	}
	
	public int promotePostToNationalFeed(long postId) {
		return _entityManager.createQuery("UPDATE Post as P SET P.postNationalPromote = true WHERE P.postId = :postId")
				.setParameter("postId", postId)
				.executeUpdate();
	}
}