/**
 * 
 */
package com.demo.ecom.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.ecom.entities.Product;

/**
 * Repository to handle {@link Order}.
 * 
 * @author Varusai
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

}
