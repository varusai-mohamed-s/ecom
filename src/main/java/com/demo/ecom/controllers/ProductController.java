/**
 * 
 */
package com.demo.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.entities.Product;
import com.demo.ecom.services.IProductService;

/**
 * This class handles all the requests related to Product.
 * 
 * @author Varusai
 *
 */
@RestController
public class ProductController {

	@Autowired
	private IProductService productService;

	/**
	 * This method returns all the Products.
	 * 
	 * @return Returns all the products.
	 */
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	/**
	 * This method retrieves the details of the product by the given product id.
	 * 
	 * @param id The id of the product.
	 * @return Returns the retrieved product detail {@link ProductDetail}.
	 */
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") final Long id) {
		return productService.getProduct(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
