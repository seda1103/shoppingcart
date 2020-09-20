package com.trendyol.shoppingcart.service;

import com.trendyol.shoppingcart.exception.ProductAlreadyExistsException;
import com.trendyol.shoppingcart.model.Product;
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

import java.math.BigDecimal;

@ExtendWith (SpringExtension.class)
@EnableAutoConfiguration
@ComponentScan (basePackages = {"com.trendyol.shoppingcart"}, lazyInit = true)
@ActiveProfiles ("test")
@TestPropertySource (locations = "classpath:/application-test.properties")
class ProductServiceTest {

	@Autowired
	private ProductService productService;


	@Test
	void createProduct() {
		Product product = Product.builder().title("computer").price(new BigDecimal(100)).build();
		Product addedProduct = productService.create(product);
		Assertions.assertNotNull(addedProduct.getId());
	}

	@Test
	void createProductWithAlreadyExists() {
		ApplicationInitializerUtil.init();
		Product product = Product.builder().title("dress").id(UUIDGeneratorUtil.getUUID()).price(new BigDecimal(100)).build();
		productService.create(product);
		Assertions.assertThrows(ProductAlreadyExistsException.class, () -> {
			productService.create(product);
		});
	}


}
