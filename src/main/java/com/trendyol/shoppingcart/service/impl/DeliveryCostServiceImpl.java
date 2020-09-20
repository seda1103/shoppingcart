package com.trendyol.shoppingcart.service.impl;

import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.model.ShoppingCart;
import com.trendyol.shoppingcart.model.helper.ProductTotalPrice;
import com.trendyol.shoppingcart.service.DeliveryCostService;
import com.trendyol.shoppingcart.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * for delivery cost calculation
 */
@Service
public class DeliveryCostServiceImpl implements DeliveryCostService {

	/**
	 * I got this logic from internet
	 * @param shoppingCart
	 * @return
	 */
	@Override
	public BigDecimal getDeliveryCost(ShoppingCart shoppingCart) {
		BigDecimal quantityOfProduct = CommonUtil.asStream(shoppingCart.getProductMap().values()).map(ProductTotalPrice::getQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
		BigDecimal numberOfCategory =new BigDecimal(CommonUtil.asStream(shoppingCart.getProductMap().keySet()).map(Product::getCategory).collect(Collectors.toSet()).size());
		return quantityOfProduct.multiply(CommonUtil.DELIVERY_COST_FOR_PRODUCT).add(numberOfCategory.multiply(CommonUtil.DELIVERY_COST_FOR_CATEGORY));
	}
}
