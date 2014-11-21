package views;

public class PostsDto {

	PostDto popularPost;
	PostDto currentPost;
	PostDto mostRecentPost;
	
	public PostsDto(){
		popularPost 	= new PostDto();
		currentPost 	= new PostDto();
		mostRecentPost	= new PostDto();

	}

	public PostDto getPopularPost() {
		return popularPost;
	}

	public void setPopularPost(PostDto popularPost) {
		this.popularPost = popularPost;
	}

	public PostDto getCurrentPost() {
		return currentPost;
	}

	public void setCurrentPost(PostDto currentPost) {
		this.currentPost = currentPost;
	}

	public PostDto getMostRecentPost() {
		return mostRecentPost;
	}

	public void setMostRecentPost(PostDto mostRecentPost) {
		this.mostRecentPost = mostRecentPost;
	}
	
	
	
}
