package com.mt.exception;

public class ClException extends Exception {

	//private String status;
	private String message;
	private String exception;
	
	public ClException(){
		
	}

	public ClException(String message, String exception){
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
	
}
