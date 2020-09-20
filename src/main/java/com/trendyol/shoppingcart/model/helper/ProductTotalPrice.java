package com.trendyol.shoppingcart.model.helper;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * informations for per product in shopping card
 */
@Data
@Component("productBean")
public class ProductTotalPrice {

	private BigDecimal quantity;
	private BigDecimal campaignDiscountRatio;
	private BigDecimal price;

	public BigDecimal getTotalPrice(){
		return price.subtract(price.multiply(campaignDiscountRatio).divide(new BigDecimal(100))).multiply(quantity);
	}

}
