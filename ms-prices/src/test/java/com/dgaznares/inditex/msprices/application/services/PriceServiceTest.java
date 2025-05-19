package com.dgaznares.inditex.msprices.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dgaznares.inditex.msprices.domine.model.PriceDto;
import com.dgaznares.inditex.msprices.domine.ports.in.RetreavePricesUseCase;

class PriceServiceTest {
	
	@Mock RetreavePricesUseCase retreavePrices;
	@InjectMocks PriceService priceService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
	void getFinalPriceTest_ExpectecPrice() {
		
		OffsetDateTime dateTime = OffsetDateTime.parse("2020-06-14T10:00:00Z");
		Long productId=35455L;
		Long brandId=1L;
		PriceDto expectedDto = PriceDto.builder()
				.brandId(1L)
				.endDate(OffsetDateTime.parse("2020-06-14T00:00:00+02:00"))
				.price(35.5)
				.priceList(1)
				.productId(35455L)
				.startDate(OffsetDateTime.parse("2020-06-14T00:00:00+02:00"))
				.build();
		Optional<PriceDto> expected = Optional.of(expectedDto);
		
		when(retreavePrices.getFinalPrice(dateTime, productId, brandId)).thenReturn(expected);
		Optional<PriceDto> result = priceService.getFinalPrice(dateTime, productId, brandId);
		
		assertTrue(result.isPresent());
		assertEquals(expected.get().getPrice(), result.get().getPrice()); 
		verify(retreavePrices, times(1)).getFinalPrice(dateTime, productId, brandId);
		
	}
	@Test
	void getFinalPriceTest_EmptyDto() {
		
		OffsetDateTime dateTime = OffsetDateTime.parse("2021-06-14T10:00:00Z");
		Long productId=35455L;
		Long brandId=1L;
		
		when(retreavePrices.getFinalPrice(dateTime, productId, brandId)).thenReturn(Optional.empty());
		Optional<PriceDto> result = priceService.getFinalPrice(dateTime, productId, brandId);
		
		assertFalse(result.isPresent());
		verify(retreavePrices, times(1)).getFinalPrice(dateTime, productId, brandId);
		
	}
	
	

	

}
