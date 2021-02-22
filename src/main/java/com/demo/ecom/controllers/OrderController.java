/**
 * 
 */
package com.demo.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.models.ProductOrder;
import com.demo.ecom.models.ProductOrderResponse;
import com.demo.ecom.services.IOrderService;

/**
 * This class handles all the request related to order.
 * 
 * @author Varusai
 *
 */
@RestController
public class OrderController {
	@Autowired
	private IOrderService orderService;

	/**
	 * This method processes the order.
	 * 
	 * @param productOrderList The product order list.
	 * @return Returns the response of type {@link ProductOrderResponse}
	 */
	@PostMapping("/order")
	public ProductOrderResponse order(@RequestBody final List<ProductOrder> productOrderList) {
		return orderService.order(productOrderList);
	}
}
