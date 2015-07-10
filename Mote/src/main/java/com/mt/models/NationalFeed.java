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
 * The <code>NationalFeed</code> is the persistent class for the clpost_national database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="clpost_national")
public class NationalFeed {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name="post_id")
	private Post post;
	
	@OneToOne
	@JoinColumn(name="college_id")
	private College college;
	
	@Column(name="likes")
	private long likes;
	
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
	public User getUser() {
		return user;
	}
	
	/**
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 
	 * @return
	 */
	public Post getPost() {
		return post;
	}
	
	/**
	 * 
	 * @param post
	 */
	public void setPost(Post post) {
		this.post = post;
	}
	
	/**
	 * 
	 * @return
	 */
	public College getCollege() {
		return college;
	}
	
	/**
	 * 
	 * @param college
	 */
	public void setCollege(College college) {
		this.college = college;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getLikes() {
		return likes;
	}
	
	/**
	 * 
	 * @param likes
	 */
	public void setLikes(long likes) {
		this.likes = likes;
	}	
}