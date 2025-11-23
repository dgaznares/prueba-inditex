package com.dgaznares.inditex.msprices.prueba8;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dgaznares.inditex.msprices.prueba8.dto.Estudiante;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Prueba8 {
	
	/**
	Escribe el código Java (usando streams y $\text{Collectors}$) para resolver el 
	siguiente problema:Dada una $\text{List<Estudiante>}$, agrupa a los estudiantes por 
	su $\text{carrera}$ y, para cada carrera, calcula la $\text{calificación promedio}$ 
	de todos los estudiantes en ese grupo.
	Resultado EsperadoEl resultado debe ser un $\text{Map}$ con la siguiente
	firma:
		$$\text{Map<String, Double>}$$Donde:$\text{String}$ (Clave): El nombre de la carrera.
		$\text{Double}$ (Valor): El promedio de las calificaciones de esa carrera.
	*/
	public static void main (String[] args) {
        List<Estudiante> lista = List.of(
                Estudiante.builder().nombre("Ana").carrera("Ingenieria").calificacion(85).build(),
                Estudiante.builder().nombre("Luis").carrera("Medicina").calificacion(90).build(),
                Estudiante.builder().nombre("María").carrera("Derecho").calificacion(78).build(),
                Estudiante.builder().nombre("Jorge").carrera("Arquitectura").calificacion(88).build(),
                Estudiante.builder().nombre("Sofía").carrera("Ciencias").calificacion(92).build(),
                Estudiante.builder().nombre("Pablo").carrera("Economia").calificacion(74).build(),
                Estudiante.builder().nombre("Laura").carrera("Psicologia").calificacion(81).build(),
                Estudiante.builder().nombre("Diego").carrera("Administracion").calificacion(69).build(),
                // Repetimos la carrera "Ingenieria" para cumplir el requisito
                Estudiante.builder().nombre("Carlos").carrera("Ingenieria").calificacion(95).build(),
                Estudiante.builder().nombre("Marta").carrera("Matematicas").calificacion(87).build());
        
        
       Map<String, Double> resultado = lista.stream()
        .collect(Collectors.groupingBy(Estudiante::getCarrera,
        		Collectors.averagingDouble(Estudiante::getCalificacion)));
      log.info("El resultado es: {} ", resultado);	
	}

}
