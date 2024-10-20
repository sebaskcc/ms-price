package com.test.prices;

import com.test.prices.exception.PriceNotFound;
import com.test.prices.model.Price;
import com.test.prices.ports.api.FindPriceByDateUseCase;
import com.test.prices.ports.spi.PriceRepository;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindPriceService implements FindPriceByDateUseCase {

  private final PriceRepository priceRepository;

  @Override
  public Price findPriceByDate(Instant applyDate, Long productId, Integer brandId)
      throws PriceNotFound {

    return this.priceRepository.findPriceByDate(applyDate, productId, brandId)
        .stream()
        .findFirst()
        .orElseThrow(() -> new PriceNotFound("Price not found"));
  }
}
