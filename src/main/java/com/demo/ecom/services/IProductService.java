package com.demo.ecom.services;

import java.util.List;

import com.demo.ecom.models.Product;
import com.demo.ecom.models.ProductDetail;

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
	 * Get the detail of the give product.
	 * 
	 * @param id The id of the product.
	 * @return Returns the product detail.
	 */
	ProductDetail getProductDetail(Long id);
}
