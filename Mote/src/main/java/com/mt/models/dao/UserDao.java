package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mt.models.User;

/**
 * Class UserDao
 * <br />
 * This class is used to access data for the User entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class UserDao{
	
	// ==============
	// PRIVATE FIELDS
	// ==============
	
	// An EntityManager will be automatically injected from entityManagerFactory
	// setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;
	
	// ==============
	// PUBLIC METHODS
	// ==============
	
	/**
	 * Method create
	 * <br/>
	 * Save the user in the database.
	 */
	public User create(User user) {
		_entityManager.persist(user);
		
		return user;
	}
	
	public User getUserbyName(String userName){
		User user = (User)_entityManager.createQuery("SELECT P FROM User P where P.userName = :userName")
				.setParameter("userName", userName)
				.getSingleResult();
		
		return user;
	}
	
	public List<User> getUsersbySchool(long schoolId){
		List<User> users = _entityManager.createQuery("SELECT P FROM User P where P.college.id = :schoolId")
				.setParameter("schoolId", schoolId)
				.getResultList();
		
		return users;
	}
	
	/**
	 * get user
	 */
	public User getUser(Long userId){
		return (User)_entityManager.createQuery("SELECT P FROM User P where P.id = :userId")
				.setParameter("userId", userId)
				.getSingleResult();
	}
	
	/**
	 * Method delete
	 * <br/>
	 * Delete the user from the database.
	 */
	public void delete(User user) {
		if (_entityManager.contains(user))
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
	 * New Data Model
	 * SELECT COUNT(A.idcollege) FROM profile A JOIN profile_has_post B ON A.idprofile = B.idprofile WHERE A.idcollege = :collegeId 
	 * 
	 * @param collegeId
	 * @return
	 */
	public Long getCr(long collegeId) {
		return (Long)_entityManager.createQuery("SELECT COUNT(user_id) AS Cr FROM User as U WHERE college_id = :collegeId")
				.setParameter("collegeId", collegeId)
				.getSingleResult();
	}
	
	/**
	 * CrIdealAvg - the average number of 'people' registered per school.
	 * New Data Model
	 * SELECT A.idcollege, COUNT(B.idprofile) FROM college A JOIN profile B ON A.idprofile = B.idprofile GROUP BY A.idcollege
	 * 
	 * select sum(TempTable.ProfileTotal)/TempTable.count(*) From (select a.idcollege College, count(b.idprofile) ProfileTotal from college A join profile B on a.idcollege=b.college) TempTable
	 * 
	 * declare cursor c_average
select a.idcollege College, count(b.idprofile) ProfileTotal from college A join profile B on a.idcollege=b.college;


SchoolNumber Int;
TotalStudents int;

For each row in c_average
  SchoolNumber ++;
  TotalStudents=  profileTotal + TotalStudents;
End For

Average= TotalStudetns/SchoolNumber;

Return Average
	 * 
	 * @param collegeId
	 * @return
	 */
	public Long getCrIdealAvg() {
		return (Long)_entityManager.createQuery("SELECT COUNT(user_id) AS Cr FROM User as U WHERE college_id = :collegeId")
				.getSingleResult();
	}
}