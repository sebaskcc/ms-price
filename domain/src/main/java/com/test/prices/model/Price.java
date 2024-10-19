package com.test.prices.model;

import java.time.Instant;
import java.util.Currency;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Price {

  private Integer brandId;

  private Long productId;

  private Instant startDate;

  private Instant endDate;

  private Long priceList;

  private Integer priority;

  private Double price;

  private Currency currency;

}
