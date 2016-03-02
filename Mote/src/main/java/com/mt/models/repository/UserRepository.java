package com.mt.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mt.models.User;

/**
 * The <code>UserRepository</code> ...
 * 
 * @author gibranecastillo
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
	@Query("select u from User u where u.profileId = :profileId")
	User findProfileById(@Param("profileId") Long profileId);	
}