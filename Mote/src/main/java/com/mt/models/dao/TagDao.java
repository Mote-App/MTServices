package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mt.models.Tag;

/**
 * The <code>TagDao</code> ...
 * 
 * @author gibranecastillo
 *
 */
@Repository
@Transactional
public class TagDao {
	//An EntityManager will be automatically injected from entityManagerFactory
	// setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;
	
	/**
	 * Method getAll
	 * <br/>
	 * 
	 */
	/*@SuppressWarnings("unchecked")
	public List<Tag> getTags(String type) {
	    return _entityManager.createQuery("from Tag where tagType = :tagType")
	    		.setParameter("tagType", type)
	    		.getResultList();
	}*/
	
	/**
	 * 
	 * @return
	 */
	public List<Tag> getTags() {
		return _entityManager.createQuery("from Tag")
				.getResultList();
	}
	
	/**
	 * 
	 * @param tagId
	 * @return
	 */
	public Tag getTag(long tagId) {
		return (Tag)_entityManager.createQuery("from Tag where id = :tagId")
				.setParameter("tagId", tagId)
				.getSingleResult();
	}
}