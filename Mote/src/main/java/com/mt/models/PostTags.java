package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The <code>PostTags</code> is the persistent class for the post_has_tags database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="post_has_tag")
@IdClass(PostTagAssociationId.class)
public class PostTags {
	@Id
	@Column(name="post_post_id")
	private long postId;
	
	@Id
	@Column(name="tag_tag_id")
	private long tagId;
	
	@ManyToOne
	@JoinColumn(name = "post_post_id", updatable = false, insertable = false)
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "tag_tag_id", updatable = false, insertable = false)
	private Tag tag;
	
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
	public long getTagId() {
		return tagId;
	}
	
	/**
	 * 
	 * @param tagId
	 */
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}	
	
	
}