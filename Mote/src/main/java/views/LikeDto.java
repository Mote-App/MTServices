package views;

/**
 * The <code>LikeDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class LikeDto {
	long likeCount;
	long postId;
	long userId;
	String level;
	
	/**
	 * 
	 * @return
	 */
	public long getLikeCount() {
		return likeCount;
	}
	
	/**
	 * 
	 * @param likeCount
	 */
	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
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