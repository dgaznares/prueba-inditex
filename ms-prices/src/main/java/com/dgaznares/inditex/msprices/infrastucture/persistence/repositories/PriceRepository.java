package com.dgaznares.inditex.msprices.infrastucture.persistence.repositories;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dgaznares.inditex.msprices.infrastucture.persistence.entities.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
	@Query("SELECT p "
			+ "FROM Price p "
			+ "WHERE :date BETWEEN p.startDate AND p.endDate "
			+ "AND p.productId = :productId "
			+ "AND p.brandId = :brandId "
			+ "ORDER BY p.priority DESC")
	public Page<Price> getFinalPrice(
			@Param("date") OffsetDateTime date, 
			@Param("productId") Long productId,
			@Param("brandId") Long brandId,
			Pageable pageable );
	
}
