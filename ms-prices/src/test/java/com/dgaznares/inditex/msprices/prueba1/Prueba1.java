package com.dgaznares.inditex.msprices.prueba1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.dgaznares.inditex.msprices.prueba1.dto.Cantidad;
import com.dgaznares.inditex.msprices.prueba1.dto.Producto;
import com.dgaznares.inditex.msprices.prueba1.dto.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Prueba1 {
	
    public static List<Producto>  products = List.of(Producto.builder()
    		.cantidad(1)
			.nombre("Camiseta básica")
			.precio(new BigDecimal("19.99"))
			.build(),
		Producto.builder()
			.cantidad(2)
			.nombre("Vaqueros slim")
			.precio(new BigDecimal("49.99"))
			.build(),
		Producto.builder()
			.cantidad(1)
			.nombre("Chaqueta impermeable")
			.precio(new BigDecimal("89.90"))
			.build()
	);
    
    public static List<Cantidad>  cantidades = List.of(Cantidad.builder()
    		.cantidad("1")
			.nombre("Camiseta básica")
			.build(),
			Cantidad.builder()
			.cantidad("2")
			.nombre("Vaqueros slim")
			.build(),
			Cantidad.builder()
			.cantidad("3")
			.nombre("Chaqueta impermeable")
			.build()
	);
    
    
    private static List<BigDecimal> preciosCaros = new ArrayList<BigDecimal>(); 

    
    public static Optional<Integer> convertirASeguro(String s) {
    
    	try {
    		return Optional.of(Integer.parseInt(s));
    	}catch (NumberFormatException e) {
			return Optional.empty();
		}
    }
    
    private static Function<String, Optional<Integer>> convertirASeguroV2 = 
    		x -> {
    			try {
    	    		return Optional.of(Integer.parseInt(x));
    	    	}catch (NumberFormatException e) {
    				return Optional.empty();
    			}
    		};
    		
    		
    public static Optional<User> buscarUsuario(int id) {
        if (id == 1) 
        	return Optional.of(new User(Optional.of("test@mail.com")));
        if (id == 2) 
        	return Optional.of(new User(Optional.empty())); // Usuario encontrado, email vacío
        
        return Optional.empty(); // Usuario no encontrado
    }		
    private static String obtenerEmailSeguro(int id) {
    	return buscarUsuario(id)
    			.flatMap(User::getEmail)
    			.orElse("Email no disponible");		
	}
    
	
	public static void main(String[] args) {
		//Ejercicio 1: Calcular cuantos productos tienen un precio mayor a 50
		long cuantos = Prueba1.products.stream()
				.filter(p -> p.getPrecio().compareTo(BigDecimal.valueOf(50)) >  0)
				.count();
		log.info("Hay {} productos con precio mayor a 50", cuantos);
		
		MathContext mc = new MathContext(6, RoundingMode.HALF_UP); // 6 dígitos de precisión
		BigDecimal total = products.stream()
				.map(p -> p.getPrecio().multiply(BigDecimal.valueOf(p.getCantidad()), mc))
				.reduce(BigDecimal.ZERO, BigDecimal::add);	
		log.info("El precio total de los productos es {}", total);
		
		
		String salida = products.stream()
						.findFirst()	
						.map(p-> p.getNombre().toUpperCase())
						.orElse("Producto no encontrado");
		
		log.info("El primer producto es: {}", salida);
				

		preciosCaros = products.stream()
			.filter(p -> p.getPrecio().compareTo(BigDecimal.valueOf(50)) < 0)
			.map(Producto::getPrecio)
			.collect(Collectors.toList());
		log.info("Precios caros: {}", preciosCaros);
		
		Integer sumaDeCantidades =cantidades.parallelStream()
		.map(p -> convertirASeguro(p.getCantidad().toString()))
		.mapToInt(p  -> p.orElseThrow())
		.sum();
		log.info("La suma de las cantidades es: {}", sumaDeCantidades);
		
		Integer sumaDeCantidadesMasOptimo = cantidades.stream()
			    .map(p -> convertirASeguro(p.getCantidad().toString())) // Stream<Optional<Integer>>
			    .filter(Optional::isPresent)                           // Filtra los fallidos (empty)
			    .mapToInt(Optional::get)                               // Desenvolver los exitosos (es seguro)
			    .sum();
		
		log.info("La suma de las cantidades mejorada es: {}", sumaDeCantidadesMasOptimo);
		
		Integer sumaDeCantidadesFuntional = cantidades.stream()
				.map(p -> convertirASeguroV2.apply(p.getCantidad().toString()))
				.filter(Optional::isPresent)
				.mapToInt(Optional::get)
				.sum();
		log.info("La suma de las cantidades funcional es: {}", sumaDeCantidadesFuntional);
		
//		Ejercicio 6: Composición de Predicados y Funciones
//		Contexto: Tienes un Stream de palabras y quieres transformarlas aplicando una lógica compleja que 
//		combina un filtro y dos transformaciones.
		Predicate<String> esMayorDeCinco = s -> s.length() > 5;
		Predicate<String> contieneA = s -> s.contains("a");
		Predicate <String> filtroFinal = esMayorDeCinco.and(contieneA); 
		Function<String, String> transformarAMayusculas = s -> s.toUpperCase();
		Function<String, String> transformarGuiones = s -> s.replace("A", "_");
		Function<String, String> transformacionFinal = transformarAMayusculas.andThen(transformarGuiones);
		
		List<String> palabras = cantidades.stream()
				.map(Cantidad::getNombre)
				.filter(filtroFinal)
				.map(cadena ->transformacionFinal.apply(cadena))
				.collect(Collectors.toList());
		log.info("Palabras transformadas: {}", palabras);
		
//		Ejercicio 8: Desenvolver Optional Anidados (flatMap)
//		Contexto: Tienes una estructura donde la obtención de un usuario podría fallar (Optional<User>) y, además,
//		el campo que te interesa dentro del usuario (el email) también es opcional (Optional<String>). Quieres unificar el manejo de errores.		
		String email = Prueba1.obtenerEmailSeguro(1);
		log.info("Email usuario 1: {}", email);
		
		
	

		
	}

}



