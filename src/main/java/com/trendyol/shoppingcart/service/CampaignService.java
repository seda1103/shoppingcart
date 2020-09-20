package com.trendyol.shoppingcart.service;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;

import java.math.BigDecimal;

public interface CampaignService {

	BigDecimal getCampaignByCategory(Category category);

	Campaign create(Campaign campaign);

}
