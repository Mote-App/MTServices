package com.cl.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cl.models.Post;

@Repository
@Transactional
public class PostDao {

	//An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager _entityManager;
	  
	  /**
	   * Method getAll
	   * <br/>
	   * Return all the colleges stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Post> getAll(String userId) {
	    return _entityManager.createQuery("from Post where user.id = :id")
	    		.setParameter("id", userId)
	    		.getResultList();
	  }
}
