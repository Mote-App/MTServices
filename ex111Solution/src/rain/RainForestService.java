package rain;

/**
 *  
 *  Main service interface
 *
 *  @author 517 Development Team
 */


import java.util.List;

public interface RainForestService {
	/**
	 *  Get a sorted list of video recording categories from the database.
	 */

	public List<Category> getVideoCategories();

	/**
	 *  Get a sorted list of video recordings from the database for the given category. <br>
	 *  The list is sorted by title.
	 *
	 *	@param theCategory the category
	 *  @return a list of VideoRecording objects
	 *	@see #getVideoRecordings(String theCategory, int sortBy)
	 */
	public List<VideoRecording> getVideoRecordings(String theCategory) ;

		
	/**
	 *  Get a sorted list of video recordings from the database for the given category. <br>
	 *  The sort order is ascending.
	 *
	 *  @param theCategory the category name
	 *  @param sortBy the key to sort by
	 *  @return a list of sorted <code>VideoRecording</code> objects
	 *	@see rain.VideoDao#SORT_BY_TITLE
	 *	@see rain.VideoDao#SORT_BY_PRICE
	 *	@see rain.VideoDao#SORT_BY_STOCK_COUNT
	 */
	public List<VideoRecording> getVideoRecordings(String theCategory, int sortBy);
	/**
	 *  Returns a video recording based on the id
	 *
	 *	@param recordingId the recording id
	 *	@return a <code>VideoRecording</code> object for the given recording id
	 * 	@see #getVideoRecording
	 */
	public VideoRecording getVideoRecording(String recordingId);

	/**
	 *  Returns a video recording based on the id
	 *
	 */
	public VideoRecording getVideoRecording(int recordingId);

	
}
