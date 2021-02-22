/**
 * 
 */
package com.demo.ecom.models;

import java.util.List;

/**
 * Product order response class.
 * 
 * @author Varusai
 *
 */
public class ProductOrderResponse {
	private List<OrderValidationError> errors;
	private List<ProductOrder> successfulTransactions;
	private List<ProductOrder> failedTransactions;

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

	/**
	 * @return the successfulTransactions
	 */
	public List<ProductOrder> getSuccessfulTransactions() {
		return successfulTransactions;
	}

	/**
	 * @param successfulTransactions the successfulTransactions to set
	 */
	public void setSuccessfulTransactions(List<ProductOrder> successfulTransactions) {
		this.successfulTransactions = successfulTransactions;
	}

	/**
	 * @return the failedTransactions
	 */
	public List<ProductOrder> getFailedTransactions() {
		return failedTransactions;
	}

	/**
	 * @param failedTransactions the failedTransactions to set
	 */
	public void setFailedTransactions(List<ProductOrder> failedTransactions) {
		this.failedTransactions = failedTransactions;
	}

}
