package com.trendyol.shoppingcart.service;

import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.model.ShoppingCart;

import java.math.BigDecimal;

public interface ShoppingCartService {

	ShoppingCart addItem(Product product, BigDecimal quantity);

	ShoppingCart removeItem(Product product, BigDecimal quantity);

	BigDecimal getTotalAmount();

	BigDecimal getDeliveryAmount();

	BigDecimal getCouponAmount();

	BigDecimal getPaymentAmount();


}
