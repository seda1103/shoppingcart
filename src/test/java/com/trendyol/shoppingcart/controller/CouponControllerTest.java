package com.trendyol.shoppingcart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.service.CampaignService;
import com.trendyol.shoppingcart.service.CouponService;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import com.trendyol.shoppingcart.util.UUIDGeneratorUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith (SpringExtension.class)
@WebMvcTest (controllers = CouponController.class)
class CouponControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CouponService couponService;

	@Test
	void create() throws Exception {
		Coupon coupon = Coupon.builder().minimumCartAmount(new BigDecimal(100)).discountAmount(new BigDecimal(10)).build();
		mockMvc.perform(post("/couponservice/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(coupon)))
				.andExpect(status().isOk());
	}

	@Test
	void createWithStatus400() throws Exception {
		ApplicationInitializerUtil.init();
		Coupon coupon = Coupon.builder().discountAmount(new BigDecimal(10)).build();
		mockMvc.perform(post("/couponservice/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(coupon)))
				.andExpect(status().isBadRequest());
	}
}
