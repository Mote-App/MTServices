package com.mt.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mt.models.User;
import com.mt.models.UserFriends;

/**
 * The <code>UserFriendsDao</code> ...
 * 
 * @author gibranecastillo
 *
 */
@Repository
@Transactional
public class UserFriendsDao {
	//An EntityManager will be automatically injected from entityManagerFactory
	// setup on DatabaseConfig class.
	@PersistenceContext
	private EntityManager _entityManager;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserDao _userDao;
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Long> getFriends(long userId) {
		List<Long> friends = _entityManager.createQuery("SELECT P.friend.profileId FROM UserFriends P where P.profileId = :userId")
				.setParameter("userId", userId)
				.getResultList();
		
		return friends;
	}
	
	/**
	 * This DAO layer should return all users in system except for the requested user,
	 * along with flag indicating if the user is friend of requested user
	 * 
	 * @param userId
	 * @return
	 */
	public List<User> getFriendsProfile(long userId) {
		List<User> friends = _entityManager.createQuery("SELECT P.friend FROM UserFriends P where P.profileId = :userId")
				.setParameter("userId", userId)
				.getResultList();
		
		return friends;
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<User> getUsersProfile(long userId) {
		List<User> users = _entityManager.createQuery("SELECT U FROM User U where U.profileId <> :userId")
				.setParameter("userId", userId)
				.getResultList();
		
		return users;
	}
	
	/**
	 * 
	 * @param profileId
	 * @param friendId
	 */
	public void removeFriend(Long profileId, Long friendId) {
		UserFriends userFriend = (UserFriends)_entityManager.createQuery("SELECT P FROM UserFriends P where P.profileId = :userId AND P.friend.profileId = :friendId")
				.setParameter("userId", profileId)
				.setParameter("friendId", friendId)
				.getSingleResult();
		
		_entityManager.remove(userFriend);
	}
	
	/**
	 * 
	 * @param profileId
	 * @param friendId
	 */
	public void addFriend(Long profileId, Long friendId) {
		UserFriends userFriend = new UserFriends();
		userFriend.setProfileId(profileId);
		
		User friend = _userDao.getUser(friendId);
		userFriend.setFriend(friend);
		
		_entityManager.persist(userFriend);
	}
}