/**
 * 
 */
package com.demo.ecom.models;

/**
 * Model class to hold validation errors of an order.
 * 
 * @author Varusai
 *
 */
public class OrderValidationError {
	private String code;
	private String message;

	/**
	 * @param code
	 * @param message
	 */
	public OrderValidationError(final String code, final String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

}
