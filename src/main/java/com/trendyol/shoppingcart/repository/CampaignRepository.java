package com.trendyol.shoppingcart.repository;

import com.trendyol.shoppingcart.model.Campaign;

/**
 * for campaign db operations
 */
public interface CampaignRepository {

	Campaign create(Campaign campaign);
}
