/**
 * 
 */
package com.userregisteration.responseenvelope;

/**
 * @author himanshugupta
 *
 */
public class RegisterResponseEnvelope {
	
	private String message;
	private String errorCode;
	/**
	 * @param message
	 * @param errorCode
	 */
	public RegisterResponseEnvelope(String message, String errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
