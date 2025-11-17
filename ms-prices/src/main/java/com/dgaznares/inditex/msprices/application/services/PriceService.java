package com.dgaznares.inditex.msprices.application.services;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dgaznares.inditex.msprices.domine.model.PriceDto;
import com.dgaznares.inditex.msprices.domine.ports.in.RetreavePricesUseCase;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PriceService {
	
	private RetreavePricesUseCase retreavePrices;

	public PriceService(RetreavePricesUseCase retreavePrices) {		
		this.retreavePrices = retreavePrices;
	}
	
	
	public Optional<PriceDto> getFinalPrice(OffsetDateTime date, Long productId, Long brandId) {
		log.info("getFinalPrice: {},{}, {}", date, productId, brandId);
		return retreavePrices.getFinalPrice(date, productId, brandId);
	}
	

}
