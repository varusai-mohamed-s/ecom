/**
 * 
 */
package com.demo.ecom.exceptions;

import java.util.List;

import com.demo.ecom.models.OrderValidationError;

/**
 * Exception to be thrown when the inventory is out or low stock.
 * 
 * @author Varusai
 *
 */
public final class InventoryValidationException extends RuntimeException {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -9145523255118008607L;

	private List<OrderValidationError> validationErrors;

	/**
	 * Default constructor
	 */
	public InventoryValidationException() {

	}

	/**
	 * Parameterized constructor
	 * 
	 * @param validationErrors
	 */
	public InventoryValidationException(final List<OrderValidationError> validationErrors) {
		super();
		this.validationErrors = validationErrors;
	}

	/**
	 * @return the validationErrors
	 */
	public List<OrderValidationError> getValidationErrors() {
		return validationErrors;
	}

}
