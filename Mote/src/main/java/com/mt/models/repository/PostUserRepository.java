package com.mt.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mt.models.PostProfileLike;

public interface PostUserRepository extends CrudRepository<PostProfileLike, Long>{

	@Query("select u.postId from PostProfileLike u where u.profileId = ? and level='F'")
	List<Long> findByUserIdForFriends(long profileId);
	
	@Query("select u.postId from PostProfileLike u where u.profileId = ? and level='S'")
	List<Long> findByUserIdForSchools(long profileId);

	@Query("select u.postId from PostProfileLike u where u.profileId = ? and level='N'")
	List<Long> findByUserIdForNational(long profileId);

}
