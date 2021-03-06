/**
 * 
 */
package com.demo.ecom.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.ecom.entities.ProductOrder;

/**
 * Repository to handle {@link ProductOrder}.
 * 
 * @author Varusai
 *
 */
public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {

}
