package com.max.campaignpromotion.repository;

import com.max.campaignpromotion.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
