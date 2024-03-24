package com.max.campaignpromotion.dto;

import java.math.BigDecimal;

public interface ProductProjection {
  Long getId();
  String getTitle();
  String getCategory();
  BigDecimal getPrice();
  String getSerialNumber();
}
