/*
 * 10-20-2015, Chris Lawson
 * 
 * Represents a Response from the Instagram API Endpoints.
 * 
 */
package com.mt.vo.instagram;

import java.util.List;

public class InstagramResponse {
	private PaginationVO pagination;
	private MetaVO meta;
	private List<?> data;
	
	public PaginationVO getPagination() {
		return pagination;
	}
	public void setPagination(PaginationVO pagination) {
		this.pagination = pagination;
	}
	public MetaVO getMeta() {
		return meta;
	}
	public void setMeta(MetaVO meta) {
		this.meta = meta;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "InstagramResponse [pagination=" + pagination + ", meta=" + meta + ", data=" + data + "]";
	}
}