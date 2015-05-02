package com.mt.models.tmp;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the post database table.
 * 
 */
@Entity
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="post_id")
	private int postId;

	@Column(name="post_caption")
	private String postCaption;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="post_date")
	private Date postDate;

	@Column(name="post_object_path")
	private String postObjectPath;

	//bi-directional many-to-one association to Tag
	@ManyToOne
	@JoinColumn(name="post_tag_id")
	private Tag tag;

	//bi-directional many-to-one association to Type
	@ManyToOne
	@JoinColumn(name="post_type_code")
	private Type type;

	//bi-directional many-to-one association to ProfileHasPost
	@OneToMany(mappedBy="post")
	private List<ProfileHasPost> profileHasPosts;

	public Post() {
	}

	public int getPostId() {
		return this.postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostCaption() {
		return this.postCaption;
	}

	public void setPostCaption(String postCaption) {
		this.postCaption = postCaption;
	}

	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getPostObjectPath() {
		return this.postObjectPath;
	}

	public void setPostObjectPath(String postObjectPath) {
		this.postObjectPath = postObjectPath;
	}

	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<ProfileHasPost> getProfileHasPosts() {
		return this.profileHasPosts;
	}

	public void setProfileHasPosts(List<ProfileHasPost> profileHasPosts) {
		this.profileHasPosts = profileHasPosts;
	}

	public ProfileHasPost addProfileHasPost(ProfileHasPost profileHasPost) {
		getProfileHasPosts().add(profileHasPost);
		profileHasPost.setPost(this);

		return profileHasPost;
	}

	public ProfileHasPost removeProfileHasPost(ProfileHasPost profileHasPost) {
		getProfileHasPosts().remove(profileHasPost);
		profileHasPost.setPost(null);

		return profileHasPost;
	}

}