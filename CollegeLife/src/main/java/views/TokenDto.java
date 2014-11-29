package views;

public class TokenDto {

	private String token;
	private long userId;
	
	public TokenDto(String token, long userId){
		
		this.token = token;
		this.userId = userId;
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
	
}
