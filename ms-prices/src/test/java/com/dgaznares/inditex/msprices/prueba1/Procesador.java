package com.dgaznares.inditex.msprices.prueba1;

import java.util.function.BiFunction;
import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Procesador {
//	public int aplicarEstrategiaBi(int x, int y,  BiFunction<Integer, Integer, Integer> estrategia) {
//        return estrategia.apply(x, y);
//    }
//	
//	public int aplicarEstrategia(int x, Function<Integer, Integer> estrategia) {
//        return estrategia.apply(x);
//    }
	
	private static BiFunction<Integer, Integer, Integer> estrategiaDoble = (x, y) -> x * y;
	private static Function<Integer, Integer> estrategiaTriple = x -> x * 3;
	
	
	public static void main(String[] args) {
		Procesador procesador = new Procesador();
		
		int resultadoDoble = procesador.estrategiaDoble.apply(5,2);
		int resultadoTriple = procesador.estrategiaTriple.apply(5);
		
		log.info("Resultado Doble: " + resultadoDoble); // Salida: 10
		log.info("Resultado Triple: " + resultadoTriple); // Salida: 15
	}

}



