package com.dgaznares.inditex.msprices.prueba2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Prueba2 {
	
	private List<String> listaNombres = Arrays.asList(
            "",
            null,
            "mgarcia",
            "lrodriguez",
            "sanchez",
            null,
            "pedro",
            "ana",
            "user123",
            "dev_guy"
        );
	
	public int obtenerLongitudPrimerNombreValidoFuncional(List<String> nombres) {
	    
		Predicate<String> noNulo = s -> s != null;
		Predicate<String> noVacio = s -> !s.isBlank();
		Predicate<String> noNuloYNoVacio = noNulo.and(noVacio);
		
		return nombres.stream()
			.filter(noNuloYNoVacio)
			.findFirst()
			.map(String::toUpperCase)
			.map(String::length)
			.orElse(-1);
	}
	
	public static void main(String[] args) {
		Supplier<Prueba2> prueba2Supplier = Prueba2::new;
		Prueba2 prueba2 = prueba2Supplier.get();
		
//		Contexto: Tienes una lista de posibles nombres de usuario (String). Quieres encontrar el 
//		primer nombre válido que no sea nulo ni esté vacío, ponerlo en mayúsculas
//		y obtener su longitud. Si ningún nombre es válido, el resultado debe ser -1.
		log.info("Prueba 2");
		
		log.info("La longitid del primer elemento válido es: {}", prueba2.obtenerLongitudPrimerNombreValidoFuncional(
				prueba2.listaNombres));
	}

}
