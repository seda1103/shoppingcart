package com.trendyol.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * category for products
 */
@Data
@Builder
@EqualsAndHashCode (of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Category{

	private String id;
	@NotNull
	private String title;
	private Category parentCategory;
	@Builder.Default
	private Integer version = 0;
}
