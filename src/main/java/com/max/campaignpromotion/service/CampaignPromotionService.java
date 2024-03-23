package com.max.campaignpromotion.service;

import com.max.campaignpromotion.ProductFactory;
import com.max.campaignpromotion.model.Campaign;
import com.max.campaignpromotion.model.Product;
import com.max.campaignpromotion.model.ProductCategory;
import com.max.campaignpromotion.repository.CampaignRepository;
import com.max.campaignpromotion.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CampaignPromotionService {
  final CampaignRepository campaignRepository;
  final ProductRepository productRepository;

  public CampaignPromotionService(final CampaignRepository campaignRepository, final ProductRepository productRepository) {
    this.campaignRepository = campaignRepository;
    this.productRepository = productRepository;
  }

  @PostConstruct
  private void init() {
    try {
      log.info("inserting dummy products to db...");
      productRepository.saveAll(ProductFactory.createRandomProducts());
    } catch (final Exception e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException("error on attempt to generate dummy products %s".formatted(e.getMessage()), e);
    }

  }

  public Campaign createCampaign(final Campaign campaign) {
    try {
      return campaignRepository.save(campaign);
    } catch (final Exception e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException("error on attempt to save campaign : %s".formatted(e.getMessage()), e);
    }
  }

  public Product serveAd(final ProductCategory category) {
    return new Product();
  }
}
