package com.demo.ecom.services;

import java.util.List;
import java.util.Optional;

import com.demo.ecom.entities.Product;

/**
 * 
 * Abstract layer for Product Service.
 * 
 * @author Varusai
 *
 */
public interface IProductService {
	/**
	 * Fetches all the products.
	 * 
	 * @return Returns the list of products.
	 */
	List<Product> getProducts();

	/**
	 * Get the detail of the given product id.
	 * 
	 * @param id The id of the product.
	 * @return Returns the product detail.
	 */
	Optional<Product> getProduct(Long id);
}
