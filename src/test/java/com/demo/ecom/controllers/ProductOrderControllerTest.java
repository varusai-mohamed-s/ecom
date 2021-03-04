/**
 * 
 */
package com.demo.ecom.controllers;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.ecom.entities.Product;
import com.demo.ecom.exceptions.InventoryValidationException;
import com.demo.ecom.models.OrderDetail;
import com.demo.ecom.models.OrderValidationError;
import com.demo.ecom.models.ProductOrderResponse;
import com.demo.ecom.services.IProductOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests the functionality of {@link ProductOrderController}.
 * 
 * @author Varusai
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ProductOrderControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private IProductOrderService productOrderService;

	/**
	 * Tests the functionality of {@link ProductController#getProducts()}.
	 * 
	 * @throws Exception Throws if the test fails.
	 */
	@Test
	public void testOrder() throws Exception {
		final ProductOrderResponse response = new ProductOrderResponse();
		final List<OrderDetail> orderDetailList = Arrays.asList(new OrderDetail(new Product(1L), 2));

		doReturn(response).when(productOrderService).order(anyList());

		this.mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(orderDetailList))).andExpect(status().isOk());
	}

	/**
	 * Tests the functionality of {@link ProductController#getProducts()} for
	 * validation error.
	 * 
	 * @throws Exception Throws if the test fails.
	 */
	@Test
	public void testOrderWithException() throws Exception {
		final List<OrderValidationError> validationErrors = Arrays
				.asList(new OrderValidationError("OUT_OF_STOCK", "Product is currently unavailable"));

		InventoryValidationException exception = new InventoryValidationException(validationErrors);
		final List<OrderDetail> orderDetailList = Arrays.asList(new OrderDetail(new Product(1L), 2));

		doThrow(exception).when(productOrderService).order(anyList());

		this.mockMvc
				.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(orderDetailList)))
				.andExpect(status().isPreconditionFailed());
	}

}
