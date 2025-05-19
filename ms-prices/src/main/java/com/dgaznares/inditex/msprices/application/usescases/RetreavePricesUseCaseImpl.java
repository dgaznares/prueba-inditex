package com.dgaznares.inditex.msprices.application.usescases;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dgaznares.inditex.msprices.domine.model.PriceDto;
import com.dgaznares.inditex.msprices.domine.ports.in.RetreavePricesUseCase;
import com.dgaznares.inditex.msprices.domine.ports.out.PriceRepositoryPort;

@Service
public class RetreavePricesUseCaseImpl implements RetreavePricesUseCase{
	
	private PriceRepositoryPort priceRepositoryPort;
	
	public RetreavePricesUseCaseImpl(PriceRepositoryPort priceRepositoryPort) {
		this.priceRepositoryPort = priceRepositoryPort;
	}


	@Override
	public Optional<PriceDto> getFinalPrice(OffsetDateTime date, Long productId, Long brandId) {
		return priceRepositoryPort.getFinalPrice(date, productId, brandId);
	}

}
