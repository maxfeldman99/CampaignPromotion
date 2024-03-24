package com.max.campaignpromotion;

import com.max.campaignpromotion.entity.Product;
import com.max.campaignpromotion.dto.ProductCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ProductFactory {
  private static final List<String> TITLES = List.of("Product x", "Product y", "Product z");
  private static final List<ProductCategory> PRODUCT_CATEGORIES = Arrays.asList(ProductCategory.values());
  private static final Integer PRODUCTS_AMOUNT = 10;

  public static List<Product> createRandomProducts() {
    final List<Product> randomProducts = new ArrayList<>();
    for (int i = 0; i < PRODUCTS_AMOUNT; i++) {
      randomProducts.add(createProduct());
    }
    return randomProducts;
  }
  private static Product createProduct() {
    return Product.builder()
      .title(TITLES.get(new Random().nextInt(TITLES.size())))
      .category(PRODUCT_CATEGORIES.get(new Random().nextInt(PRODUCT_CATEGORIES.size())).name())
      .price(generateRandomBigDecimal(BigDecimal.valueOf(5000L)))
      .serialNumber(UUID.randomUUID().toString())
      .build();
  }

  private static BigDecimal generateRandomBigDecimal(final BigDecimal max) {
    Random random = new Random();
    BigDecimal range = max.subtract(BigDecimal.ONE);
    return BigDecimal.ONE.add(range.multiply(BigDecimal.valueOf(random.nextDouble())));
  }

}
