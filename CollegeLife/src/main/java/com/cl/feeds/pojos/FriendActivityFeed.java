package com.cl.feeds.pojos;

public class FriendActivityFeed extends Friend {
	private static final long serialVersionUID = 1L;
	
	private String postType;
	private Feed feedList[];
	
	public FriendActivityFeed() {
		//
	}
	
	public String getPostType() {
		return postType;
	}
	
	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	public Feed[] getFeedList() {
		return feedList;
	}
	
	public void setFeedList(Feed[] feedList) {
		this.feedList = feedList;
	}
	
	@Override
	public Post getPost() {
		Feed feedTemp;
		Post postTemp;
		int total = 0;
		
		if(feedList == null) {
			return new Post();
		}
		
		for(int i = 0; i < feedList.length; i++) {
			feedTemp = feedList[i];
			postTemp = feedTemp.getPost();
			total++;
		}
		
		return new Post(total);
	}
	
	/*
	@Override
	public void setPost(Post post) {
		// TODO Auto-generated method stub
	}*/
}