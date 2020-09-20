package com.trendyol.shoppingcart.controller;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.service.CampaignService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping ("campaignservice")
@Api
public class CampaignController {

	@Autowired
	private CampaignService campaignService;

	@PostMapping ("/create")
	public Campaign create(@Valid @RequestBody Campaign campaign) {
		return campaignService.create(campaign);
	}


}
