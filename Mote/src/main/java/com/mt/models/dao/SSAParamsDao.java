package com.mt.models.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mt.models.SSAParams;

/**
 * The <code>SSAParamsDao</code> ...
 * 
 * @author gibranecastillo
 *
 */
@Repository
public class SSAParamsDao {
	//An EntityManager will be automatically injected from entityManagerFactory setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 * This works:  SELECT * FROM motedb.ssa_coefficient_parameters as S WHERE S.id = 1;
	 * 
	 * @param ssaParamsId
	 * @return
	 */
	public SSAParams getSSAParams(Long ssaParamsId) {
		
		log.info("Get SSA Coefficient Parameters " + ssaParamsId);
		
		return (SSAParams)_entityManager.createQuery("SELECT S FROM SSAParams S WHERE S.id = :ssaParamsId")
				.setParameter("ssaParamsId", ssaParamsId)
				.getSingleResult();
	}
}