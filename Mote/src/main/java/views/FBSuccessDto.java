package views;

public class FBSuccessDto {
	private boolean fbSuccess;
	private String profileId;
	private String FBAccessToken;
	
	public boolean isFbSuccess() {
		return fbSuccess;
	}
	
	public void setFbSuccess(boolean fbSuccess) {
		this.fbSuccess = fbSuccess;
	}
	
	public String getProfileId() {
		return profileId;
	}
	
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	
	public String getFBAccessToken() {
		return FBAccessToken;
	}
	
	public void setFBAccessToken(String fBAccessToken) {
		FBAccessToken = fBAccessToken;
	}
}