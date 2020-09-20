package com.trendyol.shoppingcart.repository.impl;

import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.repository.CouponRepository;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import com.trendyol.shoppingcart.util.UUIDGeneratorUtil;
import org.springframework.stereotype.Repository;

@Repository
public class CouponRepositoryImpl implements CouponRepository {

	@Override
	public Coupon create(Coupon coupon) {
		coupon.setId(UUIDGeneratorUtil.getUUID());
		ApplicationInitializerUtil.couponList.add(coupon);
		return coupon;
	}

}
