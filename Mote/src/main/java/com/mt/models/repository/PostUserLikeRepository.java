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
	@Query("select u.postId from PostProfileLike u where u.profileId = ? and u.postId = ? and level = ? ")
	Long findPostLikeForLevel(long profileId, long postId, String level);
	
	@Query("select count(u.postId) from PostProfileLike u where u.postId = ? and level = ? ")
	int countPostLikeForLevel(long postId, String level);
	
}