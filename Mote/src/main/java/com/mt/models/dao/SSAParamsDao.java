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
@Transactional
public class SSAParamsDao {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//An EntityManager will be automatically injected from entityManagerFactory setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;
	
	/**
	 * 
	 * @param ssaParamsId
	 * @return
	 */
	public SSAParams getSSAParams() {
		log.info("*********************************");
		log.info("_entityManager: " + _entityManager);
		log.info("*********************************");
		
		return (SSAParams)_entityManager.createQuery("FROM SSAParams")
				.getSingleResult();
	}
	
	/**
	 * 
	 * @param ssaParamsId
	 * @return
	 */
	/*public SSAParams getSSAParams(long ssaParamsId) {
		return (SSAParams)_entityManager.createQuery("FROM SSAParams WHERE id = :ssaParamsId")
				.setParameter("ssaParamsId", ssaParamsId)
				.getSingleResult();
	}*/
	
	/**
	 * 
	 * @return
	 */
	/*public List<SSAParams> getSSAParamsList() {
		return _entityManager.createQuery("FROM SSAParams")
				.getResultList();
	}*/
}