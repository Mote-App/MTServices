package com.mt.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

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
	@Query("select u.postId from PostProfileView u where u.profileId = ? and u.postId = ? and level = ? ")
	Long findPostViewForLevel(long profileId, long postId, String level);
	
	@Query("select count(u.postId) from PostProfileView u where u.postId = ? and level = ? ")
	int countPostViewForLevel(long postId, String level);
}