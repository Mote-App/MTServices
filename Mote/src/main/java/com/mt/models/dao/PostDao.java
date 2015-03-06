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

	//An EntityManager will be automatically injected from entityManagerFactory
	// setup on DatabaseConfig class.
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
	  public List<Post> getMostRecentPost(long userId){
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
}
