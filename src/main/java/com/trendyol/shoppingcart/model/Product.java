package com.trendyol.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * product for shopping
 */
@Data
@Builder
@EqualsAndHashCode ( of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private String id;
	@NotNull
	private String title;
	@NotNull
	private BigDecimal price;
	private Category category;
}
