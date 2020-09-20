package com.trendyol.shoppingcart.service;

import com.trendyol.shoppingcart.model.Coupon;
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
class CouponServiceTest {

	@Autowired
	private CouponService couponService;


	@Test
	void createCoupon() {
		Coupon coupon = Coupon.builder().discountAmount(BigDecimal.TEN).minimumCartAmount(new BigDecimal(100)).build();
		Coupon addedCoupon = couponService.create(coupon);
		Assertions.assertNotNull(addedCoupon.getId());
	}
}
