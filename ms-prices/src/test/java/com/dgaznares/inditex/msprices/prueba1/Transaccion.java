package com.dgaznares.inditex.msprices.prueba1;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dgaznares.inditex.msprices.prueba1.dto.TransaccionDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Transaccion {
	
	private static final List<TransaccionDto> transactions = List.of(
	            TransaccionDto.builder().moneda("EUR").monto(new BigDecimal("100.00")).build(),
	            TransaccionDto.builder().moneda("USD").monto(new BigDecimal("120.50")).build(),
	            TransaccionDto.builder().moneda("GBP").monto(new BigDecimal("85.75")).build(),
	            TransaccionDto.builder().moneda("JPY").monto(new BigDecimal("15000")).build(),
	            TransaccionDto.builder().moneda("AUD").monto(new BigDecimal("130.20")).build(),
	            TransaccionDto.builder().moneda("CAD").monto(new BigDecimal("115.00")).build(),
	            TransaccionDto.builder().moneda("EUR").monto(new BigDecimal("95.40")).build(),
	            TransaccionDto.builder().moneda("USD").monto(new BigDecimal("780.00")).build(),
	            TransaccionDto.builder().moneda("INR").monto(new BigDecimal("9000")).build(),
	            TransaccionDto.builder().moneda("EUR").monto(new BigDecimal("470.30")).build()
	        );
	
	public static Map<String, List<TransaccionDto>> agrupacionBasica(List<TransaccionDto> transactions) {
		return transactions.stream()
				.collect(Collectors.groupingBy(TransaccionDto::getMoneda));
	}
	
	
	public static Map<String, BigDecimal> agrupacionYSumaPorMoneda(List<TransaccionDto> transactions) {
		return transactions.stream()
				.collect(Collectors.groupingBy(
						 TransaccionDto::getMoneda, 
						 Collectors.reducing(BigDecimal.ZERO,
								 			TransaccionDto::getMonto,
								 			BigDecimal::add
								 		))
					);	
	}
	
	public static Map<String, Long> agrupacionYContadoPorMoneda(List<TransaccionDto> transactions) {
		return transactions.stream()
				.collect(Collectors.groupingBy(
						 TransaccionDto::getMoneda, 
						 Collectors.counting()
						 ));		
	}
	
	public static Map<String, List<BigDecimal>> agrupacionYTransformar(List<TransaccionDto> transactions) {
		return transactions.stream()
				.collect(Collectors.groupingBy(
						 TransaccionDto::getMoneda, 
						 Collectors.mapping(TransaccionDto::getMonto,Collectors.toList())
						 ));		
	}
	public static void main(String[] args) {

		Map<String, List<TransaccionDto>> agrupacionBasica = Transaccion.agrupacionBasica(transactions);
		log.info("Resultado: {}", agrupacionBasica);
		
		Map<String, BigDecimal> agrupacionYSumaPorMoneda = Transaccion.agrupacionYSumaPorMoneda(transactions);
		log.info("Resultado: {}", agrupacionYSumaPorMoneda);
		
		Map<String, Long> agrupacionYContadoPorMoneda = Transaccion.agrupacionYContadoPorMoneda(transactions);
		log.info("Resultado: {}", agrupacionYContadoPorMoneda);

		Map<String, List<BigDecimal>> agrupacionYTransformar = Transaccion.agrupacionYTransformar(transactions);
		log.info("Resultado: {}", agrupacionYTransformar);
		
		
	}
}

