package com.dgaznares.inditex.msprices.domine.model;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceDto {

	private Long brandId;
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
	private Integer priceList;
	private Long productId;
	private Double price;

}
