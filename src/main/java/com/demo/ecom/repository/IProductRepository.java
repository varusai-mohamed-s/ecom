/**
 * 
 */
package com.demo.ecom.repository;

import java.util.List;

import com.demo.ecom.models.Product;
import com.demo.ecom.models.ProductDetail;

/**
 * Abstract layer for Product Repository.
 * 
 * @author Varusai
 *
 */
public interface IProductRepository {
	List<Product> getProducts();

	public ProductDetail getProductDetails(Long id);

	public boolean updateInventory(Product product, int selectedQuantity);
}
