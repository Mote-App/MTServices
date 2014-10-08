package com.cl.feeds.pojos;

import java.io.Serializable;

public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public abstract long getLoggedUserId();
	public abstract void setLoggedUserId(long loggedUserId);
}