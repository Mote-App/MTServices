package com.cl.feeds.dao;

import java.util.List;

import org.apache.log4j.Category;
import org.hibernate.SessionFactory;

/**
 *  DAO implementation built using Hibernate
 *
 *  @author Gibran E. Castillo
 */
public class HibernateSpringCollegeLifeDao  implements CollegeLifeDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<School> getSchools() throws DAOException {
		// TODO
		//
		// Execute the HQL "from com.cl.feeds.pojos.Post"
		return sessionFactory.getCurrentSession().createQuery("from com.cl.feeds.pojos.Post").list();
	}
	
	public List<School> getSchools(String theSchool)throws DAOException{
		return getSchools(theSchool, CollegeLifeDao.SORT_BY_TITLE);
	}

	
	public List<School> getSchools(String theSchool,
			int sortBy) throws DAOException {
		// TODO
		//
		// Notice how we execute the Named Query with a name parameter using HQL
    	return sessionFactory.getCurrentSession().getNamedQuery("School").setParameter("theSchool", theSchool).list();	
	}
	
	public School getSchool(String theSchoolId) throws DAOException {
		int schoolId =0;
		try {
			schoolId = Integer.parseInt(theSchoolId);
		} catch(NumberFormatException e) {
			// pending!!
		}
		
		return this.getSchool(schoolId);
	}
	
	public School getSchool(int schoolId) throws DAOException {
		// TODO
		//
		// Notice how we execute the Named Query with a named parameter using HQL and return the result
		return (School) sessionFactory.getCurrentSession().getNamedQuery("School").setParameter("theSchoolId", schoolId).uniqueResult();
	}
}