package com.test.prices;

import com.test.prices.jpa.PriceEntityJpaRepository;
import com.test.prices.jpa.PriceEntityMapperImpl;
import com.test.prices.model.Price;
import com.test.prices.ports.spi.PriceRepository;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

  private final PriceEntityJpaRepository jpaRepository;

  private final PriceEntityMapperImpl mapper;

  @Override
  public List<Price> findPriceByDate(Instant applyDate, Long productId, Integer brandId) {

    return jpaRepository.findByProductIdAndBrandIdAndDate(productId, brandId, applyDate).stream()
        .map(mapper::toDomain)
        .toList();
  }
}
