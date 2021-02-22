/**
 * 
 */
package com.demo.ecom.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.ecom.exceptions.InventoryValidationException;

/**
 * Handles all the exceptions and return proper response.
 * 
 * @author Varusai
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler {

	@Autowired
	private IApiErrorHelper apiErrorHelper;

	@ExceptionHandler(InventoryValidationException.class)
	public ResponseEntity<Object> handle(InventoryValidationException e) {
		return apiErrorHelper.processError(e.getMessage(), e.getValidationErrors(), HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleNotFound(final RuntimeException ex) {
		return new ResponseEntity<>("We are unable to process your request right now. Please try after some time.",
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
