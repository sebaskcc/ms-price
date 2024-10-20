package com.test.prices;

import com.test.prices.model.Price;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.openapitools.model.PriceDTO;

@Mapper
public interface RestMapper {

  PriceDTO fromDomain(Price priceEntity);

  default OffsetDateTime instantToOffsetDateTime(Instant instant) {
    return Optional.ofNullable(instant)
        .map(inst -> inst.atOffset(ZoneOffset.UTC))
        .orElse(null);
  }

}
