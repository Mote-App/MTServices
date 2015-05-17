package com.mt.models.dao;

import java.util.List;

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
	  
  	public List<Long> getFriends(long userId){
  		
  		List<Long> friends = _entityManager.createQuery("SELECT P.profileFriendId FROM UserFriends P where P.profileId = :userId")
  				.setParameter("userId", userId)
  				.getResultList();
  		
  		return friends;
  	}
}
