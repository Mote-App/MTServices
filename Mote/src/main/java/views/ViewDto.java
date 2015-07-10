package views;

/**
 * The <code>ViewDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class ViewDto {
	long viewCount;
	long postId;
	long userId;
	String level;
	
	/**
	 * 
	 * @return
	 */
	public long getViewCount() {
		return viewCount;
	}
	
	/**
	 * 
	 * @param viewCount
	 */
	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
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
	public String getLevel() {
		return level;
	}
	
	/**
	 * 
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}	
}