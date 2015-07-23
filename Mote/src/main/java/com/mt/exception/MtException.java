package com.mt.exception;

/**
 * The <code>MtException</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class MtException extends Exception {
	private static final long serialVersionUID = 1L;
	
	//private String status;
	private String message;
	private String exception;
	
	
	/**
	 * 
	 */
	public MtException(String message, Exception e) {
		super(message, e);
	}
	
	/**
	 * 
	 * @param message
	 * @param exception
	 */
	public MtException(String message, String exception) {
		//this.status = status;
		this.message = message;
		this.exception = exception;
	}
	
	/*	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	 */	
	
	/**
	 * 
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getException() {
		return exception;
	}
	
	/**
	 * 
	 * @param exception
	 */
	public void setException(String exception) {
		this.exception = exception;
	}
}