package rain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 *  This class represents a VideoRecording.  It is a specialized version
 *  of the Recording class and adds the following data members:
 *  director, actors, rating, yearReleased.  <p>
 *
 *
 *  @author 517 Development Team
 */

@Entity
@Table(name="video_recordings")
@NamedQueries({
	@NamedQuery(name="VideoRecording.byCategory", query="from rain.VideoRecording where category=:theCategory"),
	@NamedQuery(name="VideoRecording.Recording",query="from rain.VideoRecording where recordingId=:theRecordingId")
})
public class VideoRecording extends Product{

	//
	//  DATA MEMBERS
	//

	/**
	 *  The recording title
	 */
	private String title;

	/**
	 *  The recording price
	 */
	private double price;

	/**
	 *  The recording category
	 */
	private String category;

	/**
	 *  The recording image name
	 */
	@Column(name="image_name")
	private String imageName;

	/**
	 *	The recording id
	 */
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="recording_id")
	private int recordingId;

	/**
	 *  The quantity of stock on hand
	 */
	
	@Column(name="stock_count")
	private int stockCount;


	/**
	 *  The director's name
	 */
	private String director;

	/**
	 *  A list of actors starring in the video recording
	 */
	
	@Transient
	private String[] actors;

	/**
	 *  The rating:  G, PG, R, YYY
	 */
	private String rating;

	/**
	 *  The year the video recording was released
	 */
	
	@Column(name="year_released")
	private int yearReleased;

	/**
	 *  The length of video recording
	 */
	private int duration;


	//
	//  CONSTRUCTORS
	//

	/**
	 *  Default constructor
	 */
	public VideoRecording() {
		// set the data members using the setter methods
	}


	//
	//  GETTER / SETTER METHODS
	//

	/**
	 *  Returns the recording title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *  Sets the recording title
	 */
	public void setTitle(String theTitle) {
		title = theTitle;
	}


	/**
	 *  Returns the recording price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 *  Sets the recording price
	 */
	public void setPrice(double thePrice) {
		price = thePrice;
	}


	/**
	 *  Returns the recording category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 *  Sets the recording category
	 */
	public void setCategory(String theCategory) {
		category = theCategory;
	}


	/**
	 *  Returns the recording image name
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 *  Sets the recording image name
	 */
	public void setImageName(String theImageName) {
		imageName = theImageName;
	}

	public int getId(){
		return recordingId;
	}
	
	public void setId(int id){
		recordingId = id;
	}
	
	/**
	 *  Returns the recording id.
	 */
	public int getRecordingId() {
		return recordingId;
	}

	/**
	 *  Sets the recording id
	 */
	public void setRecordingId(int theRecordingId) {
		recordingId = theRecordingId;
	}


	/**
	 *  Returns the name of the product
	 */
	public String getName() {
		return toString();
	}

	/**
	 *  Sets the name / title
	 */
	public void setName(String theName) {
		setTitle(theName);
	}

	/**
	 *  Returns the stock count - quantity on hand
	 */
	public int getStockCount() {
		return stockCount;
	}

	/**
	 *  Sets the stock count
	 */
	public void setStockCount(int theCount) {
		stockCount  = theCount;
	}

	/**
	 *  Returns true if the product is in stock
	 */
	public boolean isInStock() {
		return stockCount > 0;
	}

	
	
	/**
	 *  Returns the director's name
	 */
	public String getDirector() {
		return director;
	}

	/**
	 *  Sets the director
	 */
	public void setDirector(String theDirector) {
		director = theDirector;
	}

	/**
	 *  Returns the list of actors
	 */
	public String[] getActors() {
		return actors;
	}

	/**
	 *  Sets the list of actors
	 */
	public void setActors(String[] theActors) {
		actors = theActors;
	}

	/**
	 *  Returns the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 *  Sets the rating
	 */
	public void setRating(String theRating) {
		rating = theRating;
	}

	/**
	 *  Returns the year released
	 */
	public int getYearReleased() {
		return yearReleased;
	}

	/**
	 *  Sets the year released
	 */
	public void setYearReleased(int year) {
		yearReleased = year;
	}

	/**
	 *  Returns the duration of the video recording
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 *  Sets the duration
	 */
	public void setDuration(int theDuration) {
		duration = theDuration;
	}

	/**
	 *  Returns the title of recording
	 */
	public String toString() {
		return getTitle();
	}

}