package views;

public class Posts {

	Post popularPost;
	Post currentPost;
	Post mostRecentPost;
	
	public Posts(){
		popularPost 	= new Post();
		currentPost 	= new Post();
		mostRecentPost	= new Post();

	}

	public Post getPopularPost() {
		return popularPost;
	}

	public void setPopularPost(Post popularPost) {
		this.popularPost = popularPost;
	}

	public Post getCurrentPost() {
		return currentPost;
	}

	public void setCurrentPost(Post currentPost) {
		this.currentPost = currentPost;
	}

	public Post getMostRecentPost() {
		return mostRecentPost;
	}

	public void setMostRecentPost(Post mostRecentPost) {
		this.mostRecentPost = mostRecentPost;
	}
	
	
	
}
