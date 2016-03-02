package com.mt.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mt.models.PostProfileView;

/**
 * The <code>PostUserViewRepository</code> ...
 * 
 * @author gibranecastillo
 *
 */
public interface PostUserViewRepository extends CrudRepository<PostProfileView, Long> {
	/**
	 * 
	 * @param profileId
	 * @return
	 */
	@Query("select u.postId from PostProfileView u where u.profileId = :profileId and u.postId = :postId and level = :level")
	Long findPostViewForLevel(@Param("profileId") long profileId, @Param("postId") long postId, @Param("level") String level);
	
	@Query("select count(u.postId) from PostProfileView u where u.postId = :postId and level = :level")
	int countPostViewForLevel(@Param("postId") long postId, @Param("level") String level);
}