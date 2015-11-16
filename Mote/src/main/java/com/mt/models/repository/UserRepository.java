package com.mt.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mt.models.User;

/**
 * The <code>UserRepository</code> ...
 * 
 * @author gibranecastillo
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("select u from User u where u.profileId = ?")
	User findProfileById(Long profileId);
	
	
}