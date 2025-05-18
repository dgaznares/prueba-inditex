package com.dgaznares.inditex.msprices.infrastucture.persistence.adapters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dgaznares.inditex.msprices.domine.model.PriceDto;
import com.dgaznares.inditex.msprices.infrastucture.persistence.entities.Price;

@Component
public class PriceToPriceDtoConverter implements Converter<Price, PriceDto>{

	@Override
	public PriceDto convert(Price source) {
	      return PriceDto.builder()
	               .brandId(source.getBrandId())
	               .endDate(source.getEndDate())
	               .price(source.getPrice())
	               .priceList(source.getPriceList())
	               .productId(source.getProductId())
	               .startDate(source.getStartDate())
	               .build();
					
	}

}
