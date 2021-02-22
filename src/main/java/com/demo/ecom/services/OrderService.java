/**
 * 
 */
package com.demo.ecom.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.constants.ApiConstant;
import com.demo.ecom.exceptions.InventoryValidationException;
import com.demo.ecom.models.OrderValidationError;
import com.demo.ecom.models.ProductDetail;
import com.demo.ecom.models.ProductOrder;
import com.demo.ecom.models.ProductOrderResponse;
import com.demo.ecom.repository.IProductRepository;
import com.demo.ecom.repository.ProductRepository;

/**
 * Service class for Order.
 * 
 * @author Varusai
 *
 */
@Service
public class OrderService implements IOrderService {
	@Autowired
	private IProductRepository productRepository;

	@Override
	public ProductOrderResponse order(final List<ProductOrder> productOrderList) throws InventoryValidationException {
		final List<OrderValidationError> validations = new ArrayList<>();
		final List<ProductOrder> successfulTransactions = new ArrayList<>();
		final List<ProductOrder> failedTransactions = new ArrayList<>();

		if (productOrderList != null && !productOrderList.isEmpty()) {
			productOrderList.stream().forEach(item -> {
				ProductDetail productDetail = ProductRepository.getProductDetail().get(item.getProductDetail().getId());

				if (isValidItem(productDetail, item.getQuantity(), validations)) {
					if (productRepository.updateInventory(productDetail, item.getQuantity())) {
						successfulTransactions.add(item);
					} else {
						failedTransactions.add(item);
						addValidationError(validations, ApiConstant.CODE_LOW_STOCK,
								String.format(ApiConstant.MSG_LOW_STOCK, productDetail.getName()));
					}
				} else {
					failedTransactions.add(item);
				}
			});
		}

		if (!failedTransactions.isEmpty() && failedTransactions.size() == productOrderList.size()) {
			throw new InventoryValidationException(validations);
		} else {
			final ProductOrderResponse response = new ProductOrderResponse();
			response.setSuccessfulTransactions(successfulTransactions);
			response.setFailedTransactions(failedTransactions);
			response.setErrors(validations);

			return response;
		}

	}

	private boolean isValidItem(final ProductDetail productDetail, final long selectedQuantity,
			final List<OrderValidationError> validations) {
		boolean isValid = true;

		if (productDetail.getAvailableQuantity() > 0) {
			if (productDetail.getAvailableQuantity() < selectedQuantity) {
				isValid = false;
				addValidationError(validations, ApiConstant.CODE_LOW_STOCK, String.format(ApiConstant.MSG_LOW_STOCK,
						productDetail.getName(), productDetail.getAvailableQuantity()));
			}
		} else {
			isValid = false;
			addValidationError(validations, ApiConstant.CODE_OUT_OF_STOCK,
					String.format(ApiConstant.MSG_OUT_OF_STOCK, productDetail.getName()));
		}

		return isValid;
	}

	private void addValidationError(final List<OrderValidationError> validations, final String code,
			final String errorMessage) {
		validations.add(new OrderValidationError(code, errorMessage));
	}

}
