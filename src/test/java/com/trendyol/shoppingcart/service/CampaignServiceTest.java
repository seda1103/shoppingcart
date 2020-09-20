package com.trendyol.shoppingcart.service;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith (SpringExtension.class)
@EnableAutoConfiguration
@ComponentScan (basePackages = {"com.trendyol.shoppingcart"}, lazyInit = true)
@ActiveProfiles ("test")
@TestPropertySource (locations = "classpath:/application-test.properties")
class CampaignServiceTest {

	@Autowired
	private CampaignService campaignService;


	@Test
	void createCoupon() {
		Campaign campaign = Campaign.builder().discountRatio(BigDecimal.TEN).category(ApplicationInitializerUtil.apple).build();
		Campaign addedCampaign = campaignService.create(campaign);
		Assertions.assertNotNull(addedCampaign.getId());
	}
}
