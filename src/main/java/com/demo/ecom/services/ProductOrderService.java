/**
 * 
 */
package com.demo.ecom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.constants.ApiConstant;
import com.demo.ecom.exceptions.InventoryValidationException;
import com.demo.ecom.models.OrderDetail;
import com.demo.ecom.models.OrderValidationError;
import com.demo.ecom.models.Product;
import com.demo.ecom.models.ProductOrder;
import com.demo.ecom.models.ProductOrderResponse;
import com.demo.ecom.repository.ProductOrderRepository;
import com.demo.ecom.repository.ProductRepository;

/**
 * Service class for Order.
 * 
 * @author Varusai
 *
 */
@Service
public class ProductOrderService implements IProductOrderService {
	@Autowired
	private ProductOrderRepository productOrderRepository;

	@Autowired
	private ProductRepository productRepository;

	// Lock to allow only one thread to update the inventory at a time.
	private Lock lock = new ReentrantLock(true);

	@Override
	public ProductOrderResponse order(final List<OrderDetail> orderDetailList) throws InventoryValidationException {
		final List<OrderValidationError> validations = new ArrayList<>();
		final List<OrderDetail> successfulTransactions = new ArrayList<>();
		final List<OrderDetail> failedTransactions = new ArrayList<>();

		if (orderDetailList != null && !orderDetailList.isEmpty()) {
			orderDetailList.stream().forEach(item -> {
				Product product = productRepository.findById(item.getProduct().getId()).get();
				item.setProduct(product);
				
				if (isValidItem(product, item.getQuantity(), validations)) {
					if (updateInventoryAndCreateOrder(product, item.getQuantity())) {
						successfulTransactions.add(item);
					} else {
						failedTransactions.add(item);
						addValidationError(validations, ApiConstant.CODE_LOW_STOCK,
								String.format(ApiConstant.MSG_LOW_STOCK, product.getName()));
					}
				} else {
					failedTransactions.add(item);
				}
			});
		}

		if (!failedTransactions.isEmpty() && failedTransactions.size() == orderDetailList.size()) {
			throw new InventoryValidationException(validations);
		} else {
			final ProductOrderResponse response = new ProductOrderResponse();
			response.setSuccessfulTransactions(successfulTransactions);
			response.setFailedTransactions(failedTransactions);
			response.setErrors(validations);

			return response;
		}

	}

	private boolean updateInventoryAndCreateOrder(Product product, int selectedQuantity) {
		boolean inventoryUpdated = false;
		final ProductOrder productOrder = new ProductOrder(product.getName(), product.getPrice(), selectedQuantity);

		lock.lock();

		try {
			final Product productFromDb = productRepository.findById(product.getId()).get();

			int existingQuantity = productFromDb.getAvailableQuantity();
			if (existingQuantity > 0 && selectedQuantity <= existingQuantity) {
				int newQuantity = existingQuantity - selectedQuantity;
				productFromDb.setAvailableQuantity(newQuantity);

				productRepository.save(productFromDb);
				productOrderRepository.save(productOrder);

				inventoryUpdated = true;
			}
		} finally {
			lock.unlock();
		}
		return inventoryUpdated;
	}

	private boolean isValidItem(final Product product, final long selectedQuantity,
			final List<OrderValidationError> validations) {
		boolean isValid = true;

		if (product.getAvailableQuantity() > 0) {
			if (product.getAvailableQuantity() < selectedQuantity) {
				isValid = false;
				addValidationError(validations, ApiConstant.CODE_LOW_STOCK,
						String.format(ApiConstant.MSG_LOW_STOCK, product.getName(), product.getAvailableQuantity()));
			}
		} else {
			isValid = false;
			addValidationError(validations, ApiConstant.CODE_OUT_OF_STOCK,
					String.format(ApiConstant.MSG_OUT_OF_STOCK, product.getName()));
		}

		return isValid;
	}

	private void addValidationError(final List<OrderValidationError> validations, final String code,
			final String errorMessage) {
		validations.add(new OrderValidationError(code, errorMessage));
	}

}
