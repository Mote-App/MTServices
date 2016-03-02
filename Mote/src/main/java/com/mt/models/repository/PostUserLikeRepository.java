package com.mt.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
	@Query("select u.postId from PostProfileLike u where u.profileId = :profileId and u.postId = :postId and level = :level ")
	Long findPostLikeForLevel(@Param("profileId") long profileId, @Param("postId") long postId, @Param("level") String level);
	
	@Query("select count(u.postId) from PostProfileLike u where u.postId = :postId and level = :level ")
	int countPostLikeForLevel(@Param("postId") long postId, @Param("level") String level);	
}