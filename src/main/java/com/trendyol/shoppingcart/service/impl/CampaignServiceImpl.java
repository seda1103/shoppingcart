package com.trendyol.shoppingcart.service.impl;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.repository.CampaignRepository;
import com.trendyol.shoppingcart.service.CampaignService;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import com.trendyol.shoppingcart.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * for campaign operations
 */
@Service
public class CampaignServiceImpl implements CampaignService {

	@Autowired
	private CampaignRepository campaignRepository;


	@Override
	public Campaign create(Campaign campaign) {
		return campaignRepository.create(campaign);
	}

	/**
	 * calculate discount ratio for category
	 * this method call getRatio method recursively
	 * to find maximum ratio for product category and its parent categories
	 * @param category this is product category
	 * @return
	 */
	@Override
	public BigDecimal getCampaignByCategory(Category category) {
		List<BigDecimal> list = getRatio(category, new ArrayList<>());
		Optional<BigDecimal> campaignOpt = CommonUtil.asStream(list).sorted(Comparator.reverseOrder()).findFirst();
		if(campaignOpt.isPresent()){
			return campaignOpt.get();
		} else {
			return BigDecimal.ZERO;
		}

	}

	/**
	 *
	 * @param category
	 * @param list to keep all ratio for category
	 * @return
	 */
	private List<BigDecimal> getRatio(Category category, List<BigDecimal> list) {
		BigDecimal ratio = getBiggestRatioByCategory(category);
		if(CommonUtil.greater(ratio,BigDecimal.ZERO)){
			list.add(ratio);
		}
		// if there is parent category for category call this method recursively
		if(category.getParentCategory() != null){
			return getRatio(category.getParentCategory(), list);
		}

		return list;
	}

	private BigDecimal getBiggestRatioByCategory(Category category){
		BigDecimal ratio= BigDecimal.ZERO;
		for(Campaign campaign : ApplicationInitializerUtil.campaignList){
			if(campaign.getCategory().equals(category) && CommonUtil.lower(ratio,campaign.getDiscountRatio())){
				ratio = campaign.getDiscountRatio();
			}
		}
		return ratio;
	}
}
