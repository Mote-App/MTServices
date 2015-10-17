package com.mt.controllers.twitter;

/**
 * 
 * @author gibranecastillo
 *
 */
public class MessageForm {
	private String to;
	private String text;
	
	/**
	 * 
	 * @return
	 */
	public String getTo() {
		return to;
	}
	
	/**
	 * 
	 * @param to
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
}