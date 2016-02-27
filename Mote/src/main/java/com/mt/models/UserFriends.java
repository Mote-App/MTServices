package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The <code>UserFriends</code> is the persistent class for the profile_has_friend database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="profile_has_friend")
public class UserFriends {
	//TODO: Discuss with team and add id to avoid complexity of composite primary key.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="profile_id")
	private long profileId;
	
	//@Column(name="profile_friend_id")
	//private long profileFriendId;
	
	@OneToOne
	@JoinColumn(name="profile_friend_id")
	private User friend;
	
	/**
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getProfileId() {
		return profileId;
	}
	
	/**
	 * 
	 * @param profileId
	 */
	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}
	
	/**
	 * 
	 * @return
	 */
	public User getFriend() {
		return friend;
	}
	
	/**
	 * 
	 * @param friend
	 */
	public void setFriend(User friend) {
		this.friend = friend;
	}	
}