package com.test.prices.ports.api;

import com.test.prices.exception.PriceNotFound;
import com.test.prices.model.Price;
import java.time.Instant;

public interface FindPriceByDateUseCase {

  Price findPriceByDate(Instant applyDate, Long productId, Integer brandId) throws PriceNotFound;

}
