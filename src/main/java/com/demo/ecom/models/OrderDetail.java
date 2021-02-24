/**
 * 
 */
package com.demo.ecom.models;

import com.demo.ecom.entities.Product;

/**
 * Class to hold product order information.
 * 
 * @author Varusai
 *
 */
public class OrderDetail {
	private Product product;
	private int quantity;

	/**
	 * 
	 */
	public OrderDetail() {
	}

	/**
	 * @param product
	 * @param quantity
	 */
	public OrderDetail(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
