package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
	
	/**
	 * This method get All the Colleges listed in the motedb.
	 * 
	 * @return all the colleges stored in the Mote database.
	 */
	@SuppressWarnings("unchecked")
	public List<College> getAll() {
		return _entityManager.createQuery("from College").getResultList();
	}
	
	/**
	 * 
	 * @param collegeId
	 * @return
	 */
	public College getCollege(long collegeId) {
		College college = (College)_entityManager.createQuery("SELECT C FROM College C where C.collegeId = :collegeId")
				.setParameter("collegeId", collegeId)
				.getSingleResult();
		
		return college;
	}
}