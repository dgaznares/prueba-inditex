package com.dgaznares.inditex.msprices.prueba1;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.dgaznares.inditex.msprices.prueba1.dto.Empleado;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

	

@Slf4j
@NoArgsConstructor
public class Ejercicio9 {
	
	private static List<Empleado> listaEmpleados =  List.of(
	        Empleado.builder().nombre("Carlos Ruiz").rol("Manager").edad(45).salario(new BigDecimal("75000")).build(),
	        Empleado.builder().nombre("María López").rol("Manager").edad(52).salario(new BigDecimal("82000")).build(),
	        Empleado.builder().nombre("Javier Martín").rol("Manager").edad(48).salario(new BigDecimal("79000")).build(),
	        Empleado.builder().nombre("Ana Gómez").rol("Developer").edad(30).salario(new BigDecimal("52000")).build(),
	        Empleado.builder().nombre("Luis Fernández").rol("Developer").edad(28).salario(new BigDecimal("48000")).build(),
	        Empleado.builder().nombre("Sofía Herrera").rol("QA").edad(33).salario(new BigDecimal("46000")).build(),
	        Empleado.builder().nombre("Miguel Sánchez").rol("DevOps").edad(38).salario(new BigDecimal("60000")).build(),
	        Empleado.builder().nombre("Laura Díaz").rol("HR").edad(41).salario(new BigDecimal("50000")).build(),
	        Empleado.builder().nombre("Pedro Torres").rol("Support").edad(26).salario(new BigDecimal("35000")).build(),
	        Empleado.builder().nombre("Cristina Ramos").rol("Analyst").edad(29).salario(new BigDecimal("43000")).build()
	    );
	
	private static String obtenerExtensionSegura(Optional<String> nombreFicherOpt) {
		
		Supplier<String> error = () -> "Error. No hay extensión";
		return nombreFicherOpt						
					.map(s -> { 
						int i = s.lastIndexOf(".");
						return (i == -1) ? "": s.substring(i+ 1); 	
					})
					.filter(ext -> !ext.isEmpty())
					.orElseGet(error);
	}
	
	
	public static void main(String[] args) {
		
//		Ejercicio 9: Filtrado, Mapeo y Reducción Condicional
//		Contexto: Tienes una lista de empleados. Quieres calcular la suma total de los salarios, pero solo de los empleados que cumplen 
//		dos criterios: son managers y tienen más de 40 años.
		
		Predicate<Empleado> esManager = e -> "manager".equalsIgnoreCase(e.getRol());
		Predicate<Empleado> masDe40años = e -> e.getEdad() > 40;
		Predicate<Empleado> managerMayorDe40 = esManager.and(masDe40años);
		BigDecimal salariosDeManagersde40 = listaEmpleados.stream()
				.filter(managerMayorDe40)
				.map(Empleado::getSalario)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		log.info("El sallario de los manager de mas de 40 es: {}",	salariosDeManagersde40 );
		// Caso 1: Extensión Válida
        log.info("Caso 1 (Válido): " + Ejercicio9.obtenerExtensionSegura(Optional.of("reporte.pdf"))); 
        // Salida: pdf

        // Caso 2: Sin Extensión (filtro lo vacía)
        log.info("Caso 2 (Sin Ext.): " + Ejercicio9.obtenerExtensionSegura(Optional.of("readme")));     
        // Salida: Error: Extensión no definida.

        // Caso 3: Optional Vacío (lo vacía al inicio)
        log.info("Caso 3 (Vacío): " + Ejercicio9.obtenerExtensionSegura(Optional.empty()));         
        // Salida: Error: Extensión no definida.
	
			
        Predicate<Empleado> edadImpar = e -> (e.getEdad() % 2) != 0;
        List<Integer> empleadosEdadImpar = listaEmpleados.stream()
        		.filter(edadImpar)
        		.map(e -> e.getEdad().intValue() * 2)
        		.toList();
        log.info("Ejercicio11: " + empleadosEdadImpar); 	
		
										
	
	}
	
	
	

}
