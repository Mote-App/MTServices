package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mt.models.SchoolFeed;


@Repository
@Transactional
public class SchoolFeedDao {

	@PersistenceContext
	private EntityManager _entityManager;

	public List<SchoolFeed> getSchoolFeeds(long collegeId){
		
		List<SchoolFeed> feeds = _entityManager.createQuery("SELECT P FROM SchoolFeed P where P.college.id = :collegeId")
		.setParameter("collegeId", collegeId)
		.getResultList();
		
		return feeds;
	}
	
	public List<SchoolFeed> getSchoolFeedsByCollegeAndTags(long collegeId, List<Long> tagIds){
		
		List<SchoolFeed> feeds = _entityManager.createQuery("SELECT S FROM SchoolFeed S JOIN S.post P JOIN S.post.lstPostTags T where S.college.id = :collegeId and T.tagId IN :tagIds")
		.setParameter("collegeId", collegeId)
		.setParameter("tagIds", tagIds)
		.getResultList();
		
		return feeds;
	}
	
	public List<SchoolFeed> getSchoolFeedsByTags( List<Long> tagIds){
		
		List<SchoolFeed> feeds = _entityManager.createQuery("SELECT S FROM SchoolFeed S JOIN S.post P JOIN S.post.lstPostTags T where T.tagId IN :tagIds")
		.setParameter("tagIds", tagIds)
		.getResultList();
		
		return feeds;
	}

}
