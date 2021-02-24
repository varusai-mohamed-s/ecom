/**
 * 
 */
package com.demo.ecom.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entities.Product;
import com.demo.ecom.repository.ProductRepository;

/**
 * Service class for Product.
 * 
 * @author Varusai
 *
 */
@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		final List<Product> productList = new ArrayList<>();
		productRepository.findAll().forEach(productList::add);
		return productList;
	}

	@Override
	public Product getProduct(final Long id) {
		return productRepository.findById(id).get();
	}

}
