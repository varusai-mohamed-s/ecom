
package com.demo.ecom.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.ecom.entities.Product;
import com.demo.ecom.repository.ProductRepository;

/**
 * Tests the functionality of {@link ProductService}.
 * 
 * @author Varusai
 *
 */
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	/**
	 * Tests the functionality of {@link ProductService#getProducts()}.
	 */
	@Test
	public void testGetProducts() {
		doReturn(buildProducts()).when(productRepository).findAll();

		final List<Product> actual = productService.getProducts();

		assertThat(actual).isNotNull();
		assertThat(actual).isNotEmpty();
		assertEquals(4, actual.size());
	}

	/**
	 * Tests the functionality of {@link ProductService#getProduct(Long)}.
	 */
	@Test
	public void testGetProduct() {
		final Long productId = 1L;
		final Product product = new Product(productId, "Product 1", 1200.0, 5, "Product 1 description", 51);

		doReturn(Optional.of(product)).when(productRepository).findById(productId);

		final Optional<Product> actual = productService.getProduct(1L);

		assertThat(actual).isNotNull();
	}

	/**
	 * Tests the functionality of {@link ProductService#getProduct(Long)} for
	 * invalid product id.
	 */
	@Test
	public void testGetProductInvalidId() {
		final Long productId = 100L;

		doReturn(Optional.of(null)).when(productRepository).findById(productId);

		final Optional<Product> actual = productService.getProduct(100L);

		assertThat(actual).isNull();
	}

	/**
	 * Helper method to build the product list.
	 * 
	 * @return Returns the list of products.
	 */
	private Iterable<Product> buildProducts() {
		final List<Product> productList = new ArrayList<>();

		productList.add(new Product(1L, "Product 1", 1200.0, 5, "Product 1 description", 51));
		productList.add(new Product(2L, "Product 2", 750.0, 3, "Product 2 description", 151));
		productList.add(new Product(3L, "Product 3", 1160.0, 4, "Product 3 description", 35));
		productList.add(new Product(4L, "Product 4", 1110.0, 4, "Product 4 description", 60));

		return productList;
	}
}
