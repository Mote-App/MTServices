package views;

public class TokenDto {

	private String token;
	private long userId;
	private long collegeId;
	
	public TokenDto(String token, long userId, long collegeId){
		
		this.token = token;
		this.userId = userId;
		this.collegeId = collegeId;
	}
	
	
	public void setToken(String token) {
		this.token = token;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public long getUserId() {
		return userId;
	}

	public long getCollegeId() {
		return collegeId;
	}


	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}
	
}
