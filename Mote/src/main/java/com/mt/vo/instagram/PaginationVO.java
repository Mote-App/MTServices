package com.mt.vo.instagram;

public class PaginationVO {
	private String next_url;
	private String next_max_id;
	
	public String getNext_url() {
		return next_url;
	}
	
	public void setNext_url(String next_url) {
		this.next_url = next_url;
	}
	
	public String getNext_max_id() {
		return next_max_id;
	}
	
	public void setNext_max_id(String next_max_id) {
		this.next_max_id = next_max_id;
	}
	
	@Override
	public String toString() {
		return "Pagination [next_url=" + next_url + ", next_max_id=" + next_max_id + "]";
	}
}