package com.mt.models.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mt.models.Locale;

/**
 * The <code>LocaleDao</code> ...
 * 
 * @author gibranecastillo
 *
 */
@Repository
@Transactional
public class LocaleDao {
	//An EntityManager will be automatically injected from entityManagerFactory setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 * @param localeId
	 * @return
	 */
	public Locale getLocale(Long localeId) {
		log.info("Get locale information for localeId: " + localeId);
		
		Locale locale = (Locale) _entityManager.createQuery("SELECT L from Locale L where L.localeId = :localeId")
				.setParameter("localeId", localeId)
				.getSingleResult();
		
		log.info(locale.toString());
		
		return locale;
	}
}