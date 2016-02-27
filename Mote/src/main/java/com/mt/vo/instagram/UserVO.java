package com.mt.vo.instagram;

public class UserVO {
    private Long id;
    private String username;
    private String profile_picture;
    private String full_name;
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getProfile_picture() {
		return profile_picture;
	}
	
	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}
	
	public String getFull_name() {
		return full_name;
	}
	
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", profile_picture=" + profile_picture + ", full_name=" + full_name + "]";
	}
}