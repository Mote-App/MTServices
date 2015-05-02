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
	public List<Long> getUserPosts(List<Long> userIds) {
	    return _entityManager.createQuery("SELECT DISTINCT P.userId FROM Post as P WHERE userId IN :userIds ORDER BY postDate DESC")
	    		.setParameter("userIds", userIds)
	    		.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getMostRecentPost(long userId) {
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE userId = :userId ORDER BY postDate DESC")
				.setParameter("userId", userId)
				.setMaxResults(2)
				.getResultList();
	}
	
	public Post getMostPopularPost(long userId){
		return (Post)_entityManager.createQuery("SELECT P FROM Post as P WHERE userId = :userId ORDER BY likes DESC")
				.setParameter("userId", userId)
				.setMaxResults(1)
				.getSingleResult();
	}
	
	public Post getPost(long postId){
		return (Post)_entityManager.createQuery("SELECT P FROM Post as P WHERE id = :postId")
				.setParameter("postId", postId)
				.setMaxResults(1)
				.getSingleResult();
	}
	
	
	
	
	
	/*
	 * Instead of using a List<Post> what other existing Java collections data structure I could use that would do
	 * this process more efficiently for time complexity [performance] and space complexity [memory footprint].
	 */
	
	@SuppressWarnings("unchecked")
	public List<Post> getFriendFeedPosts() {
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE school_promote = false AND national_promote = false ORDER BY postDate DESC")
				.getResultList();
	}
	
	public Long getNs() {
		return (Long)_entityManager.createQuery("SELECT COUNT(P.school_promote) AS Ns FROM Post as P WHERE school_promote = true")
				.getSingleResult();
	}
	
	public int promotePostToSchoolFeed(long postId) {
		return _entityManager.createQuery("UPDATE Post SET school_promote = true WHERE id = :postId")
				.setParameter("postId", postId)
				.executeUpdate();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Post> getSchoolFeedPosts() {
		return _entityManager.createQuery("SELECT P FROM Post as P WHERE school_promote = true AND national_promote = false ORDER BY postDate DESC")
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
		return (Long)_entityManager.createQuery("SELECT SUM(likes) AS Cl FROM Post as P WHERE college_id = :collegeId")
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
		return (Long)_entityManager.createQuery("SELECT SUM(likes) AS Cl FROM Post as P WHERE college_id = :collegeId")
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
		return (Long)_entityManager.createQuery("SELECT COUNT(*) AS Cpn FROM Post as P WHERE college_id = :collegeId")
				.setParameter("collegeId", collegeId)
				.getSingleResult();
	}
	
	public int promotePostToNationalFeed(long postId) {
		return _entityManager.createQuery("UPDATE Post SET national_promote = true WHERE id = :postId")
				.setParameter("postId", postId)
				.executeUpdate();
	}
}