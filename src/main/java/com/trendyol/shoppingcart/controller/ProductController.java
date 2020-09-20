package com.trendyol.shoppingcart.controller;

import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping ("productservice")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping ("/create")
	public Product create(@Valid @RequestBody Product product) {
		return productService.create(product);
	}


}
