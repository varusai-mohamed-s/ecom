/**
 * 
 */
package com.demo.ecom.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.demo.ecom.models.ErrorResponse;
import com.demo.ecom.models.OrderValidationError;

/**
 * Helper class to properly build error message and respond.
 * 
 * @author Varusai
 *
 */
@Component
public class ApiErrorHelper implements IApiErrorHelper {

	@Override
	public ResponseEntity<Object> processError(final String message, final List<OrderValidationError> errors,
			final HttpStatus status) {
		final ErrorResponse apiError = createApiError(message, errors);
		return new ResponseEntity<>(apiError, status);
	}

	/**
	 * Builds the {@link ErrorResponse}.
	 * 
	 * @param message The error message.
	 * @param errors  The validation error messages.
	 * @return Returns the ErrorResponse.
	 */
	private ErrorResponse createApiError(final String message, final List<OrderValidationError> errors) {
		return new ErrorResponse(message, errors);
	}

}
