package com.trendyol.shoppingcart.repository.impl;

import com.trendyol.shoppingcart.exception.ProductAlreadyExistsException;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.repository.ProductRepository;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import com.trendyol.shoppingcart.util.UUIDGeneratorUtil;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@Override
	public Product create(Product product) {
		if(findByTitle(product.getTitle()).isPresent()){
			throw new ProductAlreadyExistsException(product.getTitle());

		}
		product.setId(UUIDGeneratorUtil.getUUID());
		ApplicationInitializerUtil.productList.add(product);
		return product;
	}


	public Optional<Product> findByTitle(String title) {
		return ApplicationInitializerUtil.productList.stream().filter(pro->pro.getTitle().equals(title)).findFirst();
	}
}
