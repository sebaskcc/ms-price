package com.test.prices;

import static com.test.prices.PriceObjectMother.createPriceWithCustomPriceList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;

import com.test.prices.exception.PriceNotFound;
import com.test.prices.model.Price;
import com.test.prices.ports.spi.PriceRepository;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindPriceServiceTest {

  static final Long PRODUCT_ID = 3333333333L;

  static final Integer BRAND_ID = 1;

  @Mock
  PriceRepository priceRepository;

  @InjectMocks
  FindPriceService findPriceService;

  @Test
  void findPriceByDate_Price_not_found() {
    // GIVEN
    List<Price> repositoryResult = List.of();
    Instant applyDate = Instant.now();

    // WHEN
    Mockito.when(
        priceRepository.findPriceByDate(any(), anyLong(), anyInt())
    ).thenReturn(repositoryResult);

    // THEN
    assertThrows(PriceNotFound.class, () -> {
      this.findPriceService.findPriceByDate(applyDate, PRODUCT_ID, BRAND_ID);
    });
  }

  @Test
  void findPriceByDate_Get_First_Price() {
    // GIVEN
    List<Price> repositoryResult = List.of(
        createPriceWithCustomPriceList(1L),
        createPriceWithCustomPriceList(2L),
        createPriceWithCustomPriceList(3L));
    Instant applyDate = Instant.now();

    // WHEN
    Mockito.when(
        priceRepository.findPriceByDate(any(), anyLong(), anyInt())
    ).thenReturn(repositoryResult);

    // THEN
    Price result = this.findPriceService.findPriceByDate(applyDate, PRODUCT_ID, BRAND_ID);
    assertThat(result.getPriceList()).isEqualTo(1L);
  }

}