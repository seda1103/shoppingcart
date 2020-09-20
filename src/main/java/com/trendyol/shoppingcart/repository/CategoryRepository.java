package com.trendyol.shoppingcart.repository;

import com.trendyol.shoppingcart.model.Category;

import java.util.Optional;

/**
 * for category db operations
 */
public interface CategoryRepository {

	Category create(Category product);

	Category update(Category product);

	Optional<Category> findByTitle(String id);

}
