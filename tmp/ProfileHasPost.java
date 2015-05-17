package com.mt.models.tmp;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the profile_has_post database table.
 * 
 */
@Entity
@Table(name="profile_has_post")
@NamedQuery(name="ProfileHasPost.findAll", query="SELECT p FROM ProfileHasPost p")
public class ProfileHasPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProfileHasPostPK id;

	private int likes;

	@Column(name="national_promote")
	private byte nationalPromote;

	@Column(name="school_promote")
	private byte schoolPromote;

	private int views;

	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="profile_id")
	private Profile profile;

	public ProfileHasPost() {
	}

	public ProfileHasPostPK getId() {
		return this.id;
	}

	public void setId(ProfileHasPostPK id) {
		this.id = id;
	}

	public int getLikes() {
		return this.likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public byte getNationalPromote() {
		return this.nationalPromote;
	}

	public void setNationalPromote(byte nationalPromote) {
		this.nationalPromote = nationalPromote;
	}

	public byte getSchoolPromote() {
		return this.schoolPromote;
	}

	public void setSchoolPromote(byte schoolPromote) {
		this.schoolPromote = schoolPromote;
	}

	public int getViews() {
		return this.views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}