package rain;


import java.util.*;

import org.springframework.transaction.annotation.Transactional;


/**
 *  Implementation of service interface.
 *
 *  @author 517 Development Team
 */

public class RainForestServiceImpl implements RainForestService {

	
	/**
	 * Reference to VideoDao interface. Set via dependency injection
	 */
	private VideoDao videoDao;


	/**
	 * Setter method for VideoDao
	 * @param videoDao
	 */
	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}
	
	/**
	 *  Get a list of video categories from the database
	 *
	 *  @return a list of video categories as a typesafe collection
	 *  Handles DAOException generated by DAO
	 */
	
	@Transactional(readOnly=true)
	public List<Category> getVideoCategories() {
		
		List<Category> categories = null;
		try {

			// TODO
			//
			// Use the videoDao to get the video categories
			//
			// You are calling the method you have just implemented

			// .. Your call here
			
		
			categories = videoDao.getVideoCategories();
		} catch (DAOException e) {
			if(categories == null){
				categories = new ArrayList<Category>();
			}
		}
		
		return categories;
	}

	
	/**
	 *  Get a sorted list of video recordings from the database for the given category. <br>
	 *  The list is sorted by title.
	 *
	 *	@param theCategory the category
	 *  @return a typesafe list of VideoRecording objects
	 *  <br/>Handles DAOException generated by VideoDao
	 *	@see #getVideoRecordings(String theCategory, int sortBy)
	 */
	
	@Transactional(readOnly=true)
	public List<VideoRecording> getVideoRecordings(String theCategory) {
		List<VideoRecording> recordings = null;
		try {
			recordings = videoDao.getVideoRecordings(theCategory);
		} catch (DAOException e) {
			if(recordings == null){
				recordings = new ArrayList<VideoRecording>();
			}
		}
		return recordings;
	}

	/**
	 *  Get a sorted typesafe list of video recordings from the database for the given category. <br>
	 *  The sort order is ascending.
	 *
	 *  @param theCategory the category name
	 *  @param sortBy the key to sort by
	 *  @return a list of sorted <code>VideoRecording</code> objects
	 *  <br/>Handles DAOException generated by VideoDao
	 *	@see rain.VideoDao#SORT_BY_TITLE
	 *	@see rain.VideoDao#SORT_BY_PRICE
	 *	@see rain.VideoDao#SORT_BY_STOCK_COUNT
	 */
	@Transactional(readOnly=true)
	public List<VideoRecording> getVideoRecordings(String theCategory,int sortBy){
		List<VideoRecording> recordings = null;
		try {
			recordings = videoDao.getVideoRecordings(theCategory, sortBy);
		} catch (DAOException e) {
			if(recordings == null){
				recordings = new ArrayList<VideoRecording>();
			}
		}
		return recordings;
	}
	/**
	 *  Returns a video recording based on the id
	 *
	 *	@param recordingId the recording id
	 *	@return a <code>VideoRecording</code> object for the given recording id
	 *  <br/>Handles DAOException generated by VideoDao
	 * 	
	 */

	@Transactional(readOnly=true)
	public VideoRecording getVideoRecording(String recordingId) {
		VideoRecording recording = null;
		try {
			recording = videoDao.getVideoRecording(recordingId);
		} catch (DAOException e) {
			if(recording == null){
				recording = new VideoRecording();
			}
		}
		return recording;
	}

	/**
	 *  Returns a video recording based on the id
	 *
	 *  <br/>Handles DAOException
	 */

	@Transactional(readOnly=true)
	public VideoRecording getVideoRecording(int recordingId) {
		VideoRecording recording = null;
		try {
			recording = videoDao.getVideoRecording(recordingId);
		} catch (DAOException e) {
			if(recording == null){
				recording = new VideoRecording();
			}
		}
		return recording;
	}

}
