package com.test.prices.ports.spi;

import com.test.prices.model.Price;
import java.time.Instant;
import java.util.List;

public interface PriceRepository {

  List<Price> findPriceByDate(Instant applyDate, Long productId, Integer brandId);

}
