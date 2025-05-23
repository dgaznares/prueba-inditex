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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/prices")
@Slf4j
public class PriceController {
	
	private final PriceService priceService;

	public PriceController(PriceService priceService) {
		this.priceService = priceService;
	}

	
	@Operation(
		    summary = "Obtiene el precio final de un producto según los parámetros recibidos",
		    description = "Devuelve el precio final de un producto según los parámetros recibidos",
		    responses = {
		        @ApiResponse(responseCode = "200", description = "Precio encontrado"),
		        @ApiResponse(responseCode = "404", description = "Precio no encontrado")
		    }
		)
	@GetMapping
	public ResponseEntity<PriceDto> getFinalPrice(
			@Parameter(description = "Fecha de entrada", example = "2020-06-15T10:00:00Z")
			@RequestParam (name="dateTime")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime date, 
			@Parameter(description = "Id del producto", example = "3545")
			@RequestParam (name="productId")  Long product,
			@Parameter(description = "Id de la compañia", example = "1")
			@RequestParam  (name="brandId") Long brand) {
		
		log.info("Request received: {},{},{} ", date, product, brand);
		
		return priceService.getFinalPrice(date, product, brand)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new PriceNotFoundException("No price found."));				

	}
}
