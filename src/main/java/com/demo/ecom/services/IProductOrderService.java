/**
 * 
 */
package com.demo.ecom.services;

import java.util.List;

import com.demo.ecom.exceptions.InventoryValidationException;
import com.demo.ecom.models.OrderDetail;
import com.demo.ecom.models.ProductOrderResponse;

/**
 * Abstract layer for Order Service.
 * 
 * @author Varusai
 *
 */
public interface IProductOrderService {

	/**
	 * This method checks inventory and places the order.
	 * 
	 * @param orderDetail The order to be placed.
	 * @return Returns the {@link ProductOrderResponse}
	 * @throws InventoryValidationException Throws if the inventory is low or out of stock.
	 */
	ProductOrderResponse order(List<OrderDetail> orderDetail) throws InventoryValidationException;
}
