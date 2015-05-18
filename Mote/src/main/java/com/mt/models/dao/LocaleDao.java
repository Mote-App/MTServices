package com.mt.models.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mt.models.Locale;

@Repository
@Transactional
public class LocaleDao {

	//An EntityManager will be automatically injected from entityManagerFactory setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;

	public Locale getLocale(Long localeId){
		
		Locale locale =  (Locale) _entityManager.createQuery("SELECT L from Locale L where L.localeId = :localeId")
				.setParameter("localeId", localeId)
				.getSingleResult();
		
		return locale;
	}
		
}
