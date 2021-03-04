/**
 * 
 */

package com.demo.ecom.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.ecom.entities.Product;
import com.demo.ecom.services.IProductService;

/**
 * Tests the functionality of {@link ProductController}.
 * 
 * @author Varusai
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IProductService productService;

	/**
	 * Tests the functionality of {@link ProductController#getProducts()}.
	 * 
	 * @throws Exception Throws if the test fails.
	 */
	@Test
	public void testGetProducts() throws Exception {
		final List<Product> products = Arrays.asList(new Product("product 1", 120L, 4, "product 1 desc", 25),
				new Product("product 2", 150L, 5, "product 2 desc", 45));

		doReturn(products).when(productService).getProducts();

		this.mockMvc.perform(get("/products")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()", is(products.size())));
	}

	/**
	 * Tests the functionality of {@link ProductController#getProduct(Long)} for
	 * {@link HttpStatus#OK}.
	 * 
	 * @throws Exception Throws if the test fails.
	 */
	@Test
	public void testGetProduct() throws Exception {
		final Product product = new Product("product 1", 120L, 4, "product 1 desc", 25);

		doReturn(Optional.of(product)).when(productService).getProduct(anyLong());

		this.mockMvc.perform(get("/product/{id}", 1L)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", is(product.getName())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description", is(product.getDescription())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.rating", is(product.getRating())));
	}

	/**
	 * Tests the functionality of {@link ProductController#getProduct(Long)} for
	 * {@link HttpStatus#NOT_FOUND}.
	 * 
	 * @throws Exception Exception Throws if the test fails.
	 */
	@Test
	public void testGetProductNotFound() throws Exception {
		doReturn(Optional.empty()).when(productService).getProduct(anyLong());

		this.mockMvc.perform(get("/product/{id}", 100L)).andExpect(status().isNotFound());
	}
}
