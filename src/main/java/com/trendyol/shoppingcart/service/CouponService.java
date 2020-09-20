package com.trendyol.shoppingcart.service;

import com.trendyol.shoppingcart.model.Coupon;

import java.math.BigDecimal;

public interface CouponService {

	BigDecimal getDiscountAmount(BigDecimal cartAmount);

	Coupon create(Coupon coupon);
}
