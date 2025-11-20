package com.dgaznares.inditex.msprices.prueba2;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Supplier;

import com.dgaznares.inditex.msprices.prueba2.dto.Usuario;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ejercicio13 {
	/**
	 * Contexto: Tienes un objeto Usuario que podría ser nulo. Si el usuario existe, tiene un método getSaldo() 
	 * que devuelve un Optional<BigDecimal>. Quieres calcular la mitad del saldo solo si el saldo existe. 
	 * Si el usuario no existe O el saldo no existe, el resultado debe ser BigDecimal.ZERO.
	 */
	
	// Función simulada que busca al usuario (podría ser nulo)
	public Optional<Usuario> buscarUsuario(int id) {
	    if (id == 1) return Optional.of(Usuario.builder()
	    									.saldo(Optional.of(new BigDecimal("500.00")))
	    									.build()); // Saldo OK
	    if (id == 2) return Optional.of(new Usuario(Optional.empty())); // Usuario OK, saldo nulo
	    return Optional.empty(); // Usuario nulo
	}
	
	public BigDecimal getSaldo(Optional<Usuario> usuario) {
		return usuario
		.flatMap(p->p.getSaldo())
		.map(s ->   s.divide(new BigDecimal("2")))
		.orElse(BigDecimal.ZERO);			
	}
	
	public static void main (String[] args) {
		Supplier<Ejercicio13> ejercicio13Supp = Ejercicio13::new;
		Ejercicio13 ejercicio13 = ejercicio13Supp.get();
		BigDecimal resultado0 = ejercicio13.getSaldo(ejercicio13.buscarUsuario(0));
		log.info("El resultado 0 es: {}", resultado0);
		
		BigDecimal resultado1 = ejercicio13.getSaldo(ejercicio13.buscarUsuario(1));
		log.info("El resultado 1 es:{}", resultado1);
		
		BigDecimal resultado2 = ejercicio13.getSaldo(ejercicio13.buscarUsuario(2));
		log.info("El resultado 2 es:{}", resultado2);
		
	}
}

	