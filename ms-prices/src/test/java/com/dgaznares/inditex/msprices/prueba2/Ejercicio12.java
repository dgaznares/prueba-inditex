package com.dgaznares.inditex.msprices.prueba2;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.dgaznares.inditex.msprices.prueba2.dto.Producto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ejercicio12 {
	
	/**
	 * Contexto: Tienes una lista de productos y quieres aplicar un descuento del 10% solo 
	 * a aquellos productos que a la vez tienen un precio mayor a 100 Y que contienen la palabra "Pro" en el nombre.
	 */

	public List<Producto> listaProductos =
        Arrays.asList(
        		Producto.builder().nombre("ProMax Speaker").precio(new BigDecimal("150.00")).build(),
                Producto.builder().nombre("UltraPro Monitor").precio(new BigDecimal("250.00")).build(),
                Producto.builder().nombre("Camera Pro 2000").precio(new BigDecimal("199.99")).build(),
                Producto.builder().nombre("Laptop ProBook").precio(new BigDecimal("1200.00")).build(),
                Producto.builder().nombre("SmartWatch Pro").precio(new BigDecimal("129.99")).build(),
                Producto.builder().nombre("Basic Speaker").precio(new BigDecimal("49.99")).build(),
                Producto.builder().nombre("Standard Monitor").precio(new BigDecimal("89.99")).build(),
                Producto.builder().nombre("Camera Lite").precio(new BigDecimal("75.00")).build(),
                Producto.builder().nombre("Laptop Air").precio(new BigDecimal("999.99")).build(),
                Producto.builder().nombre("USB Cable").precio(new BigDecimal("9.99")).build()
        );
	
	public List<Producto> aplicarDescuentoCondicional(List<Producto> productos){
		
		Predicate<Producto> precioMayor100 = p -> p.getPrecio().compareTo(new BigDecimal("100")) == 1;
		Predicate<Producto> contienePro = p -> p.getNombre().toUpperCase().contains("pro".toUpperCase());
		Predicate<Producto> precioMayor100YcontienePro = precioMayor100.and(contienePro);
		List<Producto> listaDescuentos = productos.stream()
				.filter(precioMayor100YcontienePro)
				.map(p -> Producto.builder()
						.nombre(p.getNombre())
						.precio(p.getPrecio().subtract(p.getPrecio().multiply(new BigDecimal("0.1"))))
						.build())
				.toList();
		return listaDescuentos;
	}
	
	public static void main (String[] args) {
		Supplier<Ejercicio12> ejercicio12Supp = Ejercicio12::new;
		Ejercicio12 ejercicio12 = ejercicio12Supp.get();
		List<Producto> listaDescuentos = ejercicio12.aplicarDescuentoCondicional(ejercicio12.listaProductos);
		
		log.info("La lista dddde productos con un 10% de descuento es:");
		listaDescuentos.forEach(p -> log.info(p.toString()));
		
		Function<String, String> sayHello = s->s.concat("Hello");
		log.info(sayHello.apply("David"));
		
		
	}
	
}
