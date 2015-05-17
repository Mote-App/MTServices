package com.mt.models;

import java.io.Serializable;

public class PostTagAssociationId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long postId;
	private long tagId;
	
	public int hashCode() {
	    return (int)(postId + tagId);
	}
	
	public boolean equals(Object object) {
	    if (object instanceof PostTagAssociationId) {
	    	PostTagAssociationId otherId = (PostTagAssociationId) object;
	      return (otherId.postId == this.postId) && (otherId.tagId == this.tagId);
	    }
	    return false;
	}
	 
}
