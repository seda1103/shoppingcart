package com.trendyol.shoppingcart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.service.CategoryService;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith (SpringExtension.class)
@WebMvcTest (controllers = CategoryController.class)
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CategoryService categoryService;

	@Test
	void create() throws Exception {
		Category category = Category.builder().title("deneme").build();
		mockMvc.perform(post("/categoryservice/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(category)))
				.andExpect(status().isOk());
	}

	@Test
	void createWithStatus400() throws Exception {
		ApplicationInitializerUtil.init();
		Category category = Category.builder().title(null).build();
		mockMvc.perform(post("/categoryservice/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(category)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void update() throws Exception {
		ApplicationInitializerUtil.init();
		mockMvc.perform(put("/categoryservice/update")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(ApplicationInitializerUtil.apple)))
				.andExpect(status().isOk());
	}
}
