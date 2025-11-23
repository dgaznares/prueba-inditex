package com.dgaznares.inditex.msprices.prueba5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Prueba5 {
	
	/**
	 * Dado un stream de cadenas que representan órdenes de productos, 
	 * debes procesarlas para calcular el precio total de las órdenes válidas.
	 */

	

	
	public static void main(String[] args) {
		
		List<String> rawOrders = Arrays.asList(
	            "Laptop:2A:1250.00",        // Válida: 2500.00
	            "Mouse:10:25.50",          // Válida: 255.00
	            "Keyboard-5-50.00",        // Inválida (delimitador incorrecto)
	            "Monitor:3:300.00",        // Válida: 900.00
	            "Webcam:X:75.00",          // Inválida (cantidad 'X')
	            "Pendrive:1:10.00"         // Válida: 10.00
	        );
		
		Function<String, Optional<Double>> tratarCadena = cadena -> {
			
			try {
				String[] datos = cadena.split(":");
				if (datos.length != 3)
					return Optional.empty();
				Double resultado = Integer.parseInt(datos[1].trim()) * Double.valueOf(datos[2].trim());
				return Optional.of(resultado); 
			}catch(NumberFormatException n){
				 throw n;
			}	
		};
		
		double resultado = rawOrders.stream()
		.map(tratarCadena)
		.flatMap(Optional::stream)
		.collect(Collectors.summingDouble(Double::doubleValue));
		
		
		log.info("El resultado completo es: {}", resultado);
		
	}
	
}
