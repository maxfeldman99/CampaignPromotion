package com.max.campaignpromotion.repository;

import com.max.campaignpromotion.dto.ProductProjection;
import com.max.campaignpromotion.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
  @Query(value = """
    SELECT p.id, p.title, p.category, p.price, p.serial_number as serialNumber 
    FROM Campaign c
    JOIN campaign_products cp ON c.id = cp.campaign_id
    JOIN Product p ON cp.products_id = p.id
    WHERE c.start_date <= DATEADD('DAY', 10, c.start_date)
    AND p.category = :category
    ORDER BY c.bid DESC
    LIMIT 1
    """, nativeQuery = true)
  Optional<ProductProjection> findHighestPromotedProductByCategory(String category);

  @Query(value = """
    SELECT p.* FROM Campaign c
    JOIN campaign_products cp ON c.id = cp.campaign_id
    JOIN Product p ON cp.products_id = p.id
    WHERE c.start_date <= DATEADD('DAY', 10, c.start_date)
    ORDER BY c.bid DESC
    LIMIT 1
    """, nativeQuery = true)
  Optional<ProductProjection> findHighestPromotedProduct();
}
