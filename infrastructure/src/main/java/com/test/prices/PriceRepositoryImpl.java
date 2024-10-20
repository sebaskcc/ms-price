package com.test.prices;

import com.test.prices.model.Price;
import com.test.prices.ports.spi.PriceRepository;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

  @Override
  public List<Price> findPriceByDate(Instant applyDate, Long productId, Integer brandId) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
