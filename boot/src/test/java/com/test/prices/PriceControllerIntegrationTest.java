package com.test.prices;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

  static final String GET_PRICE_PATH = "/api/v1/price";

  static final String PRODUCT_ID = "35455";

  static final String BRAND_ID = "1";

  @Autowired
  private MockMvc mockMvc;

  @ParameterizedTest
  @CsvSource({
      "2020-06-14T10:00:00.000+00:00, 35.50",
      "2020-06-14T16:00:00.000+00:00, 25.45",
      "2020-06-14T21:00:00.000+00:00, 35.50",
      "2020-06-15T10:00:00.000+00:00, 30.50",
      "2020-06-16T21:00:00.000+00:00, 38.95"
  })
  void testGetPrice_OK(String applyDate, Double expectedPrice) throws Exception {
    mockMvc.perform(get(GET_PRICE_PATH)
            .param("applyDate", applyDate)
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.price").value(expectedPrice));
  }

  @Test
  void testGetPrice_Price_not_found() throws Exception {
    mockMvc.perform(get(GET_PRICE_PATH)
            .param("applyDate", "2020-06-14T10:00:00.000+00:00")
            .param("productId", PRODUCT_ID + 1)
            .param("brandId", BRAND_ID))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.status").value("404"));
  }

  @Test
  void testGetPrice_Invalid_dattime_format() throws Exception {
    mockMvc.perform(get(GET_PRICE_PATH)
            .param("applyDate", "2020-06-14T10:00:00.000")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetPrice_MissingArgument() throws Exception {
    mockMvc.perform(get(GET_PRICE_PATH)
            .param("applyDate", "2020-06-14T10:00:00.000+00:00")
            .param("brandId", BRAND_ID))
        .andExpect(status().isBadRequest());
  }

}