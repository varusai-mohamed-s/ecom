/**
 * 
 */
package com.demo.ecom.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model class to hold Product information.
 * 
 * @author Varusai
 *
 */
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private double price;
	private int rating;
	private String description;
	private int availableQuantity;

	/**
	 * 
	 */
	public Product() {
	}

	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param rating
	 * @param description
	 * @param availableQuantity
	 */
	public Product(String name, double price, int rating, String description, int availableQuantity) {
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.description = description;
		this.availableQuantity = availableQuantity;
	}
	
	

	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param rating
	 * @param description
	 * @param availableQuantity
	 */
	public Product(Long id, String name, double price, int rating, String description, int availableQuantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.description = description;
		this.availableQuantity = availableQuantity;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", rating=" + rating + ", description="
				+ description + ", availableQuantity=" + availableQuantity + "]";
	}

}
