package com.mt.models.tmp;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the profile_has_post database table.
 * 
 */
@Embeddable
public class ProfileHasPostPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="profile_id", insertable=false, updatable=false)
	private int profileId;

	@Column(name="post_id", insertable=false, updatable=false)
	private int postId;

	public ProfileHasPostPK() {
	}
	public int getProfileId() {
		return this.profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public int getPostId() {
		return this.postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProfileHasPostPK)) {
			return false;
		}
		ProfileHasPostPK castOther = (ProfileHasPostPK)other;
		return 
			(this.profileId == castOther.profileId)
			&& (this.postId == castOther.postId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.profileId;
		hash = hash * prime + this.postId;
		
		return hash;
	}
}