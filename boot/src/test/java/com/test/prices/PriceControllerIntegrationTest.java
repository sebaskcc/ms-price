package com.test.prices;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Disabled
class PriceControllerIntegrationTest {

  static final String GET_PRICE_PATH = "/api/v1/price";

  static final String PRODUCT_ID = "35455";

  static final String BRAND_ID = "1";

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetPrice_14_1000() throws Exception {
    mockMvc.perform(get(GET_PRICE_PATH)
            .param("applyDate", "2020-06-14T10:00:00.000+00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.price").value(35.50));
  }

  @Test
  void testGetPrice_14_1600() throws Exception {
    mockMvc.perform(get(GET_PRICE_PATH)
            .param("applyDate", "2020-06-14T16:00:00.000+00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.price").value(25.45));
  }

  @Test
  void testGetPrice_14_2100() throws Exception {
    mockMvc.perform(get(GET_PRICE_PATH)
            .param("applyDate", "2020-06-14T21:00:00.000+00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.price").value(35.50));
  }

  @Test
  void testGetPrice_15_1000() throws Exception {
    mockMvc.perform(get(GET_PRICE_PATH)
            .param("applyDate", "2020-06-15T10:00:00.000+00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.price").value(30.50));
  }

  @Test
  public void testGetPrice_16_2100() throws Exception {
    mockMvc.perform(get(GET_PRICE_PATH)
            .param("applyDate", "2020-06-16T21:00:00.000+00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.price").value(38.95));
  }

}