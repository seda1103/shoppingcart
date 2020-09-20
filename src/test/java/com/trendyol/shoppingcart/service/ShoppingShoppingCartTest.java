package com.trendyol.shoppingcart.service;

import com.trendyol.shoppingcart.exception.ProductDoesNotExistToRemoveException;
import com.trendyol.shoppingcart.exception.ProductDoesNotHaveEnoughQuantityToRemoveException;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.model.ShoppingCart;
import com.trendyol.shoppingcart.model.helper.ProductTotalPrice;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import com.trendyol.shoppingcart.util.CommonUtil;
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
class ShoppingShoppingCartTest {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Test
	void addItem() {
		ApplicationInitializerUtil.shoppingCart = new ShoppingCart();
		ApplicationInitializerUtil.init();
		for(Product product : ApplicationInitializerUtil.productList){
			shoppingCartService.addItem(product, BigDecimal.TEN);
		}
		shoppingCartService.addItem(ApplicationInitializerUtil.polo,BigDecimal.TEN);
		BigDecimal quantitySum = CommonUtil.asStream(ApplicationInitializerUtil.shoppingCart.getProductMap().values()).map(ProductTotalPrice::getQuantity).reduce(BigDecimal::add).get();
		Assertions.assertEquals(quantitySum, new BigDecimal(50));
	}

	@Test
	void removeItem() {
		ApplicationInitializerUtil.shoppingCart = new ShoppingCart();
		ApplicationInitializerUtil.init();
		for(Product product : ApplicationInitializerUtil.productList){
			shoppingCartService.addItem(product, BigDecimal.TEN);
		}

		ShoppingCart shoppingCart = shoppingCartService.removeItem(ApplicationInitializerUtil.strapless, BigDecimal.ONE);
		Assertions.assertEquals(shoppingCart.getProductMap().get(ApplicationInitializerUtil.strapless).getQuantity(), new BigDecimal(9));
	}

	@Test
	void removeItemCompletely() {
		ApplicationInitializerUtil.shoppingCart = new ShoppingCart();
		ApplicationInitializerUtil.init();
		for(Product product : ApplicationInitializerUtil.productList){
			shoppingCartService.addItem(product, BigDecimal.TEN);
		}
		ShoppingCart shoppingCart = shoppingCartService.removeItem(ApplicationInitializerUtil.strapless, new BigDecimal(10));
		Assertions.assertFalse(shoppingCart.getProductMap().containsKey(ApplicationInitializerUtil.strapless));
	}

	@Test
	void removeItemWithDoesNotExistException() {
		ApplicationInitializerUtil.shoppingCart = new ShoppingCart();
		ApplicationInitializerUtil.init();
		for(Product product : ApplicationInitializerUtil.productList){
			shoppingCartService.addItem(product, BigDecimal.TEN);
		}

		Assertions.assertThrows(ProductDoesNotExistToRemoveException.class, () ->
			shoppingCartService.removeItem(Product.builder().id(UUIDGeneratorUtil.getUUID()).build(), BigDecimal.ONE)
		);
	}

	@Test
	void removeItemWithDoesNotContainEnoughAmountException() {
		ApplicationInitializerUtil.shoppingCart = new ShoppingCart();
		ApplicationInitializerUtil.init();
		for(Product product : ApplicationInitializerUtil.productList){
			shoppingCartService.addItem(product, BigDecimal.TEN);
		}

		Assertions.assertThrows(ProductDoesNotHaveEnoughQuantityToRemoveException.class, () ->
			shoppingCartService.removeItem(ApplicationInitializerUtil.strapless, new BigDecimal(20))
		);
	}

	@Test
	void getTotalAmount() {
		ApplicationInitializerUtil.shoppingCart = new ShoppingCart();
		ApplicationInitializerUtil.init();
		shoppingCartService.addItem(ApplicationInitializerUtil.polo,BigDecimal.ONE);

		Assertions.assertEquals(shoppingCartService.getTotalAmount(),ApplicationInitializerUtil.polo.getPrice());
	}

	@Test
	void getDeliveryAmount() {
		ApplicationInitializerUtil.shoppingCart = new ShoppingCart();
		ApplicationInitializerUtil.init();
		shoppingCartService.addItem(ApplicationInitializerUtil.polo,BigDecimal.ONE);

		Assertions.assertEquals(shoppingCartService.getDeliveryAmount(),CommonUtil.DELIVERY_COST_FOR_CATEGORY.add(CommonUtil.DELIVERY_COST_FOR_PRODUCT));
	}

	@Test
	void getCouponAmount() {
		ApplicationInitializerUtil.shoppingCart = new ShoppingCart();
		ApplicationInitializerUtil.init();
		ApplicationInitializerUtil.strapless.setPrice(new BigDecimal(50));
		shoppingCartService.addItem(ApplicationInitializerUtil.strapless,BigDecimal.ONE);

		Assertions.assertEquals( BigDecimal.ZERO, shoppingCartService.getCouponAmount());
	}

	@Test
	void getPaymentAmount() {
		ApplicationInitializerUtil.shoppingCart = new ShoppingCart();
		ApplicationInitializerUtil.init();
		ApplicationInitializerUtil.strapless.setPrice(new BigDecimal(50));
		shoppingCartService.addItem(ApplicationInitializerUtil.strapless,new BigDecimal(2));

		Assertions.assertEquals(shoppingCartService.getPaymentAmount(), new BigDecimal(97));
	}
}
