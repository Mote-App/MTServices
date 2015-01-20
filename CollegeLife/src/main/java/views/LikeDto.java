package views;

public class LikeDto {

	long likeCount;
	long postId;
	long userId;
	String level;
	
	public long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
}
