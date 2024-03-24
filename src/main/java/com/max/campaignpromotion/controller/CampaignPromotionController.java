package com.max.campaignpromotion.controller;

import com.max.campaignpromotion.dto.ProductProjection;
import com.max.campaignpromotion.entity.Campaign;
import com.max.campaignpromotion.dto.ProductCategory;
import com.max.campaignpromotion.service.CampaignPromotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  public Campaign createCampaign(@RequestBody final Campaign campaign) {
    return campaignPromotionService.createCampaign(campaign);
  }

  @GetMapping("/serveAd")
  public ProductProjection serveAd(@RequestParam(value = "category") final ProductCategory category) {
    return campaignPromotionService.serveAd(category);
  }

}
