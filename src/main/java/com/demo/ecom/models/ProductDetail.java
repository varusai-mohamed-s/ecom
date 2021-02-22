/**
 * 
 */
package com.demo.ecom.models;

/**
 * Model class to hold Product details.
 * 
 * @author Varusai
 *
 */
public class ProductDetail extends Product {
	private int rating;
	private String description;
	private int availableQuantity;

	public ProductDetail() {
		super();
	}

	public ProductDetail(final Long id, final String name, double price, int availableQuantity, int rating,
			final String description) {
		super(id, name, price);
		this.availableQuantity = availableQuantity;
		this.rating = rating;
		this.description = description;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the availableQuantity
	 */
	public int getAvailableQuantity() {
		return availableQuantity;
	}

	/**
	 * @param availableQuantity the availableQuantity to set
	 */
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
}
