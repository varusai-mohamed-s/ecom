/**
 * 
 */
package com.demo.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.models.OrderDetail;
import com.demo.ecom.models.ProductOrderResponse;
import com.demo.ecom.services.IProductOrderService;

/**
 * This class handles all the request related to order.
 * 
 * @author Varusai
 *
 */
@RestController
public class ProductOrderController {
	@Autowired
	private IProductOrderService productOrderService;

	/**
	 * This method processes the order.
	 * 
	 * @param orderDetailList The product order list.
	 * @return Returns the response of type {@link ProductOrderResponse}
	 */
	@PostMapping("/order")
	public ProductOrderResponse order(@RequestBody final List<OrderDetail> orderDetailList) {
		return productOrderService.order(orderDetailList);
	}
}
