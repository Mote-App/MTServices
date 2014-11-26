package views;

public class TokenDto {

	private final String token;
	private final long userId;
	
	public TokenDto(String token, long userId){
		
		this.token = token;
		this.userId = userId;
	}
	
	public String getToken() {
		return token;
	}

	public long getUserId() {
		return userId;
	}
	
}
