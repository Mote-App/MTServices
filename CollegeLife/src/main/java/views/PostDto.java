package views;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDto {
	
	long 			postId;
	String 			postingDate;
	String 			postImg;
	long 			likes; 
	String 			caption;
	int				progressInd; //default value
	int 			schoolId;
	String 			SchoolName;
	String 			SchoolImg;
	String 			tagCategory; //Each post will only have one category and many sub categories
	List<String> 	tags;
	List<String>	customTags;
	
	public PostDto(){
		tags = new ArrayList<String>();
	}
	
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public String getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	public String getPostImg() {
		return postImg;
	}
	public void setPostImg(String postImg) {
		this.postImg = postImg;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return SchoolName;
	}
	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}
	public String getSchoolImg() {
		return SchoolImg;
	}
	public void setSchoolImg(String schoolImg) {
		SchoolImg = schoolImg;
	}

	public String getTagCategory() {
		return tagCategory;
	}

	public void setTagCategory(String tagCategory) {
		this.tagCategory = tagCategory;
	}

	public List<String> getCustomTags() {
		return customTags;
	}

	public void setCustomTags(List<String> customTags) {
		this.customTags = customTags;
	}

	public int getProgressInd() {
		return progressInd;
	}

	public void setProgressInd(int progressInd) {
		this.progressInd = progressInd;
	}
	
	
}
