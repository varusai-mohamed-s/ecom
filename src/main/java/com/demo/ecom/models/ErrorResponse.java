/**
 * 
 */
package com.demo.ecom.models;

import java.util.List;

/**
 * Model class for Error response.
 * 
 * @author Varusai
 *
 */
public class ErrorResponse {
	private String code;
	private String message;
	private List<OrderValidationError> errors;

	public ErrorResponse() {

	}

	public ErrorResponse(final String message, final List<OrderValidationError> errors) {
		this.message = message;
		this.errors = errors;
	}

	public ErrorResponse(final String code, final String message) {
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

	/**
	 * @return the errors
	 */
	public List<OrderValidationError> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<OrderValidationError> errors) {
		this.errors = errors;
	}

}
