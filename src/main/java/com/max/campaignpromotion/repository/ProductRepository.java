package com.max.campaignpromotion.repository;

import com.max.campaignpromotion.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
