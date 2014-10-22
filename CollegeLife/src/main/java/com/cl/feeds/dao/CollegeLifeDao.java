package com.cl.feeds.dao;

import java.util.List;

import org.apache.log4j.Category;

/**
 *  This interface defined the data access methods for the college life database.
 *  Methods are available to get a list of ...   <p>
 *
 *
 *  @author Gibran E. Castillo
 */
public interface CollegeLifeDao  {
	/**
	 * Flag to sort by title
	 */
	public static final int SORT_BY_NAME = 0;
	
	/**
	 * Flag to sort by price
	 */
	public static final int SORT_BY_ID = 1;
	
	/**
	 * Flag to sort by stock count
	 */
	public static final int SORT_BY_POPULARITY = 2;
	
	/**
	 *  Get a list of schools from the database
	 *
	 *  @return a list of schools
	 *  @throws DAOException error accessing the data source
	 */
	public List<School> getSchools() throws DAOException;
	
	/**
	 *  Get a sorted list of schools from the database for the given school name. <br>
	 *  The list is sorted by name.
	 *
	 *	@param theSchool the school name
	 *  @return a list of School objects
	 *  @throws DAOException error accessing the data source
	 *	@see #getSchools(String theSchool, int sortBy)
	 */
	public List<School> getSchools(String theSchool) throws DAOException;
	
	/**
	 *  Get a sorted list of schools from the database for the given school name. <br>
	 *  The sort order is ascending.
	 *
	 *  @param theSchool the school name
	 *  @param sortBy the key to sort by
	 *  @return a list of sorted <code>School</code> objects
	 *  @throws DAOException error accessing the data source
	 *	@see #SORT_BY_NAME
	 *	@see #SORT_BY_ID
	 *	@see #SORT_BY_POPULARITY
	 */
	public List<School> getSchools(String theSchool, int sortBy) throws DAOException;
	
	/**
	 *  Returns a school based on the id
	 *
	 *	@param schoolId the school id
	 *	@return a <code>School</code> object for the given recording id
	 *  @throws DAOException error accessing the data source
	 * 	@see #getSchool
	 */
	public School getSchool(String schoolId)throws DAOException;
	
	/**
	 *  Returns a school based on the id
	 *
	 *  @throws DAOException error accessing the data source
	 */
	public School getSchool(int schoolId) throws DAOException;
}