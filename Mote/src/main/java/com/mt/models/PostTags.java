package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


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
	
	
	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	
	
}
