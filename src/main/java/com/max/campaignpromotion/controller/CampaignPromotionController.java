package com.max.campaignpromotion.controller;

import com.max.campaignpromotion.model.Campaign;
import com.max.campaignpromotion.model.Product;
import com.max.campaignpromotion.model.ProductCategory;
import com.max.campaignpromotion.service.CampaignPromotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CampaignPromotionController {

  final CampaignPromotionService campaignPromotionService;

  public CampaignPromotionController(final CampaignPromotionService campaignPromotionService) {
    this.campaignPromotionService = campaignPromotionService;
  }

  @PostMapping("/createCampaign")
  public Campaign createCampaign(final Campaign campaign) {
    return campaignPromotionService.createCampaign(campaign);
  }

  @GetMapping("/serveAd")
  public Product serveAd(@RequestParam(value = "category") final ProductCategory category) {
    return campaignPromotionService.serveAd(category);
  }
}
