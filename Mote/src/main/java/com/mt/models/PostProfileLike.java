package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The <code>PostProfileLike</code> is the persistent class for the post_user_like database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="post_user_like")
public class PostProfileLike {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="post_id")
	private long postId;
	
	@Column(name="profile_id")
	private long profileId;
	
	@Column(name="level")
	private String level;
	
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
	public long getPostId() {
		return postId;
	}
	
	/**
	 * 
	 * @param postId
	 */
	public void setPostId(long postId) {
		this.postId = postId;
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
	public String getLevel() {
		return level;
	}
	
	/**
	 * 
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}	
}