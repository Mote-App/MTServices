package com.cl.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cl.models.SchoolFeed;


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
}
