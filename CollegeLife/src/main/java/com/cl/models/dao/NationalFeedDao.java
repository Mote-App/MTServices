package com.cl.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cl.models.NationalFeed;

@Repository
@Transactional
public class NationalFeedDao {

	@PersistenceContext
	private EntityManager _entityManager;

	public List<NationalFeed> getNationalFeeds(long collegeId){
		
		List<NationalFeed> feeds = _entityManager.createQuery("SELECT P FROM NationalFeed P where P.college.id = :collegeId")
		.setParameter("collegeId", collegeId)
		.getResultList();
		
		return feeds;
	}
	
	
}
