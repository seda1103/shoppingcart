package com.trendyol.shoppingcart.service.impl;

import com.trendyol.shoppingcart.exception.ResourceNotFoundException;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.repository.CategoryRepository;
import com.trendyol.shoppingcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * for category operations
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public Category create(Category category) {
		return categoryRepository.create(category);
	}

	@Override
	public Category update(Category category) {
		return categoryRepository.update(category);
	}

	@Override
	public Category findByTitle(String title) {
		Optional<Category> categoryOptional = categoryRepository.findByTitle(title);
		if(categoryOptional.isPresent()){
			return categoryOptional.get();
		}
		throw new ResourceNotFoundException();
	}
}
