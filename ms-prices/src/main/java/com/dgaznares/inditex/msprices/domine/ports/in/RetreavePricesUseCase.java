package com.dgaznares.inditex.msprices.domine.ports.in;

import java.time.OffsetDateTime;
import java.util.Optional;

import com.dgaznares.inditex.msprices.domine.model.PriceDto;

public interface RetreavePricesUseCase {
	
	public Optional<PriceDto> getFinalPrice( OffsetDateTime date, Long productId, Long brandId); 

}
