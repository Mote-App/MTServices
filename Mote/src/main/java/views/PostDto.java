package views;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>PostDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class PostDto {
	long 		   postId;
	String 		   postingDate;
	String 		   postImg;
	long 		   likes; 
	String 		   caption;
	int			   progressInd; //default value
	String 		   tagCategory; //Each post will only have one category and many sub categories -discarded
	List<Long> 	   tags;
	//List<String> customTags;
	boolean		   likeDone = false;
	boolean		   viewDone = false;
	boolean 	   isMediaPost = false;
	
	/**
	 * 
	 */
	public PostDto() {
		tags = new ArrayList<Long>();
	}
	
	/**
	 * 
	 * @return
	 */
	public long getPostId() {
		return postId;
	}
	
	/**
	 * 
	 * @param postId
	 */
	public void setPostId(long postId) {
		this.postId = postId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPostingDate() {
		return postingDate;
	}
	
	/**
	 * 
	 * @param postingDate
	 */
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPostImg() {
		return postImg;
	}
	
	/**
	 * 
	 * @param postImg
	 */
	public void setPostImg(String postImg) {
		this.postImg = postImg;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Long> getTags() {
		return tags;
	}
	
	/**
	 * 
	 * @param tags
	 */
	public void setTags(List<Long> tags) {
		this.tags = tags;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getLikes() {
		return likes;
	}
	
	/**
	 * 
	 * @param likes
	 */
	public void setLikes(long likes) {
		this.likes = likes;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCaption() {
		return caption;
	}
	
	/**
	 * 
	 * @param caption
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTagCategory() {
		return tagCategory;
	}
	
	/**
	 * 
	 * @param tagCategory
	 */
	public void setTagCategory(String tagCategory) {
		this.tagCategory = tagCategory;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getProgressInd() {
		return progressInd;
	}
	
	/**
	 * 
	 * @param progressInd
	 */
	public void setProgressInd(int progressInd) {
		this.progressInd = progressInd;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isLikeDone() {
		return likeDone;
	}
	
	/**
	 * 
	 * @param likeDone
	 */
	public void setLikeDone(boolean likeDone) {
		this.likeDone = likeDone;
	}
	
	public boolean isViewDone() {
		return viewDone;
	}
	
	public void setViewDone(boolean viewDone) {
		this.viewDone = viewDone;
	}
	
	public boolean isMediaPost() {
		return isMediaPost;
	}
	
	public void setMediaPost(boolean isMediaPost) {
		this.isMediaPost = isMediaPost;
	}	
}