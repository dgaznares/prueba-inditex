package com.dgaznares.inditex.msprices.domine.ports.out;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dgaznares.inditex.msprices.domine.model.PriceDto;
import com.dgaznares.inditex.msprices.infrastucture.persistence.adapters.PriceToPriceDtoConverter;
import com.dgaznares.inditex.msprices.infrastucture.persistence.repositories.PriceRepository;

@Service
public class PriceRepositoryAdapter implements PriceRepositoryPort{
	
	private PriceRepository priceRepository;
	private PriceToPriceDtoConverter priceToPriceDtoConverter;
	
	
	public PriceRepositoryAdapter(PriceRepository priceRepository,
			                      PriceToPriceDtoConverter priceToPriceDtoConverter) {
		this.priceRepository = priceRepository;
		this.priceToPriceDtoConverter=priceToPriceDtoConverter;
	}

	@Override
	public Optional<PriceDto> getFinalPrice(OffsetDateTime date, Long productId, Long brandId) {		
		return priceRepository.getFinalPrice(date, productId, brandId, PageRequest.of(0, 1))
	    		.stream()
	    		.findFirst()
	    		.map(priceToPriceDtoConverter::convert);
	}

}
