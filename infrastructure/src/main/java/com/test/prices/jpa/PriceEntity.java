package com.test.prices.jpa;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prices")
@Getter
@Setter
public class PriceEntity {

  @Id
  @GeneratedValue
  @Column(name = "price_list")
  private Long priceList;

  @Column(name = "brand_id")
  private Long brandId;

  @Column(name = "start_date")
  private Instant startDate;

  @Column(name = "end_date")
  private Instant endDate;

  @Column(name = "product_id")
  private Long productId;

  @Column(name = "priority")
  private Integer priority;

  @Column(name = "price")
  private Double price;

  @Column(name = "curr")
  private String currency;

}
