package com.trendyol.shoppingcart.model;

import com.trendyol.shoppingcart.model.helper.ProductTotalPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provide to keep products in shopping cart with their quantity, campaign discount and  price
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

	@Builder.Default
	private Map<Product, ProductTotalPrice> productMap = new HashMap<>();
}
