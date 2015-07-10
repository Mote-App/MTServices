package views;

import java.util.List;

/**
 * The <code>NewPostDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class NewPostDto {
	String		  postType;			
	long 		  userId;
	String 		  caption;
	List<Integer> tags;
	String		  customTags; //Comma separated tags.
	
	/**
	 * 
	 * @return
	 */
	public String getPostType() {
		return postType;
	}
	
	/**
	 * 
	 * @param postType
	 */
	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getUserId() {
		return userId;
	}
	
	/**
	 * 
	 * @param userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
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
	public List<Integer> getTags() {
		return tags;
	}
	
	/**
	 * 
	 * @param tags
	 */
	public void setTags(List<Integer> tags) {
		this.tags = tags;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCustomTags() {
		return customTags;
	}
	
	/**
	 * 
	 * @param customTags
	 */
	public void setCustomTags(String customTags) {
		this.customTags = customTags;
	}	
}