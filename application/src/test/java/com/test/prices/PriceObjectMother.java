package com.test.prices;

import com.test.prices.model.Price;
import java.time.Instant;
import java.util.Currency;

public class PriceObjectMother {

  static Price createDeafaultPrice() {
    return Price.builder()
        .price(30.00)
        .priceList(0L)
        .currency(Currency.getInstance("EUR"))
        .endDate(Instant.now().plusSeconds(3000L))
        .startDate(Instant.now())
        .priority(1)
        .build();
  }

  static Price createPriceWithCustomPriceList(Long priceList) {
    return Price.builder()
        .price(30.00)
        .priceList(priceList)
        .currency(Currency.getInstance("EUR"))
        .endDate(Instant.now().plusSeconds(3000L))
        .startDate(Instant.now())
        .priority(1)
        .build();
  }
}
