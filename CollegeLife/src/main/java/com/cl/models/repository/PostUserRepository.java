package com.cl.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cl.models.PostUser;

public interface PostUserRepository extends CrudRepository<PostUser, Long>{

	@Query("select u.postId from PostUser u where u.userId = ? and level='F'")
	List<Long> findByUserIdForFriends(long userId);
	
	@Query("select u.postId from PostUser u where u.userId = ? and level='S'")
	List<Long> findByUserIdForSchools(long userId);

	@Query("select u.postId from PostUser u where u.userId = ? and level='N'")
	List<Long> findByUserIdForNational(long userId);

}
