package com.trendyol.shoppingcart.service;

import com.trendyol.shoppingcart.model.Category;

public interface CategoryService {
	Category create(Category category);

	Category update(Category category);

	Category findByTitle(String title);
}
