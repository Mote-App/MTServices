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
	long profileId;
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
	public long getProfileId() {
		return profileId;
	}
	
	/**
	 * 
	 * @param profileId
	 */
	public void setProfileIdId(long profileId) {
		this.profileId = profileId;
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
	
	public String toString(){
		return " profileId : " + profileId + ",  postId : " + postId + ",  likeCount: " + likeCount + ",  level: " + level;
	}
}