package com.test.prices.jpa;

import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceEntityJpaRepository extends JpaRepository<PriceEntity, Long> {

  @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId " +
      "AND p.startDate <= :applicationDate AND p.endDate >= :applicationDate " +
      "ORDER BY p.priority DESC")
  List<PriceEntity> findByProductIdAndBrandIdAndDate(
      @Param("productId") Long productId,
      @Param("brandId") Integer brandId,
      @Param("applicationDate") Instant applicationDate);
}
