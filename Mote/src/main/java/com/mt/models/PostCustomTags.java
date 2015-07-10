package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The <code>PostCustomTags</code> is the persistent class for the clpost_custom_tags database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="clpost_custom_tags")
public class PostCustomTags {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="post_id")
	private long postId;
	
	/*@ManyToOne
	private Post post;
	*/
	@Column(name="tag_name")
	private String tagName;
	
	@Column(name="user_id")
	private long userId;
	
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
	public String getTagName() {
		return tagName;
	}
	
	/**
	 * 
	 * @param tagName
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getUserId() {
		return userId;
	}
	
	/**
	 * 
	 * @param userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	/*public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}*/
}