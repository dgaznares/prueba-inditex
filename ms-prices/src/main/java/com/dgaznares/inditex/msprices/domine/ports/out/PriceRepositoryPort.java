package com.dgaznares.inditex.msprices.domine.ports.out;

import java.time.OffsetDateTime;
import java.util.Optional;

import com.dgaznares.inditex.msprices.domine.model.PriceDto;

public interface PriceRepositoryPort {
	
	public Optional<PriceDto> getFinalPrice(OffsetDateTime date, Long productId, Long brandId);

}
