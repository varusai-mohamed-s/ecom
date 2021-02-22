/**
 * 
 */
package com.demo.ecom.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Repository;

import com.demo.ecom.models.Product;
import com.demo.ecom.models.ProductDetail;

/**
 * Repository class for {@link Product} and {@link ProductDetail}.
 * 
 * @author Varusai
 *
 */
@Repository
public class ProductRepository implements IProductRepository {
	// Static objects to mimic db. Actual db repositories will be included once
	// implemented.
	static List<Product> products;
	static Map<Long, ProductDetail> productDetail;

	// Lock to allow only one thread to update the inventory at a time.
	private Lock lock = new ReentrantLock(true);

	// Initializing products in db.
	static {
		products = new ArrayList<>();
		products.add(new Product(1L, "Product1", 150));
		products.add(new Product(2L, "Product2", 200));
		products.add(new Product(3L, "Product3", 500));
		products.add(new Product(4L, "Product4", 300));
		products.add(new Product(5L, "Product5", 20));

		productDetail = new HashMap<>();
		productDetail.put(1L, new ProductDetail(1L, "Product1", 150, 5, 4, "This is product 1"));
		productDetail.put(2L, new ProductDetail(2L, "Product2", 200, 4, 4, "This is product 2"));
		productDetail.put(3L, new ProductDetail(3L, "Product3", 500, 19, 4, "This is product 3"));
		productDetail.put(4L, new ProductDetail(4L, "Product4", 300, 2, 3, "This is product 4"));
		productDetail.put(5L, new ProductDetail(5L, "Product4", 20, 100, 5, "This is product 5"));
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public ProductDetail getProductDetails(final Long id) {
		return productDetail.get(id);
	}

	@Override
	public boolean updateInventory(final Product product, int selectedQuantity) {
		boolean inventoryUpdated = false;
		lock.lock();

		try {
			int existingQuantity = ProductRepository.getProductDetail().get(product.getId()).getAvailableQuantity();
			if (selectedQuantity <= existingQuantity) {
				int newQuantity = existingQuantity - selectedQuantity;
				ProductRepository.getProductDetail().get(product.getId())
						.setAvailableQuantity(newQuantity > 0 ? newQuantity : 0);
				inventoryUpdated = true;
			}
		} finally {
			lock.unlock();
		}
		return inventoryUpdated;
	}

	/**
	 * Returns the Product Details.
	 * 
	 * @return the productDetail
	 */
	public static Map<Long, ProductDetail> getProductDetail() {
		return productDetail;
	}

}
