package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mt.models.College;

/**
 * The <code>CollegeDao</code> ...
 * 
 * @author gibranecastillo
 *
 */
@Repository
@Transactional
public class CollegeDao {
	//An EntityManager will be automatically injected from entityManagerFactory setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * This method get All the Colleges listed in the motedb.
	 * 
	 * @return all the colleges stored in the Mote database.
	 */
	@SuppressWarnings("unchecked")
	public List<College> getAll() {
		log.info("Get All the Colleges listed in the motedb.college sql table.");
		
		return _entityManager.createQuery("from College").getResultList();
	}
	
	/**
	 * 
	 * @param collegeId
	 * @return
	 */
	public College getCollege(long collegeId) {
		log.info("Get college information for collegeId: " + collegeId);
		
		College college = (College)_entityManager.createQuery("SELECT C FROM College C where C.collegeId = :collegeId")
				.setParameter("collegeId", collegeId)
				.getSingleResult();
		
		log.info(college.toString());
		
		return college;
	}
}