package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 * @param collegeId
	 * @param tagIds
	 * @return
	 */
	public List<PostTags> getSchoolFeedsByCollegeAndTags(List<Long> collegeIds, List<Long> tagIds) {
		log.info("List of School Feeds by College ["+ collegeIds + "] and Tags [" + tagIds + "]");
		
		List<PostTags> feeds = _entityManager.createQuery("SELECT PT FROM PostTags PT JOIN User U on PT.post.profileId = U.profileId where U.profileCollege.collegeId IN :collegeIds and PT.postSchoolPromote = true and PT.tagId IN :tagIds")
				.setParameter("collegeIds", collegeIds)
				.setParameter("tagIds", tagIds)
				.getResultList();
		
		log.info("List of PostTags: " + feeds);
		
		return feeds;
	}
	
	/**
	 * 
	 * @param tagIds
	 * @return
	 */
	public List<PostTags> getSchoolFeedsByTags(List<Long> tagIds) {
		log.info("List of School Feeds by Tags [" + tagIds + "]");
		
		List<PostTags> feeds = _entityManager.createQuery("SELECT PT FROM PostTags PT JOIN User U on PT.post.profileId = U.profileId where PT.postSchoolPromote = true and PT.tagId IN :tagIds")
				.setParameter("tagIds", tagIds)
				.getResultList();
		
		log.info("List of PostTags: " + feeds);
		
		return feeds;
	}
}