package views;

/**
 * The <code>PostsDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class PostsDto {
	PostDto popularPost;
	PostDto currentPost;
	PostDto mostRecentPost;
	
	/**
	 * 
	 */
	public PostsDto() {
		popularPost    = new PostDto();
		currentPost    = new PostDto();
		mostRecentPost = new PostDto();
	}
	
	/**
	 * 
	 * @return
	 */
	public PostDto getPopularPost() {
		return popularPost;
	}
	
	/**
	 * 
	 * @param popularPost
	 */
	public void setPopularPost(PostDto popularPost) {
		this.popularPost = popularPost;
	}
	
	/**
	 * 
	 * @return
	 */
	public PostDto getCurrentPost() {
		return currentPost;
	}
	
	/**
	 * 
	 * @param currentPost
	 */
	public void setCurrentPost(PostDto currentPost) {
		this.currentPost = currentPost;
	}
	
	/**
	 * 
	 * @return
	 */
	public PostDto getMostRecentPost() {
		return mostRecentPost;
	}
	
	/**
	 * 
	 * @param mostRecentPost
	 */
	public void setMostRecentPost(PostDto mostRecentPost) {
		this.mostRecentPost = mostRecentPost;
	}	
}