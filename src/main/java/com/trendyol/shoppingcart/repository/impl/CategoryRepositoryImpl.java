package com.trendyol.shoppingcart.repository.impl;

import com.trendyol.shoppingcart.exception.CategoryAlreadyExistsException;
import com.trendyol.shoppingcart.exception.ObjectAlreadyUpdatedException;
import com.trendyol.shoppingcart.exception.ResourceNotFoundException;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.repository.CategoryRepository;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import com.trendyol.shoppingcart.util.UUIDGeneratorUtil;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

	@Override
	public Category create(Category category) {
		if(findByTitle(category.getTitle()).isPresent()){
			throw new CategoryAlreadyExistsException(category.getTitle());

		}
		category.setId(UUIDGeneratorUtil.getUUID());
		ApplicationInitializerUtil.categoryList.add(category);
		return category;
	}

	@Override
	public Category update(Category category) {
		Optional<Category> categoryOptional = findById(category.getId());
		if(categoryOptional.isPresent() && !categoryOptional.get().getVersion().equals(category.getVersion())){
			throw new ObjectAlreadyUpdatedException();
		}
		if(categoryOptional.isPresent()){
			categoryOptional.get().setTitle(category.getTitle());
			Integer version = categoryOptional.get().getVersion() + 1;
			categoryOptional.get().setVersion(version);
			return categoryOptional.get();
		}
		throw new ResourceNotFoundException();
	}

	public Optional<Category> findByTitle(String title) {
		return ApplicationInitializerUtil.categoryList.stream().filter(pro->pro.getTitle().equals(title)).findFirst();
	}

	public Optional<Category> findById(String id) {
		return ApplicationInitializerUtil.categoryList.stream().filter(pro->pro.getId().equals(id)).findFirst();
	}


}
