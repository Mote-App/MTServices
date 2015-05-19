package views;

import java.util.List;

public class NewPostDto {

	String			postType;			
	long 			userId;
	String 			caption;
	List<Integer> 	tags;
	String			customTags; //Comma separated tags.
	
	
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public List<Integer> getTags() {
		return tags;
	}
	public void setTags(List<Integer> tags) {
		this.tags = tags;
	}
	public String getCustomTags() {
		return customTags;
	}
	public void setCustomTags(String customTags) {
		this.customTags = customTags;
	}

	
}
