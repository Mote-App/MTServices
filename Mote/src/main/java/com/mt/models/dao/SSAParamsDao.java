package com.mt.models.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
	//private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//An EntityManager will be automatically injected from entityManagerFactory setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;
	
	/**
	 * 
	 * This works:  SELECT * FROM motedb.ssa_coefficient_parameters as S WHERE S.id = 1;
	 * 
	 * @param ssaParamsId
	 * @return
	 */
	public SSAParams getSSAParams(long ssaParamsId) {
		return (SSAParams)_entityManager.createQuery("SELECT S FROM SSAParams as S WHERE S.id = :ssaParamsId")
		//return (SSAParams)_entityManager.createQuery("FROM SSAParams WHERE id = :ssaParamsId")
				.setParameter("ssaParamsId", ssaParamsId)
				.setMaxResults(1)
				.getSingleResult();
	}
}