package com.cl.models.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserFriendsDao {

		//An EntityManager will be automatically injected from entityManagerFactory
		// setup on DatabaseConfig class.
	  	@PersistenceContext
	  	private EntityManager _entityManager;
	  
	  	
}
