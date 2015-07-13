package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mt.models.PostTags;

/**
 * The <code>SchoolFeedDao</code> ...
 * 
 * @author gibranecastillo
 *
 */
@Repository
@Transactional
public class SchoolFeedDao {
	@PersistenceContext
	private EntityManager _entityManager;
	
	
	/**
	 * 
	 * @param collegeId
	 * @param tagIds
	 * @return
	 */
	public List<PostTags> getSchoolFeedsByCollegeAndTags(long collegeId, List<Long> tagIds) {
		List<PostTags> feeds = _entityManager.createQuery("SELECT PT FROM PostTags PT JOIN User U on PT.post.profileId = U.profileId where U.profileCollege.collegeId = :collegeId and PT.postSchoolPromote = true and PT.tagId IN :tagIds")
				.setParameter("collegeId", collegeId)
				.setParameter("tagIds", tagIds)
				.getResultList();
		
		return feeds;
	}
	
	/**
	 * 
	 * @param tagIds
	 * @return
	 */
	public List<PostTags> getSchoolFeedsByTags( List<Long> tagIds) {
		List<PostTags> feeds = _entityManager.createQuery("SELECT PT FROM PostTags PT JOIN User U on PT.post.profileId = U.profileId where PT.postSchoolPromote = true and PT.tagId IN :tagIds")
				.setParameter("tagIds", tagIds)
				.getResultList();
		
		return feeds;
	}
}