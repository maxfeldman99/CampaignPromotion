package com.max.campaignpromotion;

import com.max.campaignpromotion.dto.ProductCategory;
import com.max.campaignpromotion.entity.Campaign;
import com.max.campaignpromotion.entity.Product;
import com.max.campaignpromotion.service.CampaignPromotionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class CampaignPromotionApplicationTests {

	@Autowired
	CampaignPromotionService campaignPromotionService;

	@Test
	void test_campaign_product_exist() {
		// since 10 products inserted randomly on init, I will insert 11th product with specific Category
		// and create a campaign with the same category product id

		campaignPromotionService.createProduct(Product.builder()
			.price(new BigDecimal(500))
				.category(ProductCategory.ELECTRONICS.name())
				.build());
		final Campaign campaign = Campaign.builder()
			.name("sample campaign 1")
			.bid(new BigDecimal(100))
			.startDate(LocalDate.now())
			.products(List.of(Product.builder().id(11L).build()))
			.build();
		campaignPromotionService.createCampaign(campaign);
		Assertions.assertDoesNotThrow(() -> {
			campaignPromotionService.serveAd(ProductCategory.ELECTRONICS);
		});
	}

	@Test
	@Disabled("execute separately to make sure id correct")
	void test_non_active_campaign_is_not_fetched() {
		final LocalDate expiredCampaign = LocalDate.now().minusDays(12);

		campaignPromotionService.createProduct(Product.builder()
			.price(new BigDecimal(500))
			.category(ProductCategory.ELECTRONICS.name())
			.build());
		final Campaign campaign = Campaign.builder()
			.name("sample campaign 1")
			.bid(new BigDecimal(100))
			.startDate(expiredCampaign)
			.products(List.of(Product.builder().id(11L).build()))
			.build();
		campaignPromotionService.createCampaign(campaign);
		Assertions.assertThrows(RuntimeException.class,
			() -> campaignPromotionService.serveAd(ProductCategory.ELECTRONICS));
	}

}
