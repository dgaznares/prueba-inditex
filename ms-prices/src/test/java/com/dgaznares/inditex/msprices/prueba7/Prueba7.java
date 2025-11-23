package com.dgaznares.inditex.msprices.prueba7;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Prueba7 {
	public static void main(String[] args) {
		CalcularIVA calularIva = v-> Double.valueOf(v) * Double.valueOf(0.21) ;
		
		List<Double>  listaValores =  Arrays.asList(120.5, 30.0, 70.75, 100.0);
		List<Double> resultado = listaValores.stream()
				.map(v -> calularIva.calcular(v))
				.toList();
		
		log.info("EL resultado es:{}", resultado);
	}
}
