package com.test.prices;

import com.test.prices.exception.PriceNotFound;
import com.test.prices.ports.api.FindPriceByDateUseCase;
import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.PriceApi;
import org.openapitools.model.PriceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PriceController implements PriceApi {

  private final FindPriceByDateUseCase userCase;

  private final RestMapper mapper;

  @Override
  public ResponseEntity<PriceDTO> getPrice(OffsetDateTime applyDate, Long productId,
      Integer brandId) {

    return Optional.of(applyDate)
        .map(OffsetDateTime::toInstant)
        .map(convertedDateTime -> userCase.findPriceByDate(convertedDateTime, productId, brandId))
        .map(mapper::fromDomain)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new PriceNotFound("Price not found"));
  }
}
