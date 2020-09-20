package com.trendyol.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * coupon for cart discount
 */
@Data
@Builder
@EqualsAndHashCode (of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

	private String id;
	@NotNull
	private BigDecimal discountAmount;
	@NotNull
	private BigDecimal minimumCartAmount;

}
