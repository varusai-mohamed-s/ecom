/**
 * 
 */
package com.demo.ecom.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.ecom.models.OrderValidationError;

/**
 * Abstract layer for {@link ApiErrorHelper}
 * 
 * @author Varusai
 *
 */
public interface IApiErrorHelper {
	/**
	 * Processes the error.
	 * 
	 * @param message The error message.
	 * @param error   The list of errors.
	 * @param status  The HTTP status to be returned.
	 * @return Returns ResponseEntity.
	 */
	ResponseEntity<Object> processError(String message, List<OrderValidationError> error, HttpStatus status);
}
