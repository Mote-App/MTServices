package com.mt.models.tmp;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tag database table.
 * 
 */
@Entity
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tag_id")
	private int tagId;

	@Column(name="tag_description")
	private String tagDescription;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="tag")
	private List<Post> posts;

	public Tag() {
	}

	public int getTagId() {
		return this.tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTagDescription() {
		return this.tagDescription;
	}

	public void setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setTag(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setTag(null);

		return post;
	}

}