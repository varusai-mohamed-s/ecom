/**
 * 
 */
package com.demo.ecom.models;

/**
 * Class to hold product order information.
 * 
 * @author Varusai
 *
 */
public class ProductOrder {
	private ProductDetail productDetail;
	private int quantity;

	/**
	 * @return the productDetail
	 */
	public ProductDetail getProductDetail() {
		return productDetail;
	}

	/**
	 * @param productDetail the productDetail to set
	 */
	public void setProductDetail(final ProductDetail productDetail) {
		this.productDetail = productDetail;
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
