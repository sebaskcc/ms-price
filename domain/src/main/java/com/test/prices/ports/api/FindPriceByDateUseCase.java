package com.test.prices.ports.api;

import com.test.prices.exception.PriceNotFound;
import com.test.prices.model.Price;

public interface FindPriceByDateUseCase {

  Price findPriceByDate() throws PriceNotFound;

}
