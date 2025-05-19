package com.dgaznares.inditex.msprices.infrastucture.controllers;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dgaznares.inditex.msprices.application.exceptions.PriceNotFoundException;
import com.dgaznares.inditex.msprices.application.services.PriceService;
import com.dgaznares.inditex.msprices.domine.model.PriceDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/prices")
@Slf4j
public class PriceController {
	
	private final PriceService priceService;

	public PriceController(PriceService priceService) {
		this.priceService = priceService;
	}

	@GetMapping
	public ResponseEntity<PriceDto> getFinalPrice(
			@RequestParam (name = "dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateTime, 
			@RequestParam (name= "productId") Long productId,
			@RequestParam (name="brandId")    Long brandId) {
		
		log.info("Request received: {},{},{} ", dateTime, productId, brandId);
		
		return priceService.getFinalPrice(dateTime, productId, brandId)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new PriceNotFoundException("No price found."));				

	}
}
