package com.trendyol.shoppingcart.service.impl;

import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.repository.CouponRepository;
import com.trendyol.shoppingcart.service.CouponService;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import com.trendyol.shoppingcart.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Optional;

/**
 * for coupon operations
 */
@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponRepository couponRepository;
	/**
	 * It is calculated discount amount according to coupons
	 * @param cartAmount to compare with coupon min. cart amount
	 * @return
	 */
	@Override
	public BigDecimal getDiscountAmount(BigDecimal cartAmount) {
		Optional<Coupon> coupon = CommonUtil.asStream(ApplicationInitializerUtil.couponList)
				.filter(cou -> cou.getMinimumCartAmount().compareTo(cartAmount) <= 0)
				.sorted(Comparator.comparing(Coupon::getDiscountAmount).reversed())
				.findFirst();
		if(coupon.isPresent()){
			return coupon.get().getDiscountAmount();
		} else {
			return BigDecimal.ZERO;
		}
	}

	@Override
	public Coupon create(Coupon coupon) {
		return couponRepository.create(coupon);
	}
}
