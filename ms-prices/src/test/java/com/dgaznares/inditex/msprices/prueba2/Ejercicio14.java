package com.dgaznares.inditex.msprices.prueba2;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ejercicio14 {
	
	/**
	 * Contexto: Tienes una lista de cadenas que representan posibles códigos (ej. "A123", "B456", "C789"). 
	 * Quieres contar cuántos de estos códigos son válidos, 
	 * donde un código válido es aquel que comienza con la letra 'A'.
	 */
	public static void main (String[] args) {
		
		List<String> listaCadenas = List.of("A123", "B456", "C789");
		long validas = listaCadenas.stream()
				.filter(c -> c.startsWith("A"))
				.count();
		log.info("Validas:  {}", validas);		
				
				
	}
}
