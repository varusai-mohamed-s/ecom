/**
 * 
 */
package com.demo.ecom.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.ecom.entities.Product;
import com.demo.ecom.entities.ProductOrder;
import com.demo.ecom.exceptions.InventoryValidationException;
import com.demo.ecom.models.OrderDetail;
import com.demo.ecom.models.ProductOrderResponse;
import com.demo.ecom.repository.ProductOrderRepository;
import com.demo.ecom.repository.ProductRepository;

/**
 * Tests the functionality of {@link ProductOrderService}.
 * 
 * @author Varusai
 *
 */
@ExtendWith(MockitoExtension.class)
public class ProductOrderServiceTest {

	@InjectMocks
	private ProductOrderService productOrderService;

	@Mock
	private ProductOrderRepository productOrderRepository;

	@Mock
	private ProductRepository productRepository;

	/**
	 * Tests the {@link ProductOrderService#order(List)} for positive flow.
	 */
	@Test
	public void testOrder() {
		final List<OrderDetail> orderDetailList = Arrays.asList(new OrderDetail(new Product(1L), 2));
		final Product product = getProduct();

		doReturn(Optional.of(product)).when(productRepository).findById(product.getId());

		final ProductOrderResponse response = productOrderService.order(orderDetailList);

		assertThat(response).isNotNull();
		assertThat(response.getErrors()).isEmpty();
		assertEquals(1, response.getSuccessfulTransactions().size());
		assertEquals(0, response.getFailedTransactions().size());
		verify(productOrderRepository).save(Mockito.any(ProductOrder.class));
		verify(productRepository).save(Mockito.any(Product.class));
	}

	/**
	 * Tests the {@link ProductOrderService#order(List)} for negative flow - Less
	 * stock validation error.
	 */
	@Test
	public void testOrderWithLessStockValidation() {
		final List<OrderDetail> orderDetailList = Arrays.asList(new OrderDetail(new Product(1L), 2),
				new OrderDetail(new Product(2L), 2));
		final Product product1 = getProduct();
		final Product product2 = new Product(2L, "Product 2", 1200.0, 5, "Product 2 description", 1);

		doReturn(Optional.of(product1)).when(productRepository).findById(product1.getId());
		doReturn(Optional.of(product2)).when(productRepository).findById(product2.getId());

		final ProductOrderResponse response = productOrderService.order(orderDetailList);

		assertThat(response).isNotNull();
		assertEquals(1, response.getErrors().size());
		assertEquals(1, response.getSuccessfulTransactions().size());
		assertEquals(1, response.getFailedTransactions().size());
	}

	/**
	 * Tests the {@link ProductOrderService#order(List)} for negative flow - Out of
	 * stock validation.
	 */
	@Test
	public void testOrderWithOutOfStockValidation() {
		final List<OrderDetail> orderDetailList = Arrays.asList(new OrderDetail(new Product(1L), 2));
		final Product product = getProduct();
		product.setAvailableQuantity(1);

		doReturn(Optional.of(product)).when(productRepository).findById(product.getId());
		final InventoryValidationException exception = assertThrows(InventoryValidationException.class,
				() -> productOrderService.order(orderDetailList));

		assertThat(exception).isNotNull();
		assertEquals(1, exception.getValidationErrors().size());
		verify(productOrderRepository, never()).save(any(ProductOrder.class));
		verify(productRepository, never()).save(any(Product.class));
	}

	/**
	 * Helper method to build the product of ID 1L.
	 * 
	 * @return Product.
	 */
	private Product getProduct() {
		return new Product(1L, "Product 1", 1200.0, 5, "Product 1 description", 5);
	}
}
