package com.dgaznares.inditex.msprices.prueba6;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.dgaznares.inditex.msprices.prueba6.dto.Transaction;
import com.dgaznares.inditex.msprices.prueba6.dto.Type;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Prueba6 {
	
	/**
	 * Dado un stream de objetos Transaction, 
	 * debes procesarlos para agrupar las transacciones por tipo y, 
	 * para cada grupo, calcular la suma total de los montos.
	 */
	
	
	public static void main(String[] args) {
		List<Transaction> transactions = Arrays.asList(
	            new Transaction(Type.DEPOSIT, 150.00),
	            new Transaction(Type.WITHDRAWAL, 50.00),
	            new Transaction(Type.DEPOSIT, 200.00),
	            new Transaction(Type.TRANSFER, 75.00),
	            new Transaction(Type.WITHDRAWAL, 25.00),
	            new Transaction(Type.DEPOSIT, 100.00)
	        );
		
		Map<Type, List<Transaction>> resultadoAgrupado1 =  transactions.stream()
		.collect(Collectors.groupingBy(Transaction::getType));
		
		log.info("Resultado: {}", resultadoAgrupado1);
		
		Map<Type, Double> resultadoAgrupado2 =  transactions.stream()
		.collect(Collectors.groupingBy(Transaction::getType,
				Collectors.summingDouble(Transaction::getAmount)));
		
		log.info("Resultado: {}", resultadoAgrupado2);
		
		Map<Type, List<Double>> resultadoAgrupado3 =  transactions.stream()
		.collect(Collectors.groupingBy(
				Transaction::getType,
				Collectors.mapping(Transaction::getAmount,
								   Collectors.toList())));
		
		log.info("Resultado: {}", resultadoAgrupado3);
	}

}
