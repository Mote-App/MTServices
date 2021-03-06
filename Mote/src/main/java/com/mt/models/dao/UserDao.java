package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mt.models.User;

/**
 * The <code>UserDao</code> ...
 * <br />
 * This class is used to access data for the User entity.
 * Repository annotation allows the component scanning support to find and configure
 * the DAO without any XML configuration and also provide the Spring exception translation.
 * 
 * Since we've setup setPackagesToScan and transaction manager on DatabaseConfig, any bean
 * method annotated with Transactional will cause Spring to magically call begin() and commit()
 * at the start/end of the method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class UserDao {
	// An EntityManager will be automatically injected from entityManagerFactory setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Method create
	 * <br/>
	 * Save the user in the database.
	 */
	public User create(User user) {
		log.info("Please create or persist the following user:");
		log.info(user.toString());
		
		_entityManager.persist(user);
		
		return user;
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public User getUserbyName(String userName) {
		log.info("Get user by profile username: " + userName);
		
		User user = (User)_entityManager.createQuery("SELECT P FROM User P where P.profileUserName = :userName")
				.setParameter("userName", userName)
				.getSingleResult();
		
		log.info(user.toString());
		
		return user;
	}
	
	/**
	 * 
	 * @param collegeId
	 * @return
	 */
	public List<User> getUsersbySchool(long collegeId) {
		log.info("Get list of users by college [collegeId]: " + collegeId);
		
		List<User> users = _entityManager.createQuery("SELECT P FROM User P where P.profileCollege.collegeId = :collegeId")
				.setParameter("collegeId", collegeId)
				.getResultList();
		
		log.info("List of Users: " + users);
		
		return users;
	}
	
	/**
	 * get user
	 */
	public User getUser(Long userId) {
		return (User)_entityManager.createQuery("SELECT P FROM User P where P.profileId = :userId")
				.setParameter("userId", userId)
				.getSingleResult();
	}
	
	/**
	 * Method delete
	 * <br/>
	 * Delete the user from the database.
	 */
	public void delete(User user) {
		if(_entityManager.contains(user))
			_entityManager.remove(user);
		else
			_entityManager.remove(_entityManager.merge(user));
		
		return;
	}
	
	/**
	 * Method getAll
	 * <br/>
	 * Return all the users stored in the database.
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return _entityManager.createQuery("from User").getResultList();
	}
	
	/**
	 * Method getByEmail
	 * <br/>
	 * Return the user having the passed email.
	 */
	public User getByEmail(String email) {
		return (User) _entityManager.createQuery("from User where email = :email")
				.setParameter("email", email)
				.getSingleResult();
	}
	
	/**
	 * Method getByEmail
	 * <br/>
	 * Return the user having the passed email.
	 */
	public User getByName(String userName) {
		return (User) _entityManager.createQuery("from User where userName = :userName")
				.setParameter("userName", userName)
				.getSingleResult();
	}
	
	/**
	 * Method getById
	 * <br/>
	 * Return the user having the passed id.
	 */
	public User getById(Long id) {
		return _entityManager.find(User.class, id);
	}
	
	/**
	 * Method update
	 * <br/>
	 * Update the passed user in the database.
	 */
	public void update(User user) {
		_entityManager.merge(user);
		
		return;
	}
	
	/**
	 * Cr - the number of 'people' registered from that school.
	 * 
	 * @param collegeId
	 * @return
	 */
	public Long getCr(long collegeId) {
		log.info("Get Cr - the number of 'people' registered from that school [collegeId]: " + collegeId);
		
		return (Long)_entityManager.createQuery("SELECT COUNT(U.profileId) AS Cr FROM User as U WHERE U.profileCollege.collegeId = :collegeId")
				.setParameter("collegeId", collegeId)
				.getSingleResult();
	}
	
	/**
	 * CrIdealAvg - the average number of 'people' registered per school.
	 * CrIdealAvg - the average number of 'people' registered by the total number of register schools.
	 * 
	 * 
	 * @param collegeId
	 * @return
	 */
	public Long getCrIdealAvg() {
		log.info("Get CrIdealAvg - the average number of 'people' registered by the total number of register schools.");
		
		List<Long> collegeCnts = _entityManager.createQuery("SELECT DISTINCT U.profileCollege.collegeId FROM User U").getResultList();
		Long userCnt = (Long) _entityManager.createQuery("SELECT COUNT(U.profileId) FROM User U").getSingleResult();
		Long CrIdealAvg = 0L;
		
		if(collegeCnts.size() > 0) {
			CrIdealAvg = (Long)(userCnt/collegeCnts.size());
		} else {
			log.info("Cannot calculate CrIdealAvg because all mote colleges have zero student");
		}
		
		return CrIdealAvg;
	}
}