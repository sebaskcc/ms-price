package com.test.prices.jpa;

import com.test.prices.model.Price;
import org.mapstruct.Mapper;

@Mapper
public interface PriceEntityMapper {

  Price toDomain(PriceEntity priceJpaEntity);

}
