package com.trendyol.shoppingcart.repository.impl;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.repository.CampaignRepository;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import com.trendyol.shoppingcart.util.UUIDGeneratorUtil;
import org.springframework.stereotype.Repository;

@Repository
public class CampaignRepositoryImpl implements CampaignRepository {

	@Override
	public Campaign create(Campaign campaign) {
		campaign.setId(UUIDGeneratorUtil.getUUID());
		ApplicationInitializerUtil.campaignList.add(campaign);
		return campaign;
	}

}
