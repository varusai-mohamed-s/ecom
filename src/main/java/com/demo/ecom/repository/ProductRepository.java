/**
 * 
 */
package com.demo.ecom.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.ecom.models.Product;

/**
 * @author Varusai
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long>{
	
}
