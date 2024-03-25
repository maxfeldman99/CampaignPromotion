package com.max.campaignpromotion.service;

import com.max.campaignpromotion.ProductFactory;
import com.max.campaignpromotion.dto.ProductProjection;
import com.max.campaignpromotion.entity.Campaign;
import com.max.campaignpromotion.dto.ProductCategory;
import com.max.campaignpromotion.entity.Product;
import com.max.campaignpromotion.repository.CampaignRepository;
import com.max.campaignpromotion.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

  public ProductProjection serveAd(final ProductCategory category) {
    log.debug("serveAd with category {}", category.name());
    final Optional<ProductProjection> highestPromotedProductByCategory = campaignRepository.findHighestPromotedProductByCategory(category.name());
    if (highestPromotedProductByCategory.isPresent()) {
      final ProductProjection highestPromotedProduct = highestPromotedProductByCategory.get();
      log.info("promoted product with highest bid fetched : {}", highestPromotedProduct);
      return highestPromotedProduct;
    } else {
      log.info("no promoted product for the matching category found , fetching product with highest bid...");
      final Optional<ProductProjection> highestPromotedProduct = campaignRepository.findHighestPromotedProduct();
      return highestPromotedProduct.orElseThrow(() -> new RuntimeException("error on attempt to fetch highest promoted product: active campaigns must be inserted before using serveAd"));
    }
  }

  public Product createProduct(final Product product) {
    try {
      return productRepository.save(product);
    } catch (final Exception e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException("error on attempt to create new product: %s".formatted(e.getMessage()));
    }

  }
}
