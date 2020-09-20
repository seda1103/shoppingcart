package com.trendyol.shoppingcart.service.impl;

import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.repository.ProductRepository;
import com.trendyol.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * for product crud operations
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product create(Product product) {
		return productRepository.create(product);
	}

}
