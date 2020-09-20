package com.trendyol.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * discount ratio for categories
 */
@Data
@Builder
@EqualsAndHashCode (of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Campaign  {

	private String id;
	@NotNull
	private BigDecimal discountRatio;
	@NotNull
	private Category category;
}
