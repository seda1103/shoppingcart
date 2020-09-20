package com.trendyol.shoppingcart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.service.CampaignService;
import com.trendyol.shoppingcart.service.ShoppingCartService;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import com.trendyol.shoppingcart.util.UUIDGeneratorUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith (SpringExtension.class)
@WebMvcTest (controllers = ShoppingCartController.class)
class ShoppingCartControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ShoppingCartService shoppingCartService;

	@Test
	void addItem() throws Exception {
		Product product = Product.builder().id(UUIDGeneratorUtil.getUUID()).price(BigDecimal.TEN).title("test").category(Category.builder().id(UUIDGeneratorUtil.getUUID()).build()).build();
		mockMvc.perform(post("/shoppingcartservice/additem/{quantity}",BigDecimal.TEN)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk());
	}

	@Test
	void removeItem() throws Exception {
		Product product = Product.builder().id(UUIDGeneratorUtil.getUUID()).price(BigDecimal.TEN).title("test").category(Category.builder().id(UUIDGeneratorUtil.getUUID()).build()).build();
		mockMvc.perform(post("/shoppingcartservice/removeitem/{quantity}",BigDecimal.TEN)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk());
	}


	@Test
	void getTotalAmount()  throws Exception {
		mockMvc.perform(get("/shoppingcartservice/totalamount")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void getDeliveryAmount()  throws Exception {
		mockMvc.perform(get("/shoppingcartservice/deliveryamount")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void getPaymentAmount()  throws Exception {
		mockMvc.perform(get("/shoppingcartservice/paymentamount")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void getCouponAmount()  throws Exception {
		mockMvc.perform(get("/shoppingcartservice/couponamount")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}
}
