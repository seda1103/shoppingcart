package com.trendyol.shoppingcart.controller;

import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping ("couponservice")
public class CouponController {

	@Autowired
	private CouponService couponService;

	@PostMapping ("/create")
	public Coupon create(@Valid @RequestBody Coupon coupon) {
		return couponService.create(coupon);
	}


}
