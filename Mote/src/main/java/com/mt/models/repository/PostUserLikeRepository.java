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
	@Query("select u.postId from PostProfileLike u where u.profileId = ? and u.postId = ? and level='F'")
	List<Long> findPostLikeForFriendFeeds(long profileId, long postId);
	
	/**
	 * 
	 * @param profileId
	 * @return
	 */
	@Query("select u.postId from PostProfileLike u where u.profileId = ? and u.postId = ? and level='S'")
	List<Long> findPostLikeForSchoolFeeds(long profileId, long postId);
	
	/**
	 * 
	 * @param profileId
	 * @return
	 */
	@Query("select u.postId from PostProfileLike u where u.profileId = ? and and u.postId = ? level='N'")
	List<Long> findPostLikeForNationalFeeds(long profileId, long postId);
}