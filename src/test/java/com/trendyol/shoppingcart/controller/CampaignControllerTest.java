package com.trendyol.shoppingcart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.service.CampaignService;
import com.trendyol.shoppingcart.service.ProductService;
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
@WebMvcTest (controllers = CampaignController.class)
class CampaignControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CampaignService campaignService;

	@Test
	void create() throws Exception {
		Campaign campaign = Campaign.builder().discountRatio(new BigDecimal(10)).category(Category.builder().id(UUIDGeneratorUtil.getUUID()).build()).build();
		mockMvc.perform(post("/campaignservice/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(campaign)))
				.andExpect(status().isOk());
	}

	@Test
	void createWithStatus400() throws Exception {
		ApplicationInitializerUtil.init();
		Campaign campaign = Campaign.builder().discountRatio(new BigDecimal(10)).build();
		mockMvc.perform(post("/campaignservice/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(campaign)))
				.andExpect(status().isBadRequest());
	}
}
