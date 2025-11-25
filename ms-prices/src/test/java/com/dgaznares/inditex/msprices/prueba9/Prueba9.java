package com.dgaznares.inditex.msprices.prueba9;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Prueba9 {
	
	List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	
	public static void main (String[] args) {
		// A. Escribe el código Java (usando streams) para resolver el siguiente problema:
		// Dada una List<Integer> de números del 1 al 10, filtra los números pares,
		// multiplícalos por 2 y luego suma todos los resultados.
		// Resultado Esperado: El resultado debe ser un entero que representa la suma
		// de los números pares multiplicados por 2.
		
		// B. Transformar una lista de números, filtrando solo los pares y luego devolviendo 
		// el cuadrado de cada uno de esos números pares.
		Supplier<Prueba9> prueba9Sup = Prueba9::new;
		Prueba9 prueba9 = prueba9Sup.get();
		
		
		log.info("El lista de numeros es: {} ", prueba9.numeros);
		Predicate<Integer> esPar = n -> n % 2 == 0;
		
		Integer sumaDeParesDobles = prueba9.numeros.stream()
				.filter(esPar)
				.map(v-> Integer.valueOf(v) * 2)
				.reduce(0, (x, y)-> x+y);
		log.info("A: El resultados es: {}", sumaDeParesDobles);		
		
		List<Integer> paresCuadrados = prueba9.numeros.stream()
				.filter(esPar)
				.map(v-> Integer.valueOf(v) * Integer.valueOf(v))
				.toList();
		log.info("B: El resultados es: {}", paresCuadrados);

		
	}

}
