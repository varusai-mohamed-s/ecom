/**
 * 
 */
package com.demo.ecom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.models.Product;
import com.demo.ecom.models.ProductDetail;
import com.demo.ecom.repository.IProductRepository;

/**
 * Service class for Product.
 * 
 * @author Varusai
 *
 */
@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.getProducts();
	}

	@Override
	public ProductDetail getProductDetail(Long id) {
		return productRepository.getProductDetails(id);
	}

}
