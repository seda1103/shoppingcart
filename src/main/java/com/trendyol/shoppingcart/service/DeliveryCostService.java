package com.trendyol.shoppingcart.service;

import com.trendyol.shoppingcart.model.ShoppingCart;

import java.math.BigDecimal;

public interface DeliveryCostService {

	BigDecimal getDeliveryCost(ShoppingCart shoppingCart);
}
