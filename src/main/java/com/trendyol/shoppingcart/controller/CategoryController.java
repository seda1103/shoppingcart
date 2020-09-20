package com.trendyol.shoppingcart.controller;

import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping ("categoryservice")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping ("/create")
	public Category create(@Valid @RequestBody Category category) {
		return categoryService.create(category);
	}

	@PutMapping ("/update")
	public Category update(@Valid @RequestBody Category category) {
		return categoryService.update(category);
	}


}
