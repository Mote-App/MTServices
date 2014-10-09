package com.cl.feeds.pojos;

import java.io.Serializable;

public class Feed implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String feedType;
	protected Post post;
	
	public Feed() {
		this.feedType = "unknown";
		this.post = new Post();
	}
	
	public Feed(String feedType, Post post) {
		this.feedType = feedType;
		this.post = post;
	}
	
	public String getFeedType() {
		return feedType;
	}
	
	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}
	
	public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	
	
	/**
	 *  Returns a string representation of the Feed.  It
	 *  includes the feed type and post caption. <p>
	 *
	 *  The string has the following format:
	 *
	 *   <pre>
	 *    feed type, post caption
	 *   </pre>
	 */
	public String toString() {
		return this.feedType + ",  " + this.post.getCaption();
	}
}