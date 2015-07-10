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
	@Query("select u.postId from PostProfileView u where u.profileId = ? and level='F'")
	List<Long> findByUserIdForFriends(long profileId);
	
	/**
	 * 
	 * @param profileId
	 * @return
	 */
	@Query("select u.postId from PostProfileView u where u.profileId = ? and level='S'")
	List<Long> findByUserIdForSchools(long profileId);
	
	/**
	 * 
	 * @param profileId
	 * @return
	 */
	@Query("select u.postId from PostProfileView u where u.profileId = ? and level='N'")
	List<Long> findByUserIdForNational(long profileId);
}