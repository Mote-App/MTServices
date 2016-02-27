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
	List<Tag> tags = new ArrayList<Tag>();
	
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