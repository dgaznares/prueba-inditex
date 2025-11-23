package com.dgaznares.inditex.msprices.prueba7;


@FunctionalInterface
public interface CalcularIVA{
	Double calcular (Double valor);
	
	default Double calcular (Double valor, Double porcentaje) {
		return Double.valueOf(valor) * Double.valueOf(porcentaje);
	}
}
