package com.mt.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mt.models.PostProfileLike;

/**
 * The <code>PostUserLikeRepository</code> ...
 * 
 * @author gibranecastillo
 *
 */
public interface PostUserLikeRepository extends CrudRepository<PostProfileLike, Long> {
	/**
	 * 
	 * @param profileId
	 * @return
	 */
	@Query("select u.postId from PostProfileLike u where u.profileId = ? and level='F'")
	List<Long> findByUserIdForFriends(long profileId);
	
	/**
	 * 
	 * @param profileId
	 * @return
	 */
	@Query("select u.postId from PostProfileLike u where u.profileId = ? and level='S'")
	List<Long> findByUserIdForSchools(long profileId);
	
	/**
	 * 
	 * @param profileId
	 * @return
	 */
	@Query("select u.postId from PostProfileLike u where u.profileId = ? and level='N'")
	List<Long> findByUserIdForNational(long profileId);
}