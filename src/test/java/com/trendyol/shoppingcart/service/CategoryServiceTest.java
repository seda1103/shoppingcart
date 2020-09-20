package com.trendyol.shoppingcart.service;

import com.trendyol.shoppingcart.exception.CategoryAlreadyExistsException;
import com.trendyol.shoppingcart.exception.ObjectAlreadyUpdatedException;
import com.trendyol.shoppingcart.exception.ResourceNotFoundException;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import com.trendyol.shoppingcart.util.UUIDGeneratorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith (SpringExtension.class)
@EnableAutoConfiguration
@ComponentScan (basePackages = {"com.trendyol.shoppingcart"}, lazyInit = true)
@ActiveProfiles ("test")
@TestPropertySource (locations = "classpath:/application-test.properties")
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	void createCategory() {
		ApplicationInitializerUtil.init();
		Category category = Category.builder().title("computer").build();
		Category addedProduct = categoryService.create(category);
		Assertions.assertNotNull(addedProduct.getId());
	}

	@Test
	void createCategoryWithAlreadyExists() {
		ApplicationInitializerUtil.init();
		Category category = Category.builder().title("electronik").id(UUIDGeneratorUtil.getUUID()).build();
		categoryService.create(category);
		Assertions.assertThrows(CategoryAlreadyExistsException.class, () -> {
			categoryService.create(category);
		});
	}

	@Test
	void updateCategory() {
		ApplicationInitializerUtil.init();
		Category category = Category.builder().title("garden").build();
		Category addedProduct = categoryService.create(category);
		Integer version = addedProduct.getVersion();
		Category categoryFormList = categoryService.findByTitle("garden");
		Category updatedCategory = categoryService.update(categoryFormList);
		Assertions.assertNotEquals(updatedCategory.getVersion(), version);
	}



	@Test
	void updateCategoryWithNotFoundException() {
		ApplicationInitializerUtil.init();
		Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryService.update(Category.builder().title("baby").build()));
	}

	@Test
	void findByTitleWithNotFoundException() {
		ApplicationInitializerUtil.init();
		Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryService.findByTitle("not title"));
	}

	@Test
	void updateCategoryWithAlreadyUpdated() {
		ApplicationInitializerUtil.init();
		Category category = Category.builder().title("garden").build();
		Category addedProduct = categoryService.create(category);
		Category updatedCategory = categoryService.update(addedProduct);
		Category failCategory = Category.builder().title("garden").id(updatedCategory.getId()).build();
		Assertions.assertThrows(ObjectAlreadyUpdatedException.class, () -> {
			categoryService.update(failCategory);
		});
	}

}
