package views;

import java.util.ArrayList;
import java.util.List;

import com.mt.models.Tag;

/**
 * The <code>TagDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class TagDto {
	/*List<Tag> smarts = new ArrayList<Tag>();
	List<Tag> socials = new ArrayList<Tag>();
	List<Tag> genre = new ArrayList<Tag>();*/
	List<Tag> tags = new ArrayList<Tag>();
	
	/*public List<Tag> getSmarts() {
		return smarts;
	}
	public void setSmarts(List<Tag> smarts) {
		this.smarts = smarts;
	}
	public List<Tag> getSocials() {
		return socials;
	}
	public void setSocials(List<Tag> socials) {
		this.socials = socials;
	}
	public List<Tag> getGenre() {
		return genre;
	}
	public void setGenre(List<Tag> genre) {
		this.genre = genre;
	}*/
	
	/**
	 * 
	 * @return
	 */
	public List<Tag> getTags() {
		return tags;
	}
	
	/**
	 * 
	 * @param tags
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}	
}