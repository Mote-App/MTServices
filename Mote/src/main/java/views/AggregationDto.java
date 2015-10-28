package views;

/**
 * The <code>AggregationDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class AggregationDto {
	private Long aggregationId;
	private String aggregationName;
	
	/**
	 * @return the aggregationId
	 */
	public Long getAggregationId() {
		return aggregationId;
	}
	
	/**
	 * @param aggregationId the aggregationId to set
	 */
	public void setAggregationId(Long aggregationId) {
		this.aggregationId = aggregationId;
	}
	
	/**
	 * @return the aggregationName
	 */
	public String getAggregationName() {
		return aggregationName;
	}
	
	/**
	 * @param aggregationName the aggregationName to set
	 */
	public void setAggregationName(String aggregationName) {
		this.aggregationName = aggregationName;
	}
}